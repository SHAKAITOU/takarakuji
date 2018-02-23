-- Table: public.m_number_s4

-- DROP TABLE public.m_number_s4;

CREATE TABLE public.m_number_s4
(
    turn integer NOT NULL,
    open_dt character varying(10) COLLATE pg_catalog."default" NOT NULL,
    l1 integer NOT NULL,
    l2 integer NOT NULL,
    l3 integer NOT NULL,
    l4 integer NOT NULL,
    CONSTRAINT m_number_s4_pkey PRIMARY KEY (turn)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.m_number_s4
    OWNER to postgres;