-- View: public.deal_stock_v

-- DROP VIEW public.deal_stock_v;

CREATE OR REPLACE VIEW public.deal_stock_v AS
 SELECT d.id,
    d.security_id,
    d.deal_number,
    d.date_time,
    d.trade_operation_id,
    d.amount,
    d.volume,
    d.commission,
    d.app_user_id,
    ds.price
   FROM deal d,
    deal_stock ds
  WHERE d.id = ds.deal_id;

ALTER TABLE public.deal_stock_v
    OWNER TO postgres;

