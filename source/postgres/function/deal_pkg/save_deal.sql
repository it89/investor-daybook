-- FUNCTION: deal_pkg.save_deal(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint)

-- DROP FUNCTION deal_pkg.save_deal(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint);

CREATE OR REPLACE FUNCTION deal_pkg.save_deal(
	p_id bigint,
	p_security_id bigint,
	p_deal_number character varying,
	p_date_time character varying,
	p_trade_operation_code character varying,
	p_amount bigint,
	p_volume numeric,
	p_commission numeric,
	p_app_user_id bigint)
    RETURNS bigint
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 0
AS $BODY$
DECLARE
  v_id bigint;
  v_trade_operation_id bigint;
BEGIN
  SELECT o.id
    INTO v_trade_operation_id
    FROM public.trade_operation o
   WHERE upper(o.code) = upper(p_trade_operation_code);
   
  IF p_id is not null THEN
    v_id := p_id;
  ELSE
    v_id := deal_pkg.find_deal_id_by_deal_number(p_deal_number, p_app_user_id);
  END IF;

  IF v_id is null THEN
    v_id := nextval('deal_id_seq');
    INSERT INTO public.deal
        (id, 
         security_id, 
         deal_number, 
         date_time, 
         trade_operation_id, 
         amount, 
         volume, 
         commission, 
         app_user_id)
      VALUES (v_id, 
	      p_security_id, 
	      p_deal_number, 
	      p_date_time, 
	      v_trade_operation_id, 
	      p_amount, 
	      p_volume, 
	      p_commission, 
	      p_app_user_id);
  ELSE
    UPDATE public.deal
       SET security_id = p_security_id, 
           deal_number = p_deal_number, 
           date_time = p_date_time, 
           trade_operation_id = v_trade_operation_id, 
           amount = p_amount, 
           volume = p_volume, 
           commission = p_commission, 
           app_user_id = p_app_user_id
	WHERE id = v_id;
  END IF;
  RETURN v_id;
END

$BODY$;

ALTER FUNCTION deal_pkg.save_deal(bigint, bigint, character varying, character varying, character varying, bigint, numeric, numeric, bigint)
    OWNER TO postgres;
