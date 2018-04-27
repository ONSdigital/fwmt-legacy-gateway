SET SCHEMA 'gateway';

CREATE TABLE gateway.sample
 (
    serno          CHARACTER VARYING(255) PRIMARY KEY,
    tla            CHARACTER VARYING(255),
    fp             CHARACTER VARYING(255),
    quota          CHARACTER VARYING(255),
    authno         CHARACTER VARYING(255),
    employeeno     CHARACTER VARYING(255),

    addressline1   CHARACTER VARYING(255),
    addressline2   CHARACTER VARYING(255),
    addressline3   CHARACTER VARYING(255),
    addressline4   CHARACTER VARYING(255),
    addressno      CHARACTER VARYING(255),
    district       CHARACTER VARYING(255),
    posttown       CHARACTER VARYING(255),
    postcode       CHARACTER VARYING(255),
    osgridref      CHARACTER VARYING(255),

    week           CHARACTER VARYING(255),
    w1yr           CHARACTER VARYING(255),
    qrtr           CHARACTER VARYING(255),
    wavfnd         CHARACTER VARYING(255),
    hhld           CHARACTER VARYING(255),
    chklet         CHARACTER VARYING(255),

    adult2         CHARACTER VARYING(255),
    adult3         CHARACTER VARYING(255),
    adult4         CHARACTER VARYING(255),
    adult5         CHARACTER VARYING(255),
    adult6         CHARACTER VARYING(255),
    adult7         CHARACTER VARYING(255),
    adult8         CHARACTER VARYING(255),
    adult9         CHARACTER VARYING(255),
    adult10        CHARACTER VARYING(255),
    adult11        CHARACTER VARYING(255),
    adult12        CHARACTER VARYING(255),
    adult13        CHARACTER VARYING(255),
    adult14        CHARACTER VARYING(255)
 );
