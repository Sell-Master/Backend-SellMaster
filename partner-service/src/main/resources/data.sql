-- Insertar datos iniciales en partners si no existen
INSERT INTO partners (partner_name, RUC, additional_info)
SELECT 'Partner A', 12345678901, 'Información adicional del Partner A'
    WHERE NOT EXISTS (SELECT 1 FROM partners WHERE partner_name = 'Partner A' AND RUC = 12345678901);
INSERT INTO partners (partner_name, RUC, additional_info)
SELECT 'Partner B', 23456789012, 'Información adicional del Partner B'
    WHERE NOT EXISTS (SELECT 1 FROM partners WHERE partner_name = 'Partner B' AND RUC = 23456789012);
INSERT INTO partners (partner_name, RUC, additional_info)
SELECT 'Partner C', 34567890123, 'Información adicional del Partner C'
    WHERE NOT EXISTS (SELECT 1 FROM partners WHERE partner_name = 'Partner C' AND RUC = 34567890123);
INSERT INTO partners (partner_name, RUC, additional_info)
SELECT 'Partner D', 45678901234, 'Información adicional del Partner D'
    WHERE NOT EXISTS (SELECT 1 FROM partners WHERE partner_name = 'Partner D' AND RUC = 45678901234);
