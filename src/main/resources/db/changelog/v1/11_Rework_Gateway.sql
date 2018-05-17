SET SCHEMA 'gateway';

DROP TABLE gateway.staff CASCADE;
DROP TABLE gateway.users CASCADE;
DROP TABLE gateway.leavers CASCADE;
DROP TABLE gateway.jobs CASCADE;

CREATE TABLE gateway.tm_jobs (
    id         bigserial PRIMARY KEY,
    tm_job_id  varchar(40) UNIQUE
);

CREATE TABLE gateway.tm_users (
    id           bigserial PRIMARY KEY,
    auth_no      varchar(40) UNIQUE,
    tm_username  varchar(40) NOT NULL,
    active       boolean
);

CREATE TABLE gateway.field_periods (
    id            bigserial PRIMARY KEY,
    start_date    date UNIQUE NOT NULL,
    end_date      date UNIQUE NOT NULL,
    field_period  varchar(5)
);
