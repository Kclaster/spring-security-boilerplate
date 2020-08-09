DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS auth_users CASCADE;
DROP TABLE IF EXISTS auth_user_roles CASCADE;


CREATE TABLE books
(
    id SERIAL,
    title varchar(100) NOT NULL,
    description varchar(300)
);

CREATE TABLE auth_user_roles
(
    id int PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL
);

CREATE TABLE auth_users
(
    id uuid PRIMARY KEY NOT NULL,
    username varchar(30) NOT NULL,
    password varchar(60) NOT NULL,
    auth_user_role_id int REFERENCES auth_user_roles (id),
    is_expired boolean NOT NULL DEFAULT TRUE,
    is_locked boolean NOT NULL DEFAULT TRUE,
    is_credentials_expired boolean NOT NULL DEFAULT TRUE,
    is_enabled boolean NOT NULL DEFAULT TRUE
);

