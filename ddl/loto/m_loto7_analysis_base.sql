-- Table: public.m_loto7_analysis_base

-- DROP TABLE public.m_loto7_analysis_base;

CREATE TABLE public.m_loto7_analysis_base
(
    turn integer NOT NULL,
    max_num integer NOT NULL,
    min_num integer NOT NULL,
    max_min_diff integer NOT NULL,
    total_avg integer NOT NULL,
    max_num_diff integer NOT NULL,
    min_num_diff integer NOT NULL,
    num_diff_avg integer NOT NULL,
    even_num_cnt integer NOT NULL,
    odd_num_cnt integer NOT NULL,
    serial_num_cnt integer NOT NULL,
    left_area_num_cnt integer NOT NULL,
    center_area_num_cnt integer NOT NULL,
    right_area_num_cnt integer NOT NULL,
    CONSTRAINT m_loto7_analysis_base_pkey PRIMARY KEY (turn)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.m_loto7_analysis_base
    OWNER to postgres;