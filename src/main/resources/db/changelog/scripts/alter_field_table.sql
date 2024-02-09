-- liquibase formatted sql

-- changeset aleksa:8
ALTER TABLE field
MODIFY COLUMN type ENUM('TEXT','NUMBER') NOT NULL;
