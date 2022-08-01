create table compose (
    id serial primary key,
    name text,
    author_id int references author(id)
);