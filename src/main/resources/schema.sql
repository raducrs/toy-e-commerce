-- Users
CREATE TABLE users (
   id INT NOT NULL,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(100) NOT NULL,
   PRIMARY KEY (id)
);

-- Inventory
CREATE TABLE inventory_products (
    product_id BIGINT       NOT NULL,
    name       VARCHAR(255) NULL,
    quantity   INT          NULL,
    CONSTRAINT pk_inventory_products PRIMARY KEY (product_id)
);
-- Pricing
CREATE TABLE pricing_products
(
    product_id BIGINT NOT NULL,
    price      INT    NULL,
    CONSTRAINT pk_pricing_product PRIMARY KEY (product_id)
);
-- Orders
CREATE TABLE orders
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    user_id      BIGINT                NULL,
    order_status INT                   NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);
CREATE TABLE line_item
(
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NULL,
    CONSTRAINT pk_line_item PRIMARY KEY (order_id, product_id)
);
ALTER TABLE line_item
    ADD CONSTRAINT FK_LINE_ITEM_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);
