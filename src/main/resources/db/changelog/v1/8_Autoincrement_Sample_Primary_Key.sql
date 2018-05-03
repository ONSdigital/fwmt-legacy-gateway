
CREATE SEQUENCE sample_primary_key_seq;
ALTER TABLE sample ALTER COLUMN primary_key SET NOT NULL;
ALTER TABLE sample ALTER COLUMN primary_key SET DEFAULT nextval('sample_primary_key_seq');
ALTER SEQUENCE sample_primary_key_seq OWNED BY sample.primary_key;
