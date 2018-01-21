CREATE SEQUENCE public.app_user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.app_user_id_seq
    OWNER TO postgres;