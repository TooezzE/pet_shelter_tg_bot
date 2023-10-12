-- liquibase formatted sql

-- changeset egor:1
CREATE TABLE cats (
	id bigserial NOT NULL,
	name varchar,
	age int4,
	breed varchar,
	is_disabled boolean,
	shelter_id int8
);

CREATE TABLE dogs (
	id bigserial NOT NULL,
	name varchar,
	age int4,
	breed varchar,
	is_disabled boolean,
	shelter_id int8
);

CREATE TABLE dog_shelter (
	id bigserial NOT NULL,
	info varchar,
	how_to_pick_up varchar,
	timetable varchar,
	address varchar,
	driving_directions varchar,
	security_info varchar,
	safety_precautions varchar,
	rules_to_meet_animal varchar,
	documents varchar,
	handlers varchar,
	CONSTRAINT dog_shelter_pkey PRIMARY KEY (id)
);

CREATE TABLE cat_shelter (
	id bigserial NOT NULL,
	info varchar,
	how_to_pick_up varchar,
	timetable varchar,
	address varchar,
	driving_directions varchar,
	security_info varchar,
	safety_precautions varchar,
	rules_to_meet_animal varchar,
	documents varchar,
	CONSTRAINT cat_shelter_pkey PRIMARY KEY (id)
);


CREATE TABLE cat_recomms (
	id bigserial NOT NULL,
	transport varchar,
	home_improve_for_young varchar,
	home_improve_for_adult varchar,
	recomms_for_disabled varchar,
	cat_id int8
);

CREATE TABLE dog_recomms (
	id bigserial NOT NULL,
	transport varchar,
	home_improve_for_young varchar,
	home_improve_for_adult varchar,
	recomms_for_disabled varchar,
	handler_advices varchar,
	dog_id int8
);