Download Apache POI Library from https://www.apache.org/dyn/closer.lua/poi/release/bin/poi-bin-5.2.3-20220909.zip


https://www.scientecheasy.com/2018/12/apache-poi-tutorial.html/

-- Table: public.product

-- DROP TABLE IF EXISTS public.product;

CREATE TABLE IF NOT EXISTS public.product
(
    availability boolean NOT NULL,
    color character varying(255) COLLATE pg_catalog."default",
    gender_recommendation character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    price double precision NOT NULL,
    product_id character varying(255) COLLATE pg_catalog."default",
    rating double precision NOT NULL,
    size character varying(255) COLLATE pg_catalog."default",
    id bigint NOT NULL DEFAULT nextval('product_id_seq'::regclass)
)


    INSERT INTO public.product(
	id, availability, color, gender_recommendation, name, price, product_id, rating, size)
	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);

    UPDATE public.product
	SET id=?, availability=?, color=?, gender_recommendation=?, name=?, price=?, product_id=?, rating=?, size=?
	WHERE <condition>;