-- TODO REFACTOR SO THIS SCRIPT DOES NOT EXIST.
-- Spring automatically creates a schema and orders it alphabetically.


DROP TABLE IF EXISTS medicine;
CREATE TABLE public.medicine (
    id integer NOT NULL,
    name character varying(5000),
    active_ingredient character varying(5000),
    pharmaceutical_form character varying(5000),
    strength character varying(5000),
    atc_code character varying(5000),
    legal_status character varying(5000),
    mah character varying(5000),
    other_info character varying(8000),
    marketed character varying(5000),
    ma_issued character varying(5000)
);