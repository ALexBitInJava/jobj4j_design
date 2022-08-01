create table music_lover_compose (
    id serial primary key,
    music_lovel_id int references music_lover(id),
    compose_id int references compose(id)
);