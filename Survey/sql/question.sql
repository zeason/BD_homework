-- Table: public.question

-- DROP TABLE public.question;

CREATE TABLE public.question
(
    id integer NOT NULL DEFAULT nextval('question_id_seq'::regclass),
    title text COLLATE pg_catalog."default" NOT NULL,
    order_num integer NOT NULL,
    survey_id integer NOT NULL DEFAULT nextval('question_survey_id_seq'::regclass),
    CONSTRAINT question_pkey PRIMARY KEY (id),
    CONSTRAINT survey_id FOREIGN KEY (survey_id)
        REFERENCES public.survey (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.question
    OWNER to postgres;