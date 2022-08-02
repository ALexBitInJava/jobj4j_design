create table fauna2 (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

create table population (
    id serial primary key,
    fauna2_id int references fauna2(id),
    population int,
    gestation_period int
);


insert into fauna2(name, avg_age, discovery_date) values ('swordfish', 5000, '1900-01-01');
insert into fauna2(name, avg_age, discovery_date) values ('sawfish', 10000, '1950-01-01');
insert into fauna2(name, avg_age, discovery_date) values ('dropfish', 17000, '1960-01-01');
insert into fauna2(name, avg_age, discovery_date) values ('clown fish', 20500, '1970-01-01');
insert into fauna2(name, avg_age, discovery_date) values ('frog', 30000, '1910-01-01');
insert into fauna2(name, avg_age, discovery_date) values ('crocodile', 3000, '1910-01-01');
insert into fauna2(name, avg_age, discovery_date) values ('alligator', 2000, null );
insert into fauna2(name, avg_age, discovery_date) values ('turtle', 10500, null);
insert into fauna2(name, avg_age, discovery_date) values ('anaconda', 100, null);
insert into fauna2(name, avg_age, discovery_date) values ('piranha', 50000, '1970-01-01');
insert into fauna2(name, avg_age, discovery_date) values ('snake', 5000, '1980-01-01');

insert into population(fauna2_id, population, gestation_period) values (1, 5000, 14);
insert into population(fauna2_id, population, gestation_period) values (2, 10000, 30);
insert into population(fauna2_id, population, gestation_period) values (3, 17000, 40);
insert into population(fauna2_id, population, gestation_period) values (4, 20500, 35);
insert into population(fauna2_id, population, gestation_period) values (5, 30000, 110);
insert into population(fauna2_id, population, gestation_period) values (6, 3000, 80);
insert into population(fauna2_id, population, gestation_period) values (7, 2000, 80);
insert into population(fauna2_id, population, gestation_period) values (8, 10500, 75);
insert into population(fauna2_id, population, gestation_period) values (9, 100, 90);
insert into population(fauna2_id, population, gestation_period) values (10, 50000, 7);
insert into population(fauna2_id, population, gestation_period) values (11, 2000, 15);

select fa.name, po.population, po.gestation_period
from fauna2 as fa join population as po on fa.id = po.fauna2_id;

select fa.name as Вид, fa.discovery_date as "Дата научного открытия вида", po.population as Популяция, po.gestation_period as "Период беременности"
from fauna2 as fa join population as po on fa.id = po.fauna2_id;

select fa.name Вид, fa.avg_age as "Средняя продолжительность жизни в днях", po.population Популяция
from fauna2 fa join population po on fa.id = po.fauna2_id;