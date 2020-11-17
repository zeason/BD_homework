-- Table: public.option

-- DROP TABLE public.option;

CREATE TABLE public.option
(
    id integer NOT NULL DEFAULT nextval('option_id_seq'::regclass),
    content text COLLATE pg_catalog."default" NOT NULL,
    order_num integer NOT NULL,
    question_id integer NOT NULL DEFAULT nextval('option_question_id_seq'::regclass),
    CONSTRAINT option_pkey PRIMARY KEY (id),
    CONSTRAINT question_id FOREIGN KEY (question_id)
        REFERENCES public.question (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.option
    OWNER to postgres;