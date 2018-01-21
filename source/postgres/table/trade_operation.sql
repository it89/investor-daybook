-- Table: public.trade_operation

-- DROP TABLE public.trade_operation;

CREATE TABLE public.trade_operation
(
    id bigint NOT NULL DEFAULT nextval('trade_operation_id_seq'::regclass),
    code character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT trade_operation_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.trade_operation
    OWNER to postgres;

-- Index: trade_operation_code_idx

-- DROP INDEX public.trade_operation_code_idx;

CREATE UNIQUE INDEX trade_operation_code_idx
    ON public.trade_operation USING btree
    (upper(code::text) COLLATE pg_catalog."default")
    TABLESPACE pg_default;