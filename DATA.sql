USE inventory;

INSERT INTO regions (name, x, y)
VALUES ('New York', 40.7128, -74.0060),
       ('Los Angeles', 34.0522, -118.2437),
       ('Chicago', 41.8781, -87.6298),
       ('Houston', 29.7604, -95.3698),
       ('Miami', 25.7617, -80.1918),
       ('San Francisco', 37.7749, -122.4194),
       ('Seattle', 47.6062, -122.3321),
       ('Boston', 42.3601, -71.0589),
       ('Dallas', 32.7767, -96.7970),
       ('Atlanta', 33.7490, -84.3880),
       ('Denver', 39.7392, -104.9903),
       ('Phoenix', 33.4484, -112.0740),
       ('Philadelphia', 39.9526, -75.1652),
       ('Las Vegas', 36.1699, -115.1398),
       ('Austin', 30.2672, -97.7431),
       ('Portland', 45.5051, -122.6750),
       ('San Diego', 32.7157, -117.1611),
       ('Nashville', 36.1627, -86.7816),
       ('Minneapolis', 44.9778, -93.2650),
       ('Orlando', 28.5383, -81.3792);


INSERT INTO warehouses (name, region)
VALUES ('Warehouse A', 1),  -- New York
       ('Warehouse B', 2),  -- Los Angeles
       ('Warehouse C', 3),  -- Chicago
       ('Warehouse D', 4),  -- Houston
       ('Warehouse E', 5),  -- Miami
       ('Warehouse F', 6),  -- San Francisco
       ('Warehouse G', 7),  -- Seattle
       ('Warehouse H', 8),  -- Boston
       ('Warehouse I', 9),  -- Dallas
       ('Warehouse J', 10), -- Atlanta
       ('Warehouse K', 11), -- Denver
       ('Warehouse L', 12), -- Phoenix
       ('Warehouse M', 13), -- Philadelphia
       ('Warehouse N', 14), -- Las Vegas
       ('Warehouse O', 15), -- Austin
       ('Warehouse P', 16), -- Portland
       ('Warehouse Q', 17), -- San Diego
       ('Warehouse R', 18), -- Nashville
       ('Warehouse S', 19), -- Minneapolis
       ('Warehouse T', 20); -- Orlando

INSERT INTO producers (name, quality, region)
VALUES ('Acme Produce Co.', 'A', 1),     -- New York
       ('West Coast Farms', 'B', 2),     -- Los Angeles
       ('Midwest AgriPro', 'A', 3),      -- Chicago
       ('Southern Harvest', 'B', 4),     -- Houston
       ('Bay Area Organic', 'C', 6),     -- San Francisco
       ('Miami Fresh Foods', 'D', 5),    -- Miami
       ('Seattle Green Groves', 'B', 7), -- Seattle
       ('Boston Farmstead', 'A', 8),     -- Boston
       ('Dallas Gardeners', 'C', 9),     -- Dallas
       ('Georgia Growers', 'B', 10),     -- Atlanta
       ('Denver Produce Co.', 'C', 11),  -- Denver
       ('Desert Harvest', 'D', 12),      -- Phoenix
       ('Philly Fresh Picks', 'A', 13),  -- Philadelphia
       ('Vegas Farms', 'E', 14),         -- Las Vegas
       ('Austin AgriPro', 'C', 15),      -- Austin
       ('Portland Organics', 'B', 16),   -- Portland
       ('San Diego Greens', 'C', 17),    -- San Diego
       ('Nashville Farms', 'D', 18),     -- Nashville
       ('Twin Cities Produce', 'B', 19), -- Minneapolis
       ('Austin AgriPro', 'C', 20); -- Austin

