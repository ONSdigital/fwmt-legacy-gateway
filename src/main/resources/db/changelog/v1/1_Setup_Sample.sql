SET SCHEMA 'gateway';

CREATE TABLE gateway.sample
 (
    serno          CHARACTER VARYING(255) PRIMARY KEY,
    tla            CHARACTER VARYING(255),
    stage          CHARACTER VARYING(255),
    quota          CHARACTER VARYING(255),
    authno         CHARACTER VARYING(255),
    employeeno     CHARACTER VARYING(255),
    addressline1   CHARACTER VARYING(255),
    addressline2   CHARACTER VARYING(255),
    addressline3   CHARACTER VARYING(255),
    addressline4   CHARACTER VARYING(255),
    district       CHARACTER VARYING(255),
    posttown       CHARACTER VARYING(255),
    postcode       CHARACTER VARYING(255),
    addressno      CHARACTER VARYING(255),
    osgridref      CHARACTER VARYING(255),
    kishgrid       CHARACTER VARYING(255),
 );
