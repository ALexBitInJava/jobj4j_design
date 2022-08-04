create table type (
id serial primary key,
	name VARCHAR(50)
);

create table product (
id serial primary key,
	name VARCHAR(50),
	type_id INT references type(id),
	expired_date date,
	price INT
);

insert into type(name) values ('СЫР'), ('Мясо'), ('Сладкое'), ('Вода');
insert into type(name) values ('МОЛОКО');

insert into product(name, type_id, expired_date, price)
values ('Гауда', 1, '2022-07-05', 500), ('Маасдам', 1, '2022-12-05', 700),('Пошехонский', 1, '2022-08-04', 650),
('Липецкое', 3, '2022-06-10' , 60),('Мраморное', 3, '2022-10-05' , 50),('Коровка из Кореновки', 3, '2022-11-05' , 90),
('Коровка из Кореновки', 3, '2022-09-15' , 90),('Алексеевское', 5, '2022-08-17' , 90),('Алексеевское', 5, '2022-09-20' , 90),
('Филе индейки', 2, '2022-03-28' , 340),('Филе индейки', 2, '2022-05-11' , 340),
('Филе курицы', 2, '2022-12-02' , 310),('Филе индейки', 2, '2022-11-24' , 340);

insert into product(name, type_id, expired_date, price)
values ('Майская хрустальная', 4, '2022-10-10' , 40), ('Черноголовка', 4, '2022-08-08' , 130),
('Кока - кола', 3, '2022-07-07' , 150),('Пепси', 4, '2022-09-09' , 150);

select product.name as "Имя продукта с типом СЫР"
from product join type on type.id = product.type_id
where type.name like '%СЫР%';

select product.name as "Имя продукта с мороженое"
from product join type on type.id = product.type_id
where product.name like '%мороженое%'
group by product.name;

select product.name as "Имя продукта с истекшим сроком годности"
from product join type on type.id = product.type_id
where expired_date < current_date
group by product.name;

select p.name  as "Самый дорогой продукт" , p.price as "И его цена"
from product as p
where p.price = (select max(price) from product);

select t.name, count(p.name) from type as t
join product as p on p.type_id = t.id
group by t.name
;

select p.name as "Имя продукта с типом СЫР и МОЛОКО"
from product as p join type as t on t.id = p.type_id
where t.name like '%СЫР%' or t.name like '%МОЛОКО%';

select t.name as Тип from type as t join product as p
on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select t.name as Тип, p.name as Продукт from product as p
join type as t on
p.type_id = t.id
;