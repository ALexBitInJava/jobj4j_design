create table departments (
id serial primary key ,
name varchar(256)
);

create table employees (
id serial primary key,
name varchar(256),
departments_id int references departments(id)
);

insert into departments(name) values ('HR'),('Sales'),('Accounting '),
('IT'),('Management');

insert into employees(name, departments_id) values ('Tom', 1),('Bob', 2),
('Alex', 3),('Met', 4),('Nick', 1),('Rock', 2),('Jim', 3),('Adam', 4)
,('Agata', 1),('Agnes', 2),('Ida', 3),('Jane', 4),('Julia', 1),('Eva', 2);

select * from departments;
select * from employees;

select d.name, e.name from departments as d left join employees as e
on e.departments_id =  d.id;

select d.name, e.name from departments as d right join employees as e
on e.departments_id =  d.id;

select d.name, e.name from departments as d full join employees as e
on e.departments_id =  d.id;

select d.name, e.name from departments as d cross join employees as e;

select d.name as "Пустой отдел" from departments as d left join employees as e
on e.departments_id =  d.id
where e."name" is null;

select d.name, e.name from departments as d left join employees as e
on e.departments_id =  d.id;

select d.name, e.name from employees as e right join departments as d
on e.departments_id =  d.id;

create table teens (
id serial primary key,
name varchar(256),
gender varchar(256));

insert into teens(name, gender) values ('Ivy', 'Female'),('Karen', 'Female'),
('Candy', 'Female'),('Linda', 'Female'),('Mia', 'Female'),('Nancy', 'Female');
insert into teens(name, gender) values ('Felix', 'Male'),('Alex', 'Male'),
('Harry', 'Male'),('Jack', 'Male'),('Jason', 'Male'),('Scott', 'Male');

select t1.name, t2.name from teens as t1 cross join teens as t2
where t1.gender != t2.gender;
