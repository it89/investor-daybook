-- Table: public.stored_report_xml

-- DROP TABLE public.stored_report_xml;

CREATE TABLE public.stored_report_xml
(
    id bigint NOT NULL DEFAULT nextval('stored_report_xml_id_seq'::regclass),
    app_user_id bigint NOT NULL,
    filename character varying(500) COLLATE pg_catalog."default" NOT NULL,
    text text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT stored_report_xml_pkey PRIMARY KEY (id),
    CONSTRAINT stored_report_xml_app_user_id_fkey FOREIGN KEY (app_user_id)
        REFERENCES public.app_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.stored_report_xml
    OWNER to postgres;

-- Index: stored_report_xml_app_user_idx

-- DROP INDEX public.stored_report_xml_app_user_idx;

CREATE INDEX stored_report_xml_app_user_idx
    ON public.stored_report_xml USING btree
    (app_user_id)
    TABLESPACE pg_default;