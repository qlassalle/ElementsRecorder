create schema if not exists elements_recorder_schema;

create table elements_recorder_schema.article
(
    id SERIAL,
    name varchar(50) not null,
    description text not null,
    rating smallint null,
    url varchar(255) not null,
    created_at timestamp default CURRENT_TIMESTAMP not null
);