-- Table: public.deal

-- DROP TABLE public.deal;

CREATE TABLE public.deal
(
    id bigint NOT NULL DEFAULT nextval('deal_id_seq'::regclass),
    security_id bigint NOT NULL,
    deal_number character varying(100) COLLATE pg_catalog."default" NOT NULL,
    date_time character varying(100) COLLATE pg_catalog."default" NOT NULL,
    trade_operation_id bigint NOT NULL,
    amount bigint NOT NULL,
    volume numeric NOT NULL,
    commission numeric NOT NULL,
    app_user_id bigint NOT NULL,
    CONSTRAINT deal_pkey PRIMARY KEY (id),
    CONSTRAINT deal_security_id_fkey FOREIGN KEY (security_id)
        REFERENCES public.security (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT deal_trade_operation_id_fkey FOREIGN KEY (trade_operation_id)
        REFERENCES public.trade_operation (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.deal
    OWNER to postgres;

-- Index: deal_app_user_id_idx

-- DROP INDEX public.deal_app_user_id_idx;

CREATE INDEX deal_app_user_id_idx
    ON public.deal USING btree
    (app_user_id)
    TABLESPACE pg_default;

-- Index: deal_deal_number_idx

-- DROP INDEX public.deal_deal_number_idx;

CREATE INDEX deal_deal_number_idx
    ON public.deal USING btree
    (upper(deal_number::text) COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: deal_security_id_idx

-- DROP INDEX public.deal_security_id_idx;

CREATE INDEX deal_security_id_idx
    ON public.deal USING btree
    (security_id)
    TABLESPACE pg_default;

-- Index: deal_trade_operation_id_idx

-- DROP INDEX public.deal_trade_operation_id_idx;

CREATE INDEX deal_trade_operation_id_idx
    ON public.deal USING btree
    (trade_operation_id)
    TABLESPACE pg_default;