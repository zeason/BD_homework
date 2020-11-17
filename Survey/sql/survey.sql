-- Table: public.survey

-- DROP TABLE public.survey;

CREATE TABLE public.survey
(
    id integer NOT NULL DEFAULT nextval('survey_id_seq'::regclass),
    creator integer NOT NULL DEFAULT nextval('survey_creator_seq'::regclass),
    title text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT survey_pkey PRIMARY KEY (id),
    CONSTRAINT creator FOREIGN KEY (creator)
        REFERENCES public.survey_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.survey
    OWNER to postgres;