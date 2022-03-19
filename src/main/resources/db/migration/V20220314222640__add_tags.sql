create table elements_recorder_schema.tag
(
    id         uuid primary key,
    name       varchar(255) not null,
    user_id    uuid         not null,
    created_at timestamp    not null,
    updated_at timestamp    not null
);

create unique index user_tag on elements_recorder_schema.tag (name, user_id);

create table elements_recorder_schema.tag_resource
(
    tag_id      uuid not null,
    resource_id uuid not null
);

create unique index tag_resource_idx on elements_recorder_schema.tag_resource (tag_id, resource_id);