create schema if not exists elements_recorder_schema;

create table elements_recorder_schema.article
(
    id uuid,
    name varchar(50) not null,
    description text not null,
    rating smallint null,
    url varchar(255) not null,
    user_id uuid not null,
    created_at timestamp not null,
    updated_at timestamp not null
);