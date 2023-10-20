-- liquibase formatted sql

-- changeset egor:1
create table dogs (
	id bigserial,
	name varchar,
	breed varchar,
	birthday int4,
	description	varchar
);

create table cats (
	id bigserial,
	name varchar,
	breed varchar,
	birthday int4,
	description	varchar
);

create table dog_adopters (
	id bigserial,
	name varchar,
	birthday int4,
	phone varchar,
	email varchar,
	address varchar,
	chat_id int8,
	dog_id int8
);

create table cat_adopters (
	id bigserial,
	name varchar,
	birthday int4,
	phone varchar,
	email varchar,
	address varchar,
	chat_id int8,
	cat_id int8
);

create table report (
	id bigserial,
	chat_id int8,
	name varchar,
	nutrition varchar,
	health varchar,
	behaviour varchar,
	last_message timestamp,
	data oid
);

CREATE TABLE user_status (
	chat_id int8,
	shelter_type varchar,
	cat_adopter_id int8,
	dog_adopter_id int8
);