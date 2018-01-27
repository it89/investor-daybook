-- View: public.deal_bond_v

-- DROP VIEW public.deal_bond_v;

CREATE OR REPLACE VIEW public.deal_bond_v AS
 SELECT d.id,
    d.security_id,
    d.deal_number,
    d.date_time,
    d.trade_operation_id,
    d.amount,
    d.volume,
    d.commission,
    d.app_user_id,
    ds.price_pct,
    ds.accumulated_coupon_yield
   FROM deal d,
    deal_bond ds
  WHERE d.id = ds.deal_id;

ALTER TABLE public.deal_bond_v
    OWNER TO postgres;

