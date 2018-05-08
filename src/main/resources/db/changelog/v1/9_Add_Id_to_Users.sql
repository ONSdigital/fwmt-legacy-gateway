SET SCHEMA 'gateway';

ALTER TABLE gateway.users
    DROP CONSTRAINT users_pkey,
    ADD COLUMN id BIGSERIAL PRIMARY KEY;

