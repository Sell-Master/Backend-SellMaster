-- Insertar datos iniciales en inventory si no existen
INSERT INTO inventory (productID, userID, stock)
SELECT 1, 1, 100
    WHERE NOT EXISTS (SELECT 1 FROM inventory WHERE productID = 1 AND userID = 1);
INSERT INTO inventory (productID, userID, stock)
SELECT 2, 2, 300
    WHERE NOT EXISTS (SELECT 1 FROM inventory WHERE productID = 2 AND userID = 2);
INSERT INTO inventory (productID, userID, stock)
SELECT 3, 3, 200
    WHERE NOT EXISTS (SELECT 1 FROM inventory WHERE productID = 3 AND userID = 3);
INSERT INTO inventory (productID, userID, stock)
SELECT 4, 4, 400
    WHERE NOT EXISTS (SELECT 1 FROM inventory WHERE productID = 4 AND userID = 4);
