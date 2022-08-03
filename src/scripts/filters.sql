create table product(
id serial primary key,
name varchar(256), type_id int references type(id),
expired_date boolean, price int);

create table type(
id serial primary key,
name varchar(256));

alter table product add column count int;

insert into type(name) values ('СЫР'), ('Мясо'), ('Сладкое'), ('Вода');
insert into type(name) values ('МОЛОКО');

insert into product(name, type_id, expired_date, price, count)
values ('Гауда', 1, true, 500, 2), ('Маасдам', 1, true, 700, 4),('Пошехонский', 1, false , 650, 6),
('Липецкое', 3, false , 60, 20),('Мраморное', 3, false , 50, 30),('Коровка из Кореновки', 3, true , 90, 4),
('Коровка из Кореновки', 3, false , 90, 40),('Алексеевское', 5, false , 90, 40),('Алексеевское', 5, true , 90, 3),
('Филе индейки', 2, true , 340, 5),('Филе индейки', 2, false , 340, 15),
('Филе курицы', 2, true , 310, 2),('Филе индейки', 2, false , 340, 17);

insert into product(name, type_id, expired_date, price, count)
values ('Майская хрустальная', 4, false , 40, 20), ('Черноголовка', 4, false , 130, 10),
('Кока - кола', 3, false , 150, 10),('Пепси', 4, false , 150, 20);

select product.name as "Имя продукта с типом СЫР"
from product join type on type.id = product.type_id
where type.name like '%СЫР%';

select product.name as "Имя продукта с мороженое"
from product join type on type.id = product.type_id
where product.name like '%мороженое%'
group by product.name;

select product.name as "Имя продукта с истекшим сроком годности"
from product join type on type.id = product.type_id
where product.expired_date = true;

select p.name  as "Самый дорогой продукт" , p.price as "И его цена"
from product as p
where p.price = (select max(price) from product);

select p.name, sum(p.count) from product as p
group by p.name
;

select product.name as "Имя продукта с типом СЫР и МОЛОКО"
from product join type on type.id = product.type_id
where type.name like '%СЫР%' or type.name like '%МОЛОКО%';

select t.name as Тип, sum(p.count) as Количество from product as p join type as t on
p.type_id = t.id
where t.name = 'СЫР'
group by t.name;

select t.name as Тип, p.name as Продукт from product as p join type as t on
p.type_id = t.id
group by t.name, p.name
;

select * from product join type on product.type_id = type.id;