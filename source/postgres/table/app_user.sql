-- Table: public.app_user

-- DROP TABLE public.app_user;

CREATE TABLE public.app_user
(
    id bigint NOT NULL DEFAULT nextval('app_user_id_seq'::regclass),
    login character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT app_user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.app_user
    OWNER to postgres;

-- Index: app_user_login_idx

-- DROP INDEX public.app_user_login_idx;

CREATE UNIQUE INDEX app_user_login_idx
    ON public.app_user USING btree
    (upper(login::text) COLLATE pg_catalog."default")
    TABLESPACE pg_default;