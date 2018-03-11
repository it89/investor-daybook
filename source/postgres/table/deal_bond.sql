-- Table: public.deal_bond

-- DROP TABLE public.deal_bond;

CREATE TABLE public.deal_bond
(
    id bigint NOT NULL,
    price_pct numeric NOT NULL,
    accumulated_coupon_yield numeric NOT NULL,
    CONSTRAINT deal_bond_pkey PRIMARY KEY (id),
    CONSTRAINT deal_bond_deal_id_fkey FOREIGN KEY (id)
        REFERENCES public.deal (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.deal_bond
    OWNER to postgres;