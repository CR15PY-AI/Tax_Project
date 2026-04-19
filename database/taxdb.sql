--
-- PostgreSQL database dump
--

\restrict UF6dd9McQXBtT0x9SSQBlfI7fe91eUWY4SrW1D7kaWzHk5mYym9oAW7TU3Bsd6E

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2026-04-19 23:32:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 24590)
-- Name: tax_forms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tax_forms (
    id integer NOT NULL,
    user_id integer,
    income double precision,
    tax double precision,
    status text,
    tax_type character varying(20)
);


ALTER TABLE public.tax_forms OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24589)
-- Name: tax_forms_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tax_forms_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tax_forms_id_seq OWNER TO postgres;

--
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 221
-- Name: tax_forms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tax_forms_id_seq OWNED BY public.tax_forms.id;


--
-- TOC entry 220 (class 1259 OID 24578)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name text,
    email text,
    password text,
    role text
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24577)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 4926 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 4761 (class 2604 OID 24593)
-- Name: tax_forms id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax_forms ALTER COLUMN id SET DEFAULT nextval('public.tax_forms_id_seq'::regclass);


--
-- TOC entry 4760 (class 2604 OID 24581)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 4919 (class 0 OID 24590)
-- Dependencies: 222
-- Data for Name: tax_forms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tax_forms (id, user_id, income, tax, status, tax_type) FROM stdin;
14	2	50000	6500	REJECTED	\N
15	2	20000	2600	APPROVED	INDIVIDUAL
16	2	11112	2778	REJECTED	OSOO
17	2	45500	11375	DRAFT	OSOO
\.


--
-- TOC entry 4917 (class 0 OID 24578)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, email, password, role) FROM stdin;
4	headadmin	admin@gmail.com	12345	ADMIN
2	azamat	a.azamat2007@mail.ru	12345	USER
\.


--
-- TOC entry 4927 (class 0 OID 0)
-- Dependencies: 221
-- Name: tax_forms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tax_forms_id_seq', 17, true);


--
-- TOC entry 4928 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 4, true);


--
-- TOC entry 4767 (class 2606 OID 24598)
-- Name: tax_forms tax_forms_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax_forms
    ADD CONSTRAINT tax_forms_pkey PRIMARY KEY (id);


--
-- TOC entry 4763 (class 2606 OID 24588)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4765 (class 2606 OID 24586)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4768 (class 2606 OID 24599)
-- Name: tax_forms tax_forms_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tax_forms
    ADD CONSTRAINT tax_forms_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2026-04-19 23:32:24

--
-- PostgreSQL database dump complete
--

\unrestrict UF6dd9McQXBtT0x9SSQBlfI7fe91eUWY4SrW1D7kaWzHk5mYym9oAW7TU3Bsd6E

