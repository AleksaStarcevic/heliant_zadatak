-- liquibase formatted sql

-- changeset aleksa:6
CREATE TABLE statistics (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      date DATE NOT NULL,
                      number_filled_forms INT NOT NULL
);
