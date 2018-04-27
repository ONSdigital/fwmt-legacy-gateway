SET SCHEMA 'gateway';

ALTER TABLE gateway.sample
    DROP CONSTRAINT sample_pkey,
    ADD COLUMN primarykey BIGINT PRIMARY KEY,
    ADD COLUMN legacyjobid CHARACTER VARYING(30);
    
COMMENT ON COLUMN sample.legacyjobid IS 'Concat of Serno + Fp + Tla, used to detect reallocation of jobs';

ALTER TABLE gateway.jobs
    RENAME COLUMN serno TO legacyjobid;
    
COMMENT ON COLUMN jobs.legacyjobid IS 'Concat of Serno + Fp + Tla, used to detect reallocation of jobs';