-- Table: public.answer

-- DROP TABLE public.answer;

CREATE TABLE public.answer
(
    question_id integer NOT NULL DEFAULT nextval('answer_question_id_seq'::regclass),
    option_id integer NOT NULL DEFAULT nextval('answer_option_id_seq'::regclass),
    user_id integer NOT NULL DEFAULT nextval('answer_user_id_seq'::regclass),
    id integer NOT NULL DEFAULT nextval('answer_id_seq'::regclass),
    CONSTRAINT answer_pkey PRIMARY KEY (id),
    CONSTRAINT option_id FOREIGN KEY (option_id)
        REFERENCES public.option (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT question_id FOREIGN KEY (question_id)
        REFERENCES public.question (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES public.survey_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.answer
    OWNER to postgres;