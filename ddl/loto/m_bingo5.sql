-- Table: public.m_bingo5

-- DROP TABLE public.m_bingo5;

CREATE TABLE public.m_bingo5
(
    turn integer NOT NULL,
    open_dt character varying(10) COLLATE pg_catalog."default" NOT NULL,
    l1 integer NOT NULL,
    l2 integer NOT NULL,
    l3 integer NOT NULL,
    l4 integer NOT NULL,
    l5 integer NOT NULL,
    l6 integer NOT NULL,
    l7 integer NOT NULL,
    l8 integer NOT NULL,
    CONSTRAINT m_bingo5_pkey PRIMARY KEY (turn)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.m_bingo5
    OWNER to postgres;