INSERT INTO products (name, producer, quality)
VALUES ('Apples', 1, 'A'),        -- Acme Produce Co.
       ('Oranges', 2, 'B'),       -- West Coast Farms
       ('Tomatoes', 3, 'A'),      -- Midwest AgriPro
       ('Bananas', 4, 'B'),       -- Southern Harvest
       ('Strawberries', 6, 'C'),  -- Bay Area Organic
       ('Pineapples', 5, 'D'),    -- Miami Fresh Foods
       ('Blueberries', 7, 'B'),   -- Seattle Green Groves
       ('Carrots', 8, 'A'),       -- Boston Farmstead
       ('Lettuce', 9, 'C'),       -- Dallas Gardeners
       ('Peppers', 10, 'B'),      -- Georgia Growers
       ('Potatoes', 11, 'C'),     -- Denver Produce Co.
       ('Corn', 12, 'D'),         -- Desert Harvest
       ('Grapes', 13, 'A'),       -- Philly Fresh Picks
       ('Watermelons', 14, 'E'),  -- Vegas Farms
       ('Avocados', 15, 'C'),     -- Austin AgriPro
       ('Broccoli', 16, 'B'),     -- Portland Organics
       ('Cucumbers', 17, 'C'),    -- San Diego Greens
       ('Apples', 18, 'D'),       -- Nashville Farms
       ('Strawberries', 19, 'B'), -- Twin Cities Produce
       ('Avocados', 20, 'C'); -- Austin AgriPro

INSERT INTO warehouses_products (warehouse, product, quantity)
VALUES
    -- Warehouse A
    (1, 1, 100),   -- stores 100 Apples
    (1, 2, 120),   -- stores 120 Oranges
    (1, 3, 50),    -- stores 50 Bananas

    -- Warehouse B
    (2, 4, 200),   -- stores 200 Pineapples
    (2, 5, 180),   -- stores 180 Strawberries
    (2, 6, 100),   -- stores 100 Tomatoes

    -- Warehouse C
    (3, 7, 150),   -- stores 150 Carrots
    (3, 8, 250),   -- stores 250 Blueberries
    (3, 9, 180),   -- stores 180 Lettuce

    -- Warehouse D
    (4, 10, 300),  -- stores 300 Broccoli
    (4, 11, 30),   -- stores 30 Watermelons
    (4, 12, 20),   -- stores 20 Avocados

    -- Warehouse E
    (5, 13, 50),   -- stores 50 Grapes
    (5, 14, 60),   -- stores 60 Potatoes
    (5, 15, 40),   -- stores 40 Corn

    -- Warehouse F
    (6, 16, 75),   -- stores 75 Avocados
    (6, 17, 90),   -- stores 90 Broccoli
    (6, 18, 70),   -- stores 70 Cucumbers

    -- Warehouse G
    (7, 19, 120),  -- stores 120 Oranges
    (7, 20, 200),  -- stores 200 Tomatoes
    (7, 1, 150),   -- stores 150 Bananas

    -- Warehouse H
    (8, 2, 250),   -- stores 250 Pineapples
    (8, 3, 120),   -- stores 120 Strawberries
    (8, 4, 80),    -- stores 80 Tomatoes
    -- Warehouse I
    (9, 5, 90),    -- stores 90 Apples
    (9, 6, 60),    -- stores 60 Oranges
    (9, 7, 40),    -- stores 40 Bananas

    -- Warehouse J
    (10, 8, 80),   -- stores 80 Strawberries
    (10, 9, 80),   -- stores 80 Pineapples
    (10, 10, 60),  -- stores 60 Blueberries

    -- Warehouse K
    (11, 11, 100), -- stores 100 Carrots
    (11, 12, 150), -- stores 150 Lettuce
    (11, 13, 120), -- stores 120 Broccoli

    -- Warehouse L
    (12, 14, 200), -- stores 200 Watermelons
    (12, 15, 100), -- stores 100 Avocados
    (12, 16, 80),  -- stores 80 Grapes

    -- Warehouse M
    (13, 17, 150), -- stores 150 Potatoes
    (13, 18, 200), -- stores 200 Corn
    (13, 19, 150), -- stores 150 Avocados

    -- Warehouse N
    (14, 20, 300), -- stores 300 Grapes
    (14, 1, 50),   -- stores 50 Potatoes
    (14, 2, 30),   -- stores 30 Corn

    -- Warehouse O
    (15, 3, 75),   -- stores 75 Avocados
    (15, 4, 80),   -- stores 80 Broccoli
    (15, 5, 80),   -- stores 80 Cucumbers

    -- Warehouse P
    (16, 6, 120),  -- stores 120 Apples
    (16, 7, 60),   -- stores 60 Oranges
    (16, 8, 40),   -- stores 40 Tomatoes

    -- Warehouse Q
    (17, 9, 90),   -- stores 90 Bananas
    (17, 10, 150), -- stores 150 Pineapples
    (17, 11, 80),  -- stores 80 Strawberries

    -- Warehouse R
    (18, 12, 150), -- stores 150 Blueberries
    (18, 13, 50),  -- stores 50 Carrots
    (18, 14, 20),  -- stores 20 Lettuce

    -- Warehouse S
    (19, 15, 50),  -- stores 50 Broccoli
    (19, 16, 40),  -- stores 40 Watermelons
    (19, 17, 30),  -- stores 30 Avocados

    -- Warehouse T
    (20, 18, 75),  -- stores 75 Grapes
    (20, 19, 80),  -- stores 80 Potatoes
    (20, 20, 60); -- stores 60 Corn


