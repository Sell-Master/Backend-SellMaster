-- Insertar datos iniciales en products si no existen
INSERT INTO products (product_type, brand, additional_info, price)
SELECT 'Tipo A', 'Marca X', 'Información adicional del Producto 1', 10.99
    WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_type = 'Tipo A' AND brand = 'Marca X');
INSERT INTO products (product_type, brand, additional_info, price)
SELECT 'Tipo B', 'Marca Y', 'Información adicional del Producto 2', 20.49
    WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_type = 'Tipo B' AND brand = 'Marca Y');
INSERT INTO products (product_type, brand, additional_info, price)
SELECT 'Tipo C', 'Marca Z', 'Información adicional del Producto 3', 30.79
    WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_type = 'Tipo C' AND brand = 'Marca Z');
INSERT INTO products (product_type, brand, additional_info, price)
SELECT 'Tipo D', 'Marca W', 'Información adicional del Producto 4', 40.89
    WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_type = 'Tipo D' AND brand = 'Marca W');
