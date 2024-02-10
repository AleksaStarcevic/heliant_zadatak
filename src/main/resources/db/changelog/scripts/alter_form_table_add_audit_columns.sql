-- liquibase formatted sql

-- changeset aleksa:9
ALTER table form
ADD COLUMN id_created_by INT,
ADD COLUMN id_last_modified_by INT;