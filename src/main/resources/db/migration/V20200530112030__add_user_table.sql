create table elements_recorder_schema.user_app
(
    id uuid primary key,
    email varchar(255) not null unique,
    password varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);