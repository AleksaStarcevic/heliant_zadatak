-- liquibase formatted sql

-- changeset aleksa:14
CREATE TABLE role (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(30) NOT NULL
);
