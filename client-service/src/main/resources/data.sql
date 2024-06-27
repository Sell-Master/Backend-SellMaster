-- Insertar datos iniciales en clients si no existen
INSERT INTO clients (first_name, last_name, dni, age)
SELECT 'Juan', 'Perez', 12345678, 30
    WHERE NOT EXISTS (SELECT 1 FROM clients WHERE dni = 12345678);
INSERT INTO clients (first_name, last_name, dni, age)
SELECT 'Maria', 'Gomez', 87654321, 25
    WHERE NOT EXISTS (SELECT 1 FROM clients WHERE dni = 87654321);
INSERT INTO clients (first_name, last_name, dni, age)
SELECT 'Carlos', 'Lopez', 11223344, 40
    WHERE NOT EXISTS (SELECT 1 FROM clients WHERE dni = 11223344);
INSERT INTO clients (first_name, last_name, dni, age)
SELECT 'Ana', 'Martinez', 44332211, 35
    WHERE NOT EXISTS (SELECT 1 FROM clients WHERE dni = 44332211);