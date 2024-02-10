-- liquibase formatted sql

-- changeset aleksa:11
ALTER table field
ADD COLUMN id_created_by INT,
ADD COLUMN id_last_modified_by INT;