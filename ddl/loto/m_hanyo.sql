-- Table: public.m_hanyo

-- DROP TABLE public.m_hanyo;

CREATE TABLE public.m_hanyo
(
    id integer NOT NULL,
    code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    value character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT m_hanyo_pkey PRIMARY KEY (id, code)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.m_hanyo
    OWNER to postgres;