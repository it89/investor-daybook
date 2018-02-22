-- View: public.deal_bond_obj_v

-- DROP VIEW public.deal_bond_obj_v;

CREATE OR REPLACE VIEW public.deal_bond_obj_v AS
 SELECT db.id AS deal_id,
    db.deal_number,
    db.date_time,
    db.trade_operation_id,
    db.amount,
    db.volume,
    db.commission,
    db.price_pct,
    db.accumulated_coupon_yield,
    o.code AS operation_code,
    s.security_id,
    s.isin,
    s.ticker,
    s.caption,
    s.code_grn,
    s.security_type_code,
    s.app_user_id,
    s.login,
    s.password
   FROM deal_bond_v db,
    trade_operation o,
    security_obj_v s
  WHERE db.trade_operation_id = o.id AND db.security_id = s.security_id;

ALTER TABLE public.deal_bond_obj_v
    OWNER TO postgres;

