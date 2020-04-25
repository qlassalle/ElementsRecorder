create table article
(
	id int auto_increment primary key,
	name varchar(50) not null,
	description text not null,
	rating tinyint null,
	url varchar(255) not null,
	created_at timestamp default CURRENT_TIMESTAMP not null,
	updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);