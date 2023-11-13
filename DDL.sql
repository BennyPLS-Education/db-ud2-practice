CREATE DATABASE IF NOT EXISTS inventory;

USE inventory;

################################ TABLES ################################

CREATE TABLE regions
(
    id         INT           NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255)  NOT NULL,
    x          DECIMAL(7, 4) NOT NULL,
    y          DECIMAL(7, 4) NOT NULL,

    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
);

CREATE TABLE warehouses
(
    id         INT                 NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) UNIQUE NOT NULL,
    region     INT                 NOT NULL,

    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    FOREIGN KEY (region) REFERENCES regions (id)
);

CREATE TABLE producers
(
    id         INT                            NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255)                   NOT NULL,
    quality    ENUM ('A', 'B', 'C', 'D', 'E') NOT NULL,
    region     INT                            NOT NULL,

    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    FOREIGN KEY (region) REFERENCES regions (id)
);

CREATE TABLE products
(
    id         INT                            NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255)                   NOT NULL,
    producer   INT                            NOT NULL,
    quality    ENUM ('A', 'B', 'C', 'D', 'E') NOT NULL,

    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    FOREIGN KEY (producer) REFERENCES producers (id)
);

CREATE TABLE warehouses_products
(
    warehouse  INT NOT NULL,
    product    INT NOT NULL,
    quantity   INT NOT NULL,

    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (warehouse, product),

    FOREIGN KEY (warehouse) REFERENCES warehouses (id),
    FOREIGN KEY (product) REFERENCES products (id)
);

CREATE TABLE clients
(
    id         INT          NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    type       ENUM ('PARTICULAR', 'SMALL BUSINESS', 'BUSINESS') DEFAULT 'PARTICULAR',

    CREATED_AT DATETIME                                          DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME                                          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id             INT NOT NULL AUTO_INCREMENT,
    warehouse      INT NOT NULL,
    product        INT NOT NULL,
    client         INT NOT NULL,
    quantity       INT NOT NULL,
    date_delivered DATETIME,
    date_ordered   DATETIME                                       DEFAULT CURRENT_TIMESTAMP,
    status         ENUM ('DELIVERED', 'IN SHIPPING', 'PREPARING') DEFAULT 'PREPARING',

    CREATED_AT     DATETIME                                       DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT     DATETIME                                       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    FOREIGN KEY (warehouse) REFERENCES warehouses (id),
    FOREIGN KEY (product) REFERENCES products (id),
    FOREIGN KEY (client) REFERENCES clients (id)
);

################################ TRIGGERS ################################

DELIMITER $$

################################ ORDERS INSERT ################################

CREATE TRIGGER orders_insert
    BEFORE INSERT
    ON orders
    FOR EACH ROW
BEGIN
    IF NEW.quantity >
       (SELECT quantity FROM warehouses_products WHERE warehouse = NEW.warehouse AND product = NEW.product) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Not enough products in the warehouse';
    END IF;

    UPDATE warehouses_products
    SET quantity = quantity - NEW.quantity
    WHERE warehouse = NEW.warehouse
      AND product = NEW.product;
END$$

################################ ORDERS UPDATE ################################

CREATE TRIGGER orders_update
    BEFORE UPDATE
    ON orders
    FOR EACH ROW
main:
BEGIN
    IF OLD.status != 'DELIVERED' && NEW.status = 'DELIVERED' THEN
        SET NEW.date_delivered = CURRENT_TIMESTAMP;
    END IF;

    IF NEW.quantity = OLD.quantity THEN
        LEAVE main;
    END IF;

    IF (NEW.quantity - OLD.quantity) >
       (SELECT quantity FROM warehouses_products WHERE warehouse = NEW.warehouse AND product = NEW.product) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Not enough products in the warehouse';
    END IF;

    UPDATE warehouses_products
    SET quantity = quantity + (OLD.quantity - NEW.quantity)
    WHERE warehouse = OLD.warehouse
      AND product = OLD.product;
