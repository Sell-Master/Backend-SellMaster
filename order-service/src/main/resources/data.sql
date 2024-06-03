-- Inserción de datos en la tabla orders
INSERT IGNORE INTO orders (fecha, montoTotal, clientID, userID) VALUES
    ('2024-05-31 14:00:00', 150.75, 1, 1),
    ('2024-05-31 15:00:00', 200.00, 2, 1),
    ('2024-05-31 16:00:00', 300.50, 3, 1),
    ('2024-05-31 17:00:00', 100.25, 4, 1);

-- Inserción de datos en la tabla order_details
INSERT IGNORE INTO order_details (cantidad, productID, orderID) VALUES
    (5, 1, 1),
    (3, 2, 1),
    (2, 3, 2),
    (4, 4, 3),
    (1, 1, 4);
