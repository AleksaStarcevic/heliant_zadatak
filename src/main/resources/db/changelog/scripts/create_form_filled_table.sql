-- liquibase formatted sql

-- changeset aleksa:2
CREATE TABLE form_filled (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             id_form INT NOT NULL,
                             created_date TIMESTAMP,
                             last_modified_date TIMESTAMP,
                             CONSTRAINT FOREIGN KEY (id_form) REFERENCES form (id)
);
