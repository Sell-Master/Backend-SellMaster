-- Insertar datos iniciales en dispatches si no existen
INSERT INTO dispatches (partnerID, productID, userID, quantity, date)
SELECT 1, 1, 1, 50, '2024-05-31 10:00:00'
    WHERE NOT EXISTS (SELECT 1 FROM dispatches WHERE partnerID = 1 AND productID = 1 AND userID = 1 AND date = '2024-05-31 10:00:00');
INSERT INTO dispatches (partnerID, productID, userID, quantity, date)
SELECT 2, 2, 2, 30, '2024-05-31 11:00:00'
    WHERE NOT EXISTS (SELECT 1 FROM dispatches WHERE partnerID = 2 AND productID = 2 AND userID = 2 AND date = '2024-05-31 11:00:00');
INSERT INTO dispatches (partnerID, productID, userID, quantity, date)
SELECT 3, 3, 3, 20, '2024-05-31 12:00:00'
    WHERE NOT EXISTS (SELECT 1 FROM dispatches WHERE partnerID = 3 AND productID = 3 AND userID = 3 AND date = '2024-05-31 12:00:00');
INSERT INTO dispatches (partnerID, productID, userID, quantity, date)
SELECT 4, 4, 4, 40, '2024-05-31 13:00:00'
    WHERE NOT EXISTS (SELECT 1 FROM dispatches WHERE partnerID = 4 AND productID = 4 AND userID = 4 AND date = '2024-05-31 13:00:00');
