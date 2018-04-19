SET SCHEMA 'gateway';

CREATE TABLE gateway.leavers
 (
    employeeNo     CHARACTER VARYING(255) PRIMARY KEY,
    authNo         CHARACTER VARYING(255) UNIQUE,
    forename       CHARACTER VARYING(255),
    surname        CHARACTER VARYING(255),
    jobTitle       CHARACTER VARYING(255),
    email          CHARACTER VARYING(255),
    phone          CHARACTER VARYING(255),
    userType       CHARACTER VARYING(255)
 );
