-- Table: public.d_users

-- DROP TABLE public.d_users;

CREATE TABLE public.d_users
(
    id integer NOT NULL,
    code character varying(40) COLLATE pg_catalog."default" NOT NULL,
    name character varying(40) COLLATE pg_catalog."default",
    pw character varying(40) COLLATE pg_catalog."default" NOT NULL,
    valid integer NOT NULL,
    CONSTRAINT d_users_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.d_users
    OWNER to postgres;
    
CREATE INDEX IF NOT EXISTS d_users_index1 ON public.d_users (code);