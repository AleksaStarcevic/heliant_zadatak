-- liquibase formatted sql

-- changeset aleksa:1
CREATE TABLE form (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(256) NOT NULL,
                      created_date TIMESTAMP,
                      last_modified_date TIMESTAMP
);
