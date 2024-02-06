-- liquibase formatted sql

-- changeset aleksa:5
CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(256) NOT NULL,
                      password VARCHAR(256) NOT NULL,
                      created_date TIMESTAMP,
                      last_modified_date TIMESTAMP
);
