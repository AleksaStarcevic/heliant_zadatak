-- liquibase formatted sql

-- changeset aleksa:3
CREATE TABLE field (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       id_form INT NOT NULL,
                       name VARCHAR(256) NOT NULL,
                       display_order INT NOT NULL,
                       type ENUM('text','number') NOT NULL,
                       created_date TIMESTAMP,
                       last_modified_date TIMESTAMP,
                       CONSTRAINT FOREIGN KEY (id_form) REFERENCES form (id)
);
