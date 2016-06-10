/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mi
 * Created: 10/06/2016
 */

 
CREATE DATABASE postgres
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE postgres
  IS 'default administrative connection database';

CREATE TABLE public.filme
(
  codigo integer,
  nome character varying(50),
  genero character varying(15),
  sinopse character varying(60)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.filme
  OWNER TO postgres;

CREATE TABLE public.sala
(
  numero integer,
  assento integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.sala
  OWNER TO postgres;

CREATE TABLE public.secao
(
  codigo integer,
  numsala integer,
  horario character varying(10),
  codfilme integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.secao
  OWNER TO postgres;

CREATE TABLE public.venda
(
  codsecao integer,
  quantidade integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.venda
  OWNER TO postgres;






