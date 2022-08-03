create table product(
id serial primary key,
name varchar(256), type_id int references type(id),
expired_date boolean, price int);

create table type(
id serial primary key,
name varchar(256));

alter table product add column count int;

insert into type(name) values ('���'), ('����'), ('�������'), ('����');
insert into type(name) values ('������');

insert into product(name, type_id, expired_date, price, count)
values ('�����', 1, true, 500, 2), ('�������', 1, true, 700, 4),('�����������', 1, false , 650, 6),
('��������', 3, false , 60, 20),('���������', 3, false , 50, 30),('������� �� ���������', 3, true , 90, 4),
('������� �� ���������', 3, false , 90, 40),('������������', 5, false , 90, 40),('������������', 5, true , 90, 3),
('���� �������', 2, true , 340, 5),('���� �������', 2, false , 340, 15),
('���� ������', 2, true , 310, 2),('���� �������', 2, false , 340, 17);

insert into product(name, type_id, expired_date, price, count)
values ('������� �����������', 4, false , 40, 20), ('������������', 4, false , 130, 10),
('���� - ����', 3, false , 150, 10),('�����', 4, false , 150, 20);

select product.name as "��� �������� � ����� ���"
from product join type on type.id = product.type_id
where type.name like '%���%';

select product.name as "��� �������� � ���������"
from product join type on type.id = product.type_id
where product.name like '%���������%'
group by product.name;

select product.name as "��� �������� � �������� ������ ��������"
from product join type on type.id = product.type_id
where product.expired_date = true;

select p.name  as "����� ������� �������" , p.price as "� ��� ����"
from product as p
where p.price = (select max(price) from product);

select p.name, sum(p.count) from product as p
group by p.name
;

select product.name as "��� �������� � ����� ��� � ������"
from product join type on type.id = product.type_id
where type.name like '%���%' or type.name like '%������%';

select t.name as ���, sum(p.count) as ���������� from product as p join type as t on
p.type_id = t.id
where t.name = '���'
group by t.name;

select t.name as ���, p.name as ������� from product as p join type as t on
p.type_id = t.id
group by t.name, p.name
;

select * from product join type on product.type_id = type.id;