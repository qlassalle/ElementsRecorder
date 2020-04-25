create table article
(
    id SERIAL,
	name varchar(50) not null,
	description text not null,
	rating smallint null,
	url varchar(255) not null,
	created_at timestamp default CURRENT_TIMESTAMP not null
);