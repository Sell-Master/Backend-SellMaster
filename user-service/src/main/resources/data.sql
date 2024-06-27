-- Insertar datos iniciales en typeofuser si no existen
INSERT INTO typeofuser (description)
SELECT 'ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM typeofuser WHERE description = 'ADMIN');
INSERT INTO typeofuser (description)
SELECT 'USER'
    WHERE NOT EXISTS (SELECT 1 FROM typeofuser WHERE description = 'USER');
INSERT INTO typeofuser (description)
SELECT 'CLIENT'
    WHERE NOT EXISTS (SELECT 1 FROM typeofuser WHERE description = 'CLIENT');

