-- Table: public.deal

-- DROP TABLE public.deal;

CREATE TABLE public.deal
(
    id bigint NOT NULL DEFAULT nextval('deal_id_seq'::regclass),
    security_id bigint NOT NULL,
    deal_number character varying(100) COLLATE pg_catalog."default" NOT NULL,
    version integer NOT NULL,
    date_time timestamp without time zone NOT NULL,
    operation character varying(100) COLLATE pg_catalog."default" NOT NULL,
    amount bigint NOT NULL,
    volume numeric NOT NULL,
    commission numeric NOT NULL,
    stage integer,
    trade_account_id bigint NOT NULL,
    CONSTRAINT deal_pkey PRIMARY KEY (id),
    CONSTRAINT deal_security_id_fkey FOREIGN KEY (security_id)
        REFERENCES public.security (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT deal_trade_account_id_fkey FOREIGN KEY (trade_account_id)
        REFERENCES public.trade_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.deal
    OWNER to postgres;

-- Index: deal_security_id_idx

-- DROP INDEX public.deal_security_id_idx;

CREATE INDEX deal_security_id_idx
    ON public.deal USING btree
    (security_id)
    TABLESPACE pg_default;

-- Index: deal_trade_account_id_deal_number_idx

-- DROP INDEX public.deal_trade_account_id_deal_number_idx;

CREATE UNIQUE INDEX deal_trade_account_id_deal_number_idx
    ON public.deal USING btree
    (trade_account_id, deal_number COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: deal_trade_account_id_idx

-- DROP INDEX public.deal_trade_account_id_idx;

CREATE INDEX deal_trade_account_id_idx
    ON public.deal USING btree
    (trade_account_id)
    TABLESPACE pg_default;