-- FUNCTION: deal_pkg.find_deal_id_by_deal_number(character varying, bigint)

-- DROP FUNCTION deal_pkg.find_deal_id_by_deal_number(character varying, bigint);

CREATE OR REPLACE FUNCTION deal_pkg.find_deal_id_by_deal_number(
	p_deal_number character varying,
	p_app_user_id bigint)
    RETURNS bigint
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 0
AS $BODY$
DECLARE
  v_id bigint;
BEGIN
  SELECT max(d.id)
    INTO v_id
    FROM public.deal d
   WHERE upper(d.deal_number) = upper(p_deal_number)
     AND d.app_user_id = p_app_user_id;
     
  RETURN v_id;
END;

$BODY$;

ALTER FUNCTION deal_pkg.find_deal_id_by_deal_number(character varying, bigint)
    OWNER TO postgres;
