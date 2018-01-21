-- Table: public.security_type

-- DROP TABLE public.security_type;

CREATE TABLE public.security_type
(
    id bigint NOT NULL DEFAULT nextval('security_type_id_seq'::regclass),
    code character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT security_type_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.security_type
    OWNER to postgres;

-- Index: security_type_code_idx

-- DROP INDEX public.security_type_code_idx;

CREATE UNIQUE INDEX security_type_code_idx
    ON public.security_type USING btree
    (upper(code::text) COLLATE pg_catalog."default")
    TABLESPACE pg_default;