-- FUNCTION: deal_pkg.save_deal_stock(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint, numeric)

-- DROP FUNCTION deal_pkg.save_deal_stock(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint, numeric);

CREATE OR REPLACE FUNCTION deal_pkg.save_deal_stock(
	p_id bigint,
	p_security_id bigint,
	p_deal_number character varying,
	p_date_time character varying,
	p_trade_operation_code character varying,
	p_amount bigint,
	p_volume numeric,
	p_commission numeric,
	p_app_user_id bigint,
	p_price numeric)
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
    FROM public.deal_stock ds
   WHERE ds.deal_id = v_deal_id;

  IF count = 0 THEN
    INSERT INTO public.deal_stock
      (deal_id, price)
      VALUES (v_deal_id, p_price);
  ELSE
    UPDATE public.deal_stock
       SET price = p_price
     WHERE deal_id = v_deal_id;
  END IF;
  
  DELETE FROM public.deal_bond ds
   WHERE ds.deal_id = v_deal_id;
  
  RETURN v_deal_id;
END;

$BODY$;

ALTER FUNCTION deal_pkg.save_deal_stock(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint, numeric)
    OWNER TO postgres;
