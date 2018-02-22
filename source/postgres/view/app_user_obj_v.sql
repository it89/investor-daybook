-- View: public.app_user_obj_v

-- DROP VIEW public.app_user_obj_v;

CREATE OR REPLACE VIEW public.app_user_obj_v AS
 SELECT u.id AS app_user_id,
    u.login,
    u.password
   FROM app_user u;

ALTER TABLE public.app_user_obj_v
    OWNER TO postgres;

