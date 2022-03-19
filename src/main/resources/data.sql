-- Seed users
INSERT INTO users (id, name,email,password)
VALUES (1, 'Jon Doe','jon.doe@company.com','$2y$10$EEpDUInDMTSdAgysTRlm6e/fe9q38fClaLeMmLBWxz/BQTRJU.CnW');

INSERT INTO users (id, name,email,password)
VALUES (2, 'Jon Doe 2','jon.doe2@company.com','$2y$10$EEpDUInDMTSdAgysTRlm6e/fe9q38fClaLeMmLBWxz/BQTRJU.CnW');

-- Seed items
INSERT INTO inventory_products (product_id, name, quantity)
VALUES (1,'Product 1',2);
INSERT INTO pricing_products (product_id, price)
VALUES (1,100);

INSERT INTO inventory_products (product_id, name, quantity)
VALUES (2,'Product 2',3);
INSERT INTO pricing_products (product_id, price)
VALUES (2,200);