INSERT INTO clients (NAME, type)
VALUES ('John Doe', 'PARTICULAR'),
       ('Alice Smith', 'PARTICULAR'),
       ('XYZ Company', 'BUSINESS'),
       ('ABC Inc.', 'BUSINESS'),
       ('Small Biz LLC', 'SMALL BUSINESS'),
       ('Sarah Brown', 'PARTICULAR'),
       ('Beta Enterprises', 'BUSINESS'),
       ('Jill Johnson', 'PARTICULAR'),
       ('Gamma Corp', 'BUSINESS'),
       ('Quick Services', 'SMALL BUSINESS'),
       ('Michael Wilson', 'PARTICULAR'),
       ('Smith & Sons', 'BUSINESS'),
       ('Lucy Davis', 'PARTICULAR'),
       ('Tech Solutions Inc.', 'BUSINESS'),
       ('Green Thumb Gardening', 'SMALL BUSINESS'),
       ('Olivia White', 'PARTICULAR'),
       ('Alpha Investments', 'BUSINESS'),
       ('Ella Jones', 'PARTICULAR'),
       ('Sunshine Bakery', 'SMALL BUSINESS'),
       ('David Clark', 'PARTICULAR');

INSERT INTO orders (warehouse, product, client, quantity, date_delivered, status)
VALUES (1, 1, 1, 50, '2023-11-15 12:00:00', 'DELIVERED'),
       (2, 2, 2, 30, '2023-11-14 14:30:00', 'DELIVERED'),
       (3, 3, 3, 20, '2023-11-13 10:45:00', 'DELIVERED'),
       (4, 4, 4, 40, '2023-11-12 11:20:00', 'DELIVERED'),
       (5, 5, 5, 25, '2023-11-11 16:10:00', 'DELIVERED'),
       (6, 6, 6, 35, NULL, 'IN SHIPPING'),
       (7, 7, 7, 15, NULL, 'PREPARING'),
       (8, 8, 8, 45, NULL, 'PREPARING'),
       (9, 9, 9, 28, NULL, 'PREPARING'),
       (10, 10, 10, 22, NULL, 'PREPARING'),
       (11, 11, 11, 38, NULL, 'PREPARING'),
       (12, 12, 12, 17, NULL, 'PREPARING'),
       (13, 13, 13, 33, NULL, 'PREPARING'),
       (14, 14, 14, 29, NULL, 'PREPARING'),
       (15, 15, 15, 41, NULL, 'PREPARING'),
       (16, 16, 16, 23, NULL, 'PREPARING'),
       (17, 17, 17, 18, NULL, 'PREPARING'),
       (18, 18, 18, 27, NULL, 'PREPARING'),
       (19, 19, 19, 36, NULL, 'PREPARING'),
       (20, 20, 20, 21, NULL, 'PREPARING');