END$$

################################ ORDERS DELETE ################################

CREATE TRIGGER orders_delete
    AFTER DELETE
    ON orders
    FOR EACH ROW
BEGIN
    UPDATE warehouses_products
    SET quantity = quantity + OLD.quantity
    WHERE warehouse = OLD.warehouse
      AND product = OLD.product;
END$$

DELIMITER ;

################################ VIEWS ################################

################################ NAMED ORDERS ################################

CREATE OR REPLACE VIEW named_orders AS
SELECT o.id,
       o.warehouse,
       w.name AS warehouse_name,
       o.product,
       p.name AS product_name,
       o.client,
       c.name AS client_name,
       o.quantity,
       o.date_delivered,
       o.date_ordered,
       o.status,
       o.CREATED_AT,
       o.UPDATED_AT
FROM orders o
         JOIN warehouses w ON o.warehouse = w.id
         JOIN products p ON o.product = p.id
         JOIN clients c ON o.client = c.id;

################################ NAMED WAREHOUSES PRODUCTS ################################

CREATE OR REPLACE VIEW named_warehouses_products AS
SELECT wp.warehouse,
       w.name  AS warehouse_name,
       wp.product,
       p.name  AS product_name,
       wp.quantity,
       p.quality,
       p.producer,
       pr.name as producer_name,
       wp.CREATED_AT,
       wp.UPDATED_AT
FROM warehouses_products wp
         JOIN warehouses w ON wp.warehouse = w.id
         JOIN products p ON wp.product = p.id
         JOIN producers pr on pr.id = p.producer;

################################ NAMED WAREHOUSES ################################

CREATE OR REPLACE VIEW named_warehouses AS
SELECT w.id,
       w.name,
       r.name AS region,
       w.CREATED_AT,
       w.UPDATED_AT
FROM warehouses w
         JOIN regions r ON w.region = r.id;

################################ NAMED PRODUCERS ################################

CREATE OR REPLACE VIEW named_producers AS
SELECT p.id,
       p.name,
       p.quality,
       r.name AS region,
       p.CREATED_AT,
       p.UPDATED_AT
FROM producers p
         JOIN regions r ON p.region = r.id;

################################ NAMED PRODUCTS ################################

CREATE OR REPLACE VIEW named_products AS
SELECT p.id,
       p.name,
       p.quality,
       pr.name AS producer,
       p.CREATED_AT,
       p.UPDATED_AT
FROM products p
         JOIN producers pr ON p.producer = pr.id;

################################ PROCEDURES ################################

DELIMITER $$

DROP PROCEDURE IF EXISTS get_warehouses_products$$
CREATE PROCEDURE get_warehouses_products(IN warehouse_id INT)
BEGIN
    SELECT p.name, wp.quantity
    FROM warehouses_products wp
             JOIN products p ON wp.product = p.id
    WHERE wp.warehouse = warehouse_id;
END$$

DROP PROCEDURE IF EXISTS get_warehouses_products_by_name$$
CREATE PROCEDURE get_warehouses_products_by_name(IN warehouse_name_in VARCHAR(255))
BEGIN
    SELECT p.name, wp.quantity
    FROM warehouses_products wp
             JOIN products p ON wp.product = p.id
             JOIN warehouses w ON wp.warehouse = w.id
    WHERE w.name = warehouse_name_in;
END$$

DROP PROCEDURE IF EXISTS get_warehouses_products_by_region$$
CREATE PROCEDURE get_warehouses_products_by_region(IN region_name_in VARCHAR(255))
BEGIN
    SELECT p.name, wp.quantity
    FROM warehouses_products wp
             JOIN products p ON wp.product = p.id
             JOIN warehouses w ON wp.warehouse = w.id
             JOIN regions r ON w.region = r.id
    WHERE r.name = region_name_in;
END$$
