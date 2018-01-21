-- Table: public.deal_stock

-- DROP TABLE public.deal_stock;

CREATE TABLE public.deal_stock
(
    deal_id bigint NOT NULL,
    price numeric NOT NULL,
    CONSTRAINT deal_stock_pkey PRIMARY KEY (deal_id),
    CONSTRAINT deal_stock_deal_id_fkey FOREIGN KEY (deal_id)
        REFERENCES public.deal (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.deal_stock
    OWNER to postgres;