-- liquibase formatted sql

-- changeset aleksa:4
CREATE TABLE field_filled (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              id_form_filled INT NOT NULL,
                              id_field INT NOT NULL,
                              value_text VARCHAR(256),
                              value_number DOUBLE,
                              created_date TIMESTAMP,
                              last_modified_date TIMESTAMP,
                              CONSTRAINT FOREIGN KEY (id_form_filled) REFERENCES form_filled (id),
                              CONSTRAINT FOREIGN KEY (id_field) REFERENCES field (id)
);
