-- Table: public.survey_user

-- DROP TABLE public.survey_user;

CREATE TABLE public.survey_user
(
    id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT name_email_unique UNIQUE (name, email)
)

TABLESPACE pg_default;

ALTER TABLE public.survey_user
    OWNER to postgres;