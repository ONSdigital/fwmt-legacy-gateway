SET SCHEMA 'gateway';

CREATE TABLE gateway.jobs
 (
    tmJobId            CHARACTER VARYING(255) PRIMARY KEY,
    serno              CHARACTER VARYING(255) UNIQUE,
    state              CHARACTER VARYING(255),
    initalTimestamp    CHARACTER VARYING(255),
    sentTimestamp      CHARACTER VARYING(255),
    processedTimestamp CHARACTER VARYING(255),
    erroredTimestamp   CHARACTER VARYING(255)
 );
