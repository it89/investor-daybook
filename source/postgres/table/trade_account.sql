-- Table: public.trade_account

-- DROP TABLE public.trade_account;

CREATE TABLE public.trade_account
(
    id bigint NOT NULL DEFAULT nextval('trade_account_id_seq'::regclass),
    version integer NOT NULL,
    code character varying(100) COLLATE pg_catalog."default" NOT NULL,
    app_user_id bigint NOT NULL,
    CONSTRAINT trade_account_pkey PRIMARY KEY (id),
    CONSTRAINT trade_account_app_user_id_code_key UNIQUE (app_user_id, code),
    CONSTRAINT trade_account_app_user_id_fkey FOREIGN KEY (app_user_id)
        REFERENCES public.app_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.trade_account
    OWNER to postgres;

-- Index: trade_account_app_user_id_code_idx

-- DROP INDEX public.trade_account_app_user_id_code_idx;

CREATE UNIQUE INDEX trade_account_app_user_id_code_idx
    ON public.trade_account USING btree
    (app_user_id, code COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: trade_account_app_user_id_idx

-- DROP INDEX public.trade_account_app_user_id_idx;

CREATE INDEX trade_account_app_user_id_idx
    ON public.trade_account USING btree
    (app_user_id)
    TABLESPACE pg_default;