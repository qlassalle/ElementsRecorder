create schema elements_recorder collate utf8_general_ci;

create table article
(
	id int auto_increment
		primary key,
	name varchar(50) not null,
	description text not null,
	rating tinyint null,
	created_at timestamp default CURRENT_TIMESTAMP not null,
	updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);

