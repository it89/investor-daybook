-- View: public.security_obj_v

-- DROP VIEW public.security_obj_v;

CREATE OR REPLACE VIEW public.security_obj_v AS
 SELECT s.id AS security_id,
    s.isin,
    s.ticker,
    s.caption,
    s.code_grn,
    st.code AS security_type_code,
    u.app_user_id,
    u.login,
    u.password
   FROM security s,
    security_type st,
    app_user_obj_v u
  WHERE s.security_type_id = st.id AND s.app_user_id = u.app_user_id;

ALTER TABLE public.security_obj_v
    OWNER TO postgres;

