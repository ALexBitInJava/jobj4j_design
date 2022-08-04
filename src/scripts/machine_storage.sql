create table car_bodies (
    id serial primary key,
    name VARCHAR(50)
);
create table car_engines (
    id serial primary key,
    name VARCHAR(50)
);
create table car_transmissions (
    id serial primary key,
    name VARCHAR(50)
);
create table cars (
    id serial primary key,
    name VARCHAR(50),
    body_id INT references car_bodies(id),
    engine_id INT references car_engines(id),
    transmission_id INT references car_transmissions(id)
);

insert into car_bodies (name) values ('sedan');
insert into car_bodies (name) values ('hatchback');
insert into car_bodies (name) values ('minivan');
insert into car_bodies (name) values ('coupe');
insert into car_bodies (name) values ('cabriolet');
insert into car_bodies (name) values ('pickup');
insert into car_bodies (name) values ('sport car');
insert into car_bodies (name) values ('electro car');
insert into car_bodies (name) values ('hybrid car');
insert into car_bodies (name) values (null);
insert into car_bodies (name) values ('Truck');
insert into car_bodies (name) values ('SUV');

insert into car_engines (name) values ('v4');
insert into car_engines (name) values ('v4 tsi');
insert into car_engines (name) values ('v4 dci');
insert into car_engines (name) values ('v6 tsi');
insert into car_engines (name) values ('v6 dci');
insert into car_engines (name) values ('hybrid');
insert into car_engines (name) values ('electro');
insert into car_engines (name) values (null);
insert into car_engines (name) values ('v8 dci');
insert into car_engines (name) values ('v8 biturbo');

insert into car_transmissions (name) values ('Manual');
insert into car_transmissions (name) values ('Robot');
insert into car_transmissions (name) values ('Automatic');
insert into car_transmissions (name) values ('CVT');
insert into car_transmissions (name) values (null);
insert into car_transmissions (name) values ('DSG');
insert into car_transmissions (name) values ('variable speed drive');

insert into cars (name, body_id, engine_id, transmission_id)
values ('Audi', 1, 1, 1),('Acura', 2, 2, 2),('BMW', 3, 3, 3),('Chevrolet', 4, 4, 4),
('Chery', 5, 5, 5),('Citroen', 6, 6, 1),('Dodge', 7, 7, 2),('Ford', 8, 8, 3),
('Geely', 9, 1, 4),('Honda', 10, 2, 5),('Hundai', 1, 3, 1),('Mazda', 2, 4, 2),
('Mercedes', 3, 5, 3),('Mini', 4, 6, 4),('Mitsubishi', 5, 7, 5),('Opel', 6, 8, 1),
('Nissan', 7, 1, 2),('Peugeot', 8, 2, 3),('Renault', 9, 3, 4),('Skoda', 10, 4, 5),
('Subaru', 1, 5, 1),('Suzuki', 2, 6, 2),('Toyota', 3, 8, 3),('Tesla', 8, 7, 4),
('Volvo', 4, 1, 5),('Volkswagen', 5, 2, 1),('Haval', 6, 3, 2),('Exeed', 7, 4, 3);

select c.name Марка, e.name Двигатель, t.name Трансмиссия from cars as c
left join car_bodies as b on c.body_id = b.id
left join car_engines as e on c.engine_id = e.id
left join car_transmissions as t on c.transmission_id = t.id;

SELECT b.name body_is_null FROM car_bodies b
left JOIN cars as c on c.body_id = b.id
where c.body_id is null;

SELECT e.name engines_is_null FROM car_engines e
left JOIN cars as c on c.engine_id = e.id
where c.body_id is null;

SELECT t.name transmissions_is_null FROM car_transmissions as t
left JOIN cars as c on c.transmission_id = t.id
where c.transmission_id is null;