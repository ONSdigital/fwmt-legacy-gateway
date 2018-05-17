SET SCHEMA 'gateway';

ALTER TABLE gateway.tm_jobs
    ADD COLUMN last_auth_no varchar(40);

ALTER TABLE gateway.tm_users
    ADD COLUMN alternate_auth_no varchar(40);
