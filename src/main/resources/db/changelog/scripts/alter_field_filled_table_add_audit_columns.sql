-- liquibase formatted sql

-- changeset aleksa:12
ALTER table field_filled
ADD COLUMN id_created_by INT,
ADD COLUMN id_last_modified_by INT;