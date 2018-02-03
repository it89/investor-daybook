-- FUNCTION: stored_report_xml_pkg.save(bigint, bigint, character varying, text)

-- DROP FUNCTION stored_report_xml_pkg.save(bigint, bigint, character varying, text);

CREATE OR REPLACE FUNCTION stored_report_xml_pkg.save(
	p_id bigint,
	p_app_user_id bigint,
	p_filename character varying,
	p_text text)
    RETURNS bigint
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    ROWS 0
AS $BODY$
DECLARE
  v_id bigint;
BEGIN
  IF p_id is not null THEN
    v_id := p_id;
    UPDATE public.stored_report_xml
    SET app_user_id = p_app_user_id,
      filename = p_filename,
      text = p_text
    WHERE id = v_id;
  ELSE
    v_id := nextval('stored_report_xml_id_seq');
    INSERT INTO stored_report_xml
    (id, app_user_id, filename, text)
    VALUES (
      v_id, p_app_user_id, p_filename, p_text);
  END IF;

  return v_id;
END;


$BODY$;

ALTER FUNCTION stored_report_xml_pkg.save(bigint, bigint, character varying, text)
    OWNER TO postgres;
