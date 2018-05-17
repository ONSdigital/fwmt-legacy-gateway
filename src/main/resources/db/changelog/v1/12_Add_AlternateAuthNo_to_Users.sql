SET SCHEMA 'gateway';

ALTER TABLE gateway.tm_users
    ADD COLUMN alternate_auth_no varchar(40);

