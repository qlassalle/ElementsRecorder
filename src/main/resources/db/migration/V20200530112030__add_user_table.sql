create table user_app
(
    id SERIAL,
    user_uuid varchar(36) not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    updated_at timestamp default CURRENT_TIMESTAMP not null
);

CREATE TRIGGER update_updated_at BEFORE UPDATE
ON user_app FOR EACH ROW EXECUTE PROCEDURE
update_updated_at_column();