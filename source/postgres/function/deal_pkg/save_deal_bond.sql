-- FUNCTION: deal_pkg.save_deal_bond(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint, numeric, numeric)

-- DROP FUNCTION deal_pkg.save_deal_bond(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint, numeric, numeric);

CREATE OR REPLACE FUNCTION deal_pkg.save_deal_bond(
	p_id bigint,
	p_security_id bigint,
	p_deal_number character varying,
	p_date_time character varying,
	p_trade_operation_code character varying,
	p_amount bigint,
	p_volume numeric,
	p_commission numeric,
	p_app_user_id bigint,
	p_price_pct numeric,
	p_accumulated_coupon_yield numeric)
    RETURNS bigint
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 0
AS $BODY$
DECLARE
  v_deal_id bigint;
  count bigint;
BEGIN
  v_deal_id:= deal_pkg.save_deal(p_id, p_security_id, p_deal_number, p_date_time, p_trade_operation_code, p_amount, p_volume, p_commission, p_app_user_id);

  SELECT count(1)
  INTO count
  FROM public.deal_bond ds
  WHERE ds.deal_id = v_deal_id;

  IF count = 0 THEN
    INSERT INTO public.deal_bond
    (deal_id, price_pct, accumulated_coupon_yield)
    VALUES (v_deal_id, p_price_pct, p_accumulated_coupon_yield);
  ELSE
    UPDATE public.deal_bond
    SET price_pct = p_price_pct,
      accumulated_coupon_yield = p_accumulated_coupon_yield
    WHERE deal_id = v_deal_id;
  END IF;
  
  DELETE FROM public.deal_stock ds
   WHERE ds.deal_id = v_deal_id;

  RETURN v_deal_id;
END;

$BODY$;

ALTER FUNCTION deal_pkg.save_deal_bond(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint, numeric, numeric)
    OWNER TO postgres;
