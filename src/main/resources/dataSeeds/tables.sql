DROP TABLE IF EXISTS books CASCADE;

CREATE TABLE books
(
    id SERIAL,
    title varchar(30) NOT NULL,
    description varchar(300)
);


