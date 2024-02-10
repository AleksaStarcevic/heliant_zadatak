-- liquibase formatted sql

-- changeset aleksa:10
ALTER table form_filled
ADD COLUMN id_created_by INT,
ADD COLUMN id_last_modified_by INT;