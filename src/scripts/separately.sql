create table separately (
id serial primary key,
name text,
money money,
account_number integer);

select * from separately;

insert into separately (name, money, account_number) values('Alex','100','987654');

update separately set name = 'Bob';

delete from separately;