SET SCHEMA 'gateway';

ALTER TABLE gateway.tm_jobs
    ADD COLUMN last_auth_no varchar(40);
