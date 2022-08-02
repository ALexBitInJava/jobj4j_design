create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('swordfish', 5000, '1900-01-01');
insert into fauna(name, avg_age, discovery_date) values ('sawfish', 10000, '1950-01-01');
insert into fauna(name, avg_age, discovery_date) values ('dropfish', 17000, '1960-01-01');
insert into fauna(name, avg_age, discovery_date) values ('clown fish', 20500, '1970-01-01');
insert into fauna(name, avg_age, discovery_date) values ('frog', 30000, '1910-01-01');
insert into fauna(name, avg_age, discovery_date) values ('crocodile', 3000, '1910-01-01');
insert into fauna(name, avg_age, discovery_date) values ('alligator', 2000, null );
insert into fauna(name, avg_age, discovery_date) values ('turtle', 10500, null);
insert into fauna(name, avg_age, discovery_date) values ('anaconda', 100, null);
insert into fauna(name, avg_age, discovery_date) values ('piranha', 50000, '1970-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select  * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';