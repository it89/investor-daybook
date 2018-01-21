-- Table: public.security

-- DROP TABLE public.security;

CREATE TABLE public.security
(
    id bigint NOT NULL DEFAULT nextval('security_id_seq'::regclass),
    isin character varying(100) COLLATE pg_catalog."default" NOT NULL,
    ticker character varying(100) COLLATE pg_catalog."default" NOT NULL,
    caption character varying(500) COLLATE pg_catalog."default" NOT NULL,
    code_grn character varying(100) COLLATE pg_catalog."default",
    app_user_id bigint NOT NULL,
    security_type_id bigint NOT NULL,
    CONSTRAINT security_pkey PRIMARY KEY (id),
    CONSTRAINT security_app_user_id_fkey FOREIGN KEY (app_user_id)
        REFERENCES public.app_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT security_security_type_id_fkey FOREIGN KEY (security_type_id)
        REFERENCES public.security_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.security
    OWNER to postgres;

-- Index: security_app_user_id_idx

-- DROP INDEX public.security_app_user_id_idx;

CREATE INDEX security_app_user_id_idx
    ON public.security USING btree
    (app_user_id)
    TABLESPACE pg_default;

-- Index: security_isin_idx

-- DROP INDEX public.security_isin_idx;

CREATE INDEX security_isin_idx
    ON public.security USING btree
    (upper(isin::text) COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: security_security_type_id_idx

-- DROP INDEX public.security_security_type_id_idx;

CREATE INDEX security_security_type_id_idx
    ON public.security USING btree
    (security_type_id)
    TABLESPACE pg_default;