SET SCHEMA 'gateway';

CREATE TABLE gateway.users
 (
    authNo         CHARACTER VARYING(255) UNIQUE,
    tmUsername     CHARACTER VARYING(255) PRIMARY KEY
 );
