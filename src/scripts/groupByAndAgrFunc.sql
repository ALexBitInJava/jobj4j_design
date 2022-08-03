create table devices(
id serial primary key,
name varchar(255),
price float
);

create table people(
id serial primary key,
name varchar(255)
);

create table devices_people(
id serial primary key,
devices_id int  references devices(id),
people_id int references people(id)
);

insert into devices(name, price) values ('xiaomi','60000');
insert into devices(name, price) values ('xiaomi watch','6000');
insert into devices(name, price) values ('meizu','50000');
insert into devices(name, price) values ('samsung phone','100000');
insert into devices(name, price) values ('samsung watch','17000');
insert into devices(name, price) values ('huawei','80000');
insert into devices(name, price) values ('huawei watch','6000');
insert into devices(name, price) values ('nokia','40000');
insert into devices(name, price) values ('poco','35000');
insert into devices(name, price) values ('iphone','120000');
insert into devices(name, price) values ('iphone watch','20000');

insert into people(name) values ('Bob');
insert into people(name) values ('Tom');
insert into people(name) values ('Alex');
insert into people(name) values ('Kick');
insert into people(name) values ('Maria');
insert into people(name) values ('Alice');
insert into people(name) values ('Daria');
insert into devices_people(devices_id,people_id) values (1, 1), (2, 1), (3, 2);
insert into devices_people(devices_id,people_id) values (4, 3), (5, 3), (6, 4);
insert into devices_people(devices_id,people_id) values (7, 4), (8, 5), (9, 6);
insert into devices_people(devices_id,people_id) values (10, 7), (11, 7);

select * from devices;
select * from devices_people;
select avg(price) from devices;
select min(price) from devices;
select max(price) from devices;

select * from devices_people as dp join people as p  on dp.people_id = p.id;

select p.name, avg(price) from devices_people as dp
join devices as d on dp.devices_id = d.id
join people as p on dp.people_id = p.id
group by p.name;

select p.name, avg(price) from devices_people as dp
join devices as d on dp.devices_id = d.id
join people as p on dp.people_id = p.id
group by p.name
having avg(price) > 25000 ;