CREATE TABLE product.product (
  id   VARCHAR(36) PRIMARY KEY,              
  name VARCHAR(255) NOT NULL UNIQUE,
  price NUMERIC(10,2) NOT NULL,
  unit VARCHAR(50) NOT NULL
);
