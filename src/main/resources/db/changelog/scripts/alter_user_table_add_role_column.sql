-- liquibase formatted sql

-- changeset aleksa:16
ALTER TABLE user
ADD COLUMN role_id INT NOT NULL,
ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role (id);