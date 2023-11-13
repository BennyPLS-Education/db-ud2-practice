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
VALUES (1, 1, 100),   -- Warehouse A stores 100 Apples
       (2, 2, 200),   -- Warehouse B stores 200 Oranges
       (3, 3, 150),   -- Warehouse C stores 150 Tomatoes
       (4, 4, 300),   -- Warehouse D stores 300 Bananas
       (5, 5, 50),    -- Warehouse E stores 50 Strawberries
       (6, 6, 75),    -- Warehouse F stores 75 Pineapples
       (7, 7, 120),   -- Warehouse G stores 120 Blueberries
       (8, 8, 250),   -- Warehouse H stores 250 Carrots
       (9, 9, 90),    -- Warehouse I stores 90 Lettuce
       (10, 10, 80),  -- Warehouse J stores 80 Peppers
       (11, 11, 100), -- Warehouse K stores 100 Potatoes
       (12, 12, 200), -- Warehouse L stores 200 Corn
       (13, 13, 150), -- Warehouse M stores 150 Grapes
       (14, 14, 300), -- Warehouse N stores 300 Watermelons
       (15, 15, 75),  -- Warehouse O stores 75 Avocados
       (16, 16, 120), -- Warehouse P stores 120 Broccoli
       (17, 17, 90),  -- Warehouse Q stores 90 Cucumbers
       (18, 18, 150), -- Warehouse R stores 150 Apples
       (19, 19, 50),  -- Warehouse S stores 50 Strawberries
       (20, 20, 75); -- Warehouse T stores 75 Avocados

INSERT INTO clients (name, type)
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
