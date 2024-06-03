INSERT IGNORE INTO orders (fecha, montoTotal, clientID, userID) VALUES
    ('2024-05-31 14:00:00', 150.75, 1, 201),
    ('2024-05-31 15:00:00', 200.00, 2, 202),
    ('2024-05-31 16:00:00', 300.50, 3, 203),
    ('2024-05-31 17:00:00', 100.25, 4, 204);

INSERT IGNORE INTO order_details (cantidad, productID, orderID) VALUES
    (5, 101, 1),
    (3, 102, 1),
    (2, 103, 2),
    (4, 104, 3),
    (1, 101, 4);