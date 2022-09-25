--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3 (Debian 13.3-1.pgdg100+1)
-- Dumped by pg_dump version 13.3 (Debian 13.3-1.pgdg100+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: elements_recorder_schema; Type: SCHEMA; Schema: -; Owner: elements_recorder_app
--

CREATE SCHEMA elements_recorder_schema;


ALTER SCHEMA elements_recorder_schema OWNER TO elements_recorder_app;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: resource; Type: TABLE; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

CREATE TABLE elements_recorder_schema.resource (
    id uuid NOT NULL,
    name character varying(255) NOT NULL,
    description text NOT NULL,
    rating smallint,
    url character varying(255) NOT NULL,
    user_id uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE elements_recorder_schema.resource OWNER TO elements_recorder_app;

--
-- Name: tag; Type: TABLE; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

CREATE TABLE elements_recorder_schema.tag (
    id uuid NOT NULL,
    name character varying(255) NOT NULL,
    user_id uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE elements_recorder_schema.tag OWNER TO elements_recorder_app;

--
-- Name: tag_resource; Type: TABLE; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

CREATE TABLE elements_recorder_schema.tag_resource (
    tag_id uuid NOT NULL,
    resource_id uuid NOT NULL
);


ALTER TABLE elements_recorder_schema.tag_resource OWNER TO elements_recorder_app;

--
-- Name: user_app; Type: TABLE; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

CREATE TABLE elements_recorder_schema.user_app (
    id uuid NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE elements_recorder_schema.user_app OWNER TO elements_recorder_app;

--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: elements_recorder_app
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO elements_recorder_app;

--
-- Data for Name: resource; Type: TABLE DATA; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

COPY elements_recorder_schema.resource (id, name, description, rating, url, user_id, created_at, updated_at) FROM stdin;
922bafe8-58a8-4cec-b9fd-530f9c9cc96a	Nebular	UI library for Angular, authentication and more	5	https://www.nebular.io	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-05-25 07:29:32.312686	2022-05-25 07:29:32.312691
78489dee-18f2-4cd8-8c5a-8c14a96990d5	My Unit Testing Epiphany	Review of what a unit test should really be	0	https://www.stevefenton.co.uk/2013/05/my-unit-testing-epiphany/	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-07-25 07:36:56.3504	2022-07-25 07:36:56.350405
3b8ca68f-cba0-426f-9573-b123033dc4c8	Review "Ian Cooper TDD: Where did it all go wrong"	A written review of Ian Cooper's presentation	0	https://robdmoore.id.au/blog/2015/01/26/review-of-ian-cooper-tdd-where-did-it-all-go-wrong	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-07-25 07:40:11.718814	2022-07-25 07:40:11.718817
eb208b72-7723-4cd5-8e6b-e9008d5d27ac	Vous ne verrez plus les tests unitaires de la même manière	A great explanation of what unit tests really are and what they should be targeting, from Michael Azerhad	5	https://medium.com/wealcomecompany/vous-ne-verrez-plus-les-tests-unitaires-de-la-meme-maniere-7b0f92c460cb	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-07-25 16:58:47.112563	2022-07-25 16:58:47.112564
b7013037-7ef6-4a36-833a-2cbd2c274195	Cybrary	Cybersecurity school. Contains tons of pathways and courses. 	0	https://www.cybrary.it	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:35:47.108217	2022-08-15 10:35:47.108219
a4f91036-f03f-4ebf-ae4b-38f2cac9c536	Try Hack Me	Platform with tons of machines to hack. Path for beginners to learn cybersecurity. Easy to spin up new machines, no need to install anything locally in my memories	5	https://tryhackme.com/	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:37:12.923018	2022-08-15 10:37:12.923019
87deba9c-8064-4113-bff2-37c1885a85ec	String permutation algorithm	A video explaining how spring permutation works. The recursion explanation is gold. Must see when preparing for interviews or getting better at algorithm	5	https://www.youtube.com/watch?v=nYFd7VHKyWQ&feature=emb_title	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:38:24.229657	2022-08-15 10:38:24.229658
3672b3d1-d5d6-4b8c-8f71-8b6c0fb15f96	Spring Security: Authentication and Authorisation In-Depth	An amazing guide written by Marco Beheld. Explains how Spring Security works and the different layers that are triggered/needed to make authentication and authorisation work.	5	https://www.marcobehler.com/guides/spring-security	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:40:39.322401	2022-08-15 10:40:39.322402
ea61e97e-f07d-40a9-a71d-2ac657e147ee	Dynamic Programming Patterns	Highlights many of the different DP patterns that exist. Must read before interviews. Read a long time ago so unable to give a precise description atm 	0	https://leetcode.com/discuss/general-discussion/458695/Dynamic-Programming-Patterns	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:44:59.397053	2022-08-15 10:44:59.397054
85fca6d6-3965-43d6-8b04-78500230a0b7	Netflix: What Happens When You Press Play?	A great article explaining how the Netflix infrastructure is setup. It's a great read, highlighting some basics as what's a CDN and how it's used.	5	http://highscalability.com/blog/2017/12/11/netflix-what-happens-when-you-press-play.html	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:47:27.372185	2022-08-15 10:47:27.372186
bcd82a67-ae73-4ab4-9660-977b3b60f765	Dynamic Programming for Beginners	A list of DP exercises aimed at beginners. Sorted by categories with some Java examples.	0	https://leetcode.com/discuss/general-discussion/662866/dp-for-beginners-problems-patterns-sample-solutions	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:48:53.981206	2022-08-15 10:48:53.981207
d153fa53-9963-4561-8c0e-5a5dbc3ffcf8	Social Engineering Podcast	A great podcast from Darknet Diaries. This is an interview of a social engineer. 	5	https://podcasts.apple.com/fr/podcast/darknet-diaries/id1296350485?i=1000483131967	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:50:59.486163	2022-08-15 10:50:59.486164
cc6aab07-11c0-4e03-b05d-9a6bdccc283f	Kata logs	Website containing tons of Kata. Never tried one	0	https://kata-log.rocks	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:00:34.608594	2022-08-15 11:00:34.608595
455f0f2a-c5f1-44ef-b765-58637be13e11	Pluralsight: Making your C# code more object-oriented	An incredible course about OOP. It shows the advantages of a real OO programming style vs a declarative one. Must see	5	https://www.pluralsight.com/courses/c-sharp-code-more-object-oriented	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:02:47.364417	2022-08-15 11:02:47.364418
e2876767-426a-45b7-8e11-45cb99c6dc02	Find (and prevent) N+1 queries	A great lib to find and prevent N+1 queries. Can be a great installation at the beginning of the project to prevent any N+1 queries to come up. IIRC, has a strict mode that makes test fail if there's a single N+1 query.	5	https://github.com/quick-perf/quickperf	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:06:39.713893	2022-08-15 11:06:39.713894
36f4769e-14ba-41df-a7a4-210a82d9fd98	System Design Primer	The best available resource to learn about System Designs. Covers all the topics required to create a real world infrastructure (DB, CDN, LB, Proxy…). Must read	5	https://github.com/donnemartin/system-design-primer	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:09:40.229127	2022-08-15 11:09:40.229128
a3d33df8-bb7f-4f28-a46c-cb68d9d082bb	JSON Content Validator	Amazing library allowing you to perform dynamic comparison on JSON response. Must have when writing integration tests in Java	5	https://github.com/ekino/jcv	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:11:19.159063	2022-08-15 11:11:19.159064
cc99a220-934e-4024-b9a4-0f6e01130252	Awesome Java	List of librairies and frameworks available for Java. The list contains tools for everything: messaging, creating REST APIs, testing, caching…	5	https://github.com/akullpp/awesome-java	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:21:59.51669	2022-08-15 11:21:59.51669
55ebc166-feb3-471b-b4c8-aac10e39e0d1	Awesome Scalability	Tons of articles about architecture, pitfalls, langage tuning…	0	https://github.com/binhnguyennus/awesome-scalability	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:02:30.414839	2022-08-15 12:02:30.41484
a3caa8b8-ad39-409f-a9fe-ea0a58d78b47	Budget Chéri: J'arrête de surconsommer	I don't recall why I saved this podcast, but if I did it must contain some value. Might listen to it again one day	0	https://podcasts.apple.com/fr/podcast/budget-cheri/id1483944806?i=1000512186894	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:05:44.281924	2022-08-15 12:05:44.281925
f72df24f-31a3-4dcc-8aff-5eada4bf6b85	Elegant Objects	A list of opinions about what makes a good object in OOP. Can be a useful resource to share with someone	5	https://www.elegantobjects.org/	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:17:04.506274	2022-08-15 12:17:04.506275
9a601b62-5a2b-41e5-afb7-51a248750d71	Modular Monolith with DDD	A C# monolith ready to be split into micro services. This repository has been referenced in a lot of resources I've already read, must have a look at it one day	0	https://github.com/kgrzybek/modular-monolith-with-ddd	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:20:06.30282	2022-08-15 12:20:06.302822
ea816f47-28c9-491c-a0f2-de125996b4dc	How to organise FUD in crypto	A great thread demonstrating how FUD takes place in crypto. Must read	5	https://twitter.com/twocommapauper/status/1397106939193085952	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:21:18.852791	2022-08-15 12:21:18.852792
48aa5571-c7f5-4f92-b934-c1b0484cc0c0	Drunk Post: Things I've learned as a Sr Engineer	Amazing post. Drunk guy that shares his wisdom about software engineering and life. Always a great and relevant read.	5	https://old.reddit.com/r/ExperiencedDevs/comments/nmodyl/drunk_post_things_ive_learned_as_a_sr_engineer/	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:11:24.372843	2022-08-15 13:11:24.372844
ab72de8a-b61c-4c60-894d-e61688dcbaa7	Software Engineering Learning Guide	List of books that any SE should read	5	https://github.com/Thialala/software-engineering-learning-guide	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:14:26.544806	2022-08-15 13:14:26.544807
04ccb8f1-cacb-4d66-8676-c1852a2339da	Polkadot is complicated as hell!	A great thread by Dan Reecer to explain Polkadot (and Kusama). Explains what parachains, crowdloans and auctions are.	5	https://mobile.twitter.com/danreecer_/status/1350933277352341509?lang=fr	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:15:48.484199	2022-08-15 13:15:48.484201
813e6ec5-26fa-4768-87a9-de0ba9499d35	Gasless swap for Polygon	An exchange providing a gasless mode. Tried only once but worked successfully. Could still be useful one day if you lack matic	5	https://exchange.dfyn.network/#/swap	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:18:26.194878	2022-08-15 13:18:26.194879
2fc76728-d145-4e0c-a3b9-5f19729e36e8	Risitas Bitcoin	An incredibly funny video mocking French politicians asking questions about Bitcoin. Backed by Risitas.	5	https://mobile.twitter.com/no_snitchcoin/status/1499324297231446020	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:19:53.060447	2022-08-15 13:19:53.060448
\.


--
-- Data for Name: tag; Type: TABLE DATA; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

COPY elements_recorder_schema.tag (id, name, user_id, created_at, updated_at) FROM stdin;
075ee8a2-3879-49f9-82c4-49597b7fcfa8	K8s	95acb2e9-e9ef-46fd-a66d-475f5d0f5fa1	2022-05-25 07:02:23.056208	2022-05-25 07:02:23.056212
753bd86f-b64d-4beb-b974-4c8d2455e9d7	Angular	95acb2e9-e9ef-46fd-a66d-475f5d0f5fa1	2022-05-25 07:03:15.479112	2022-05-25 07:03:15.479118
8946baaf-6399-4223-b39e-c9b583a8bbd8	Angular	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-05-25 07:29:32.229008	2022-05-25 07:29:32.229011
f4bff9d9-c21b-44b4-8bbe-875cbcff367b	Unit Testing	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-07-25 07:36:56.294614	2022-07-25 07:36:56.294621
865cfaa3-82ea-45ab-b105-098fe46cc95d	TDD	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-07-25 07:36:56.327673	2022-07-25 07:36:56.327678
455b2509-9b0b-46ca-932b-14eb531fce46	Testing	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-07-25 07:36:56.340031	2022-07-25 07:36:56.340037
af0b738b-998f-467f-b6f9-1a1b47faaa0a	Cybersecurity	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:35:47.057035	2022-08-15 10:35:47.057036
8f9790ff-ad2c-4f37-85c2-f38f7ba5fcf1	Courses	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:37:12.908112	2022-08-15 10:37:12.908113
0c3f2528-786f-4e1f-b6bc-b17b96b71e35	Algorithm	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:38:24.203488	2022-08-15 10:38:24.203489
f1d3a627-9a2b-4260-99c6-3f774350d5df	Interview	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:38:24.220278	2022-08-15 10:38:24.220279
f1c34ada-7718-4d95-95a3-2b6ad4edbcea	Spring	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:40:39.301287	2022-08-15 10:40:39.301288
ce76bf31-4b20-41cf-833c-7c416be77c70	Spring Security	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:40:39.313598	2022-08-15 10:40:39.313599
c4989137-de91-425e-b96e-82522b561b26	Dynamic Programming	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:44:59.380414	2022-08-15 10:44:59.380415
48a3ae08-605e-4506-9440-4ac1fca3b717	Design System	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:47:27.333847	2022-08-15 10:47:27.333848
caaa16c4-6384-4a3e-b7dd-cfddfe06d871	Architecture	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:47:27.34823	2022-08-15 10:47:27.348231
b07e99be-9c8f-46fe-bee5-345fadb3e64f	Real Infrastructures	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:47:27.361388	2022-08-15 10:47:27.361389
8754bcaa-c551-4092-b02e-254602173fb8	Social Engineering	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 10:50:59.472947	2022-08-15 10:50:59.472948
b0b48de3-2f15-4d44-8212-13605f6c0937	Kata	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:00:34.59505	2022-08-15 11:00:34.595051
d6f30b3c-63d4-4941-9487-438b7b1a79e5	OOP	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:02:47.353036	2022-08-15 11:02:47.353037
73023870-0db8-469e-8b65-f51d9b402b48	Java	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:06:39.680726	2022-08-15 11:06:39.680727
f2192130-195e-4c4d-b780-370592c9bfb5	Database	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:06:39.693575	2022-08-15 11:06:39.693576
dce67f42-77f7-4a95-9fcc-40947e0cb9a2	Performance	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 11:06:39.705083	2022-08-15 11:06:39.705084
4dbb3d75-ec2f-46bc-b2cc-955b37c44f09	Investing	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:05:44.25144	2022-08-15 12:05:44.251442
69664384-66bf-4121-9555-3cc5b7702bee	Budget	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:05:44.272039	2022-08-15 12:05:44.272041
718d2894-318e-40b4-ac8d-dbfdfde7a187	DDD	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:20:06.235632	2022-08-15 12:20:06.235633
cc7baa3c-f6a0-424f-9540-869839cf2960	Monolith	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:20:06.254723	2022-08-15 12:20:06.254725
4532d70a-d80f-488a-a178-ea162f844c93	Hexagonal Architecture	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:20:06.271775	2022-08-15 12:20:06.271777
4a69822d-7c3b-40d2-bf5d-c670a8f9bc6e	Microservices	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:20:06.290061	2022-08-15 12:20:06.290062
d4d46a16-9fce-4cfe-b90e-9c95ed226bd4	Crypto	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 12:21:18.841959	2022-08-15 12:21:18.841959
4c6c0438-db7e-46bf-8efe-56b436d34f17	Wisdom	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:11:24.331653	2022-08-15 13:11:24.331654
49330bbd-e683-4cd3-ae59-2b98f29748f4	Programming	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:11:24.35487	2022-08-15 13:11:24.354871
a2f4429c-e94d-411b-9934-8373d685cc45	Fun	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:11:24.364898	2022-08-15 13:11:24.364899
a84d1570-5bfd-4b32-9037-8bf5957ad5d3	Books	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:14:26.535705	2022-08-15 13:14:26.535706
961a7a5a-14f3-4776-a284-0511b8321d96	Polkadot	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:15:48.470735	2022-08-15 13:15:48.470737
3bc91ee3-77bd-4dfa-b6f9-a45341c9921f	Polygon	32c1bffc-0bc6-4086-b6a3-934767b81508	2022-08-15 13:18:26.186545	2022-08-15 13:18:26.186546
\.


--
-- Data for Name: tag_resource; Type: TABLE DATA; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

COPY elements_recorder_schema.tag_resource (tag_id, resource_id) FROM stdin;
8946baaf-6399-4223-b39e-c9b583a8bbd8	922bafe8-58a8-4cec-b9fd-530f9c9cc96a
865cfaa3-82ea-45ab-b105-098fe46cc95d	78489dee-18f2-4cd8-8c5a-8c14a96990d5
455b2509-9b0b-46ca-932b-14eb531fce46	78489dee-18f2-4cd8-8c5a-8c14a96990d5
f4bff9d9-c21b-44b4-8bbe-875cbcff367b	78489dee-18f2-4cd8-8c5a-8c14a96990d5
865cfaa3-82ea-45ab-b105-098fe46cc95d	3b8ca68f-cba0-426f-9573-b123033dc4c8
455b2509-9b0b-46ca-932b-14eb531fce46	3b8ca68f-cba0-426f-9573-b123033dc4c8
f4bff9d9-c21b-44b4-8bbe-875cbcff367b	3b8ca68f-cba0-426f-9573-b123033dc4c8
865cfaa3-82ea-45ab-b105-098fe46cc95d	eb208b72-7723-4cd5-8e6b-e9008d5d27ac
455b2509-9b0b-46ca-932b-14eb531fce46	eb208b72-7723-4cd5-8e6b-e9008d5d27ac
f4bff9d9-c21b-44b4-8bbe-875cbcff367b	eb208b72-7723-4cd5-8e6b-e9008d5d27ac
af0b738b-998f-467f-b6f9-1a1b47faaa0a	b7013037-7ef6-4a36-833a-2cbd2c274195
af0b738b-998f-467f-b6f9-1a1b47faaa0a	a4f91036-f03f-4ebf-ae4b-38f2cac9c536
8f9790ff-ad2c-4f37-85c2-f38f7ba5fcf1	a4f91036-f03f-4ebf-ae4b-38f2cac9c536
f1d3a627-9a2b-4260-99c6-3f774350d5df	87deba9c-8064-4113-bff2-37c1885a85ec
0c3f2528-786f-4e1f-b6bc-b17b96b71e35	87deba9c-8064-4113-bff2-37c1885a85ec
ce76bf31-4b20-41cf-833c-7c416be77c70	3672b3d1-d5d6-4b8c-8f71-8b6c0fb15f96
f1c34ada-7718-4d95-95a3-2b6ad4edbcea	3672b3d1-d5d6-4b8c-8f71-8b6c0fb15f96
f1d3a627-9a2b-4260-99c6-3f774350d5df	ea61e97e-f07d-40a9-a71d-2ac657e147ee
0c3f2528-786f-4e1f-b6bc-b17b96b71e35	ea61e97e-f07d-40a9-a71d-2ac657e147ee
c4989137-de91-425e-b96e-82522b561b26	ea61e97e-f07d-40a9-a71d-2ac657e147ee
caaa16c4-6384-4a3e-b7dd-cfddfe06d871	85fca6d6-3965-43d6-8b04-78500230a0b7
48a3ae08-605e-4506-9440-4ac1fca3b717	85fca6d6-3965-43d6-8b04-78500230a0b7
b07e99be-9c8f-46fe-bee5-345fadb3e64f	85fca6d6-3965-43d6-8b04-78500230a0b7
f1d3a627-9a2b-4260-99c6-3f774350d5df	bcd82a67-ae73-4ab4-9660-977b3b60f765
0c3f2528-786f-4e1f-b6bc-b17b96b71e35	bcd82a67-ae73-4ab4-9660-977b3b60f765
c4989137-de91-425e-b96e-82522b561b26	bcd82a67-ae73-4ab4-9660-977b3b60f765
af0b738b-998f-467f-b6f9-1a1b47faaa0a	d153fa53-9963-4561-8c0e-5a5dbc3ffcf8
8754bcaa-c551-4092-b02e-254602173fb8	d153fa53-9963-4561-8c0e-5a5dbc3ffcf8
b0b48de3-2f15-4d44-8212-13605f6c0937	cc6aab07-11c0-4e03-b05d-9a6bdccc283f
d6f30b3c-63d4-4941-9487-438b7b1a79e5	455f0f2a-c5f1-44ef-b765-58637be13e11
8f9790ff-ad2c-4f37-85c2-f38f7ba5fcf1	455f0f2a-c5f1-44ef-b765-58637be13e11
dce67f42-77f7-4a95-9fcc-40947e0cb9a2	e2876767-426a-45b7-8e11-45cb99c6dc02
f2192130-195e-4c4d-b780-370592c9bfb5	e2876767-426a-45b7-8e11-45cb99c6dc02
73023870-0db8-469e-8b65-f51d9b402b48	e2876767-426a-45b7-8e11-45cb99c6dc02
f1d3a627-9a2b-4260-99c6-3f774350d5df	36f4769e-14ba-41df-a7a4-210a82d9fd98
48a3ae08-605e-4506-9440-4ac1fca3b717	36f4769e-14ba-41df-a7a4-210a82d9fd98
73023870-0db8-469e-8b65-f51d9b402b48	a3d33df8-bb7f-4f28-a46c-cb68d9d082bb
455b2509-9b0b-46ca-932b-14eb531fce46	a3d33df8-bb7f-4f28-a46c-cb68d9d082bb
73023870-0db8-469e-8b65-f51d9b402b48	cc99a220-934e-4024-b9a4-0f6e01130252
48a3ae08-605e-4506-9440-4ac1fca3b717	55ebc166-feb3-471b-b4c8-aac10e39e0d1
69664384-66bf-4121-9555-3cc5b7702bee	a3caa8b8-ad39-409f-a9fe-ea0a58d78b47
4dbb3d75-ec2f-46bc-b2cc-955b37c44f09	a3caa8b8-ad39-409f-a9fe-ea0a58d78b47
d6f30b3c-63d4-4941-9487-438b7b1a79e5	f72df24f-31a3-4dcc-8aff-5eada4bf6b85
718d2894-318e-40b4-ac8d-dbfdfde7a187	9a601b62-5a2b-41e5-afb7-51a248750d71
4532d70a-d80f-488a-a178-ea162f844c93	9a601b62-5a2b-41e5-afb7-51a248750d71
4a69822d-7c3b-40d2-bf5d-c670a8f9bc6e	9a601b62-5a2b-41e5-afb7-51a248750d71
cc7baa3c-f6a0-424f-9540-869839cf2960	9a601b62-5a2b-41e5-afb7-51a248750d71
d4d46a16-9fce-4cfe-b90e-9c95ed226bd4	ea816f47-28c9-491c-a0f2-de125996b4dc
a2f4429c-e94d-411b-9934-8373d685cc45	48aa5571-c7f5-4f92-b934-c1b0484cc0c0
4c6c0438-db7e-46bf-8efe-56b436d34f17	48aa5571-c7f5-4f92-b934-c1b0484cc0c0
49330bbd-e683-4cd3-ae59-2b98f29748f4	48aa5571-c7f5-4f92-b934-c1b0484cc0c0
a84d1570-5bfd-4b32-9037-8bf5957ad5d3	ab72de8a-b61c-4c60-894d-e61688dcbaa7
49330bbd-e683-4cd3-ae59-2b98f29748f4	ab72de8a-b61c-4c60-894d-e61688dcbaa7
961a7a5a-14f3-4776-a284-0511b8321d96	04ccb8f1-cacb-4d66-8676-c1852a2339da
d4d46a16-9fce-4cfe-b90e-9c95ed226bd4	04ccb8f1-cacb-4d66-8676-c1852a2339da
3bc91ee3-77bd-4dfa-b6f9-a45341c9921f	813e6ec5-26fa-4768-87a9-de0ba9499d35
d4d46a16-9fce-4cfe-b90e-9c95ed226bd4	813e6ec5-26fa-4768-87a9-de0ba9499d35
a2f4429c-e94d-411b-9934-8373d685cc45	2fc76728-d145-4e0c-a3b9-5f19729e36e8
d4d46a16-9fce-4cfe-b90e-9c95ed226bd4	2fc76728-d145-4e0c-a3b9-5f19729e36e8
\.


--
-- Data for Name: user_app; Type: TABLE DATA; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

COPY elements_recorder_schema.user_app (id, email, password, created_at, updated_at) FROM stdin;
894ecdb6-db23-4460-b5b1-9e6c792a27dc	someone@gmail.com	$2a$10$SGHEUyguKnKvunBdsQWILudJVNDBDLvsGPdLTP.7DdyNhTBnBthp.	2022-05-14 17:43:41.931959	2022-05-14 17:43:41.931961
f0891e0c-8a75-4ec1-bdbb-e559c428fba2	heyyou@gmail.com	$2a$10$6LzcF/jnxrYb8I/fZV5my.inyIF88WeyxMJpUb9uF1bTlSvm.lehm	2022-05-14 17:45:03.818029	2022-05-14 17:45:03.81803
3fec4f0a-f7ce-430c-8295-da00e82a873c	xxxxxxx@gmail.com	$2a$10$opxLT/Z.UE8eEhpuwkAh/e2gtVI1/g98WjkuGbljcu10Y0t8mKBZa	2022-05-14 17:48:53.960612	2022-05-14 17:48:53.960613
95acb2e9-e9ef-46fd-a66d-475f5d0f5fa1	newaccount@gmail.com	$2a$10$otEXEwMlpWvLEYOd8bsZgetLikbaXuNiB6NsRE/i.VN0mZWZQGyoa	2022-05-25 07:01:47.477422	2022-05-25 07:01:47.477426
32c1bffc-0bc6-4086-b6a3-934767b81508	qlassalle@gmail.com	$2a$10$hgWa0./MDAa2iYPsi37PfO02tJvo/xjHKrcdCZac6OiuvuSWC1sbG	2022-05-25 07:29:01.758682	2022-05-25 07:29:01.758687
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: elements_recorder_app
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	20200425163438	init	SQL	V20200425163438__init.sql	-1506115782	elements_recorder_app	2022-05-04 17:18:46.209792	33	t
2	20200530112030	add user table	SQL	V20200530112030__add_user_table.sql	-1150836664	elements_recorder_app	2022-05-04 17:18:46.290167	30	t
3	20220314222640	add tags	SQL	V20220314222640__add_tags.sql	583752415	elements_recorder_app	2022-05-04 17:18:46.35936	37	t
4	20220725183515	increase resource name size	SQL	V20220725183515__increase_resource_name_size.sql	1557588126	elements_recorder_app	2022-07-25 16:57:33.280475	44	t
\.


--
-- Name: resource resource_pkey; Type: CONSTRAINT; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

ALTER TABLE ONLY elements_recorder_schema.resource
    ADD CONSTRAINT resource_pkey PRIMARY KEY (id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

ALTER TABLE ONLY elements_recorder_schema.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: user_app user_app_email_key; Type: CONSTRAINT; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

ALTER TABLE ONLY elements_recorder_schema.user_app
    ADD CONSTRAINT user_app_email_key UNIQUE (email);


--
-- Name: user_app user_app_pkey; Type: CONSTRAINT; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

ALTER TABLE ONLY elements_recorder_schema.user_app
    ADD CONSTRAINT user_app_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: elements_recorder_app
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: tag_resource_idx; Type: INDEX; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

CREATE UNIQUE INDEX tag_resource_idx ON elements_recorder_schema.tag_resource USING btree (tag_id, resource_id);


--
-- Name: user_tag; Type: INDEX; Schema: elements_recorder_schema; Owner: elements_recorder_app
--

CREATE UNIQUE INDEX user_tag ON elements_recorder_schema.tag USING btree (name, user_id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: elements_recorder_app
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- PostgreSQL database dump complete
--

