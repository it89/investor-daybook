-- View: public.deal_stock_v

-- DROP VIEW public.deal_stock_v;

CREATE OR REPLACE VIEW public.deal_stock_v AS 
 SELECT d.id,
    d.id_security,
    d.deal_number,
    d.date_time,
    d.id_trade_operation,
    d.amount,
    d.volume,
    d.commission,
    d.id_app_user,
    ds.price
   FROM deal d,
    deal_stock ds
  WHERE d.id = ds.id_deal;

ALTER TABLE public.deal_stock_v
  OWNER TO postgres;
