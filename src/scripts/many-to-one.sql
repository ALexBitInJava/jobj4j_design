create table surnameTable (
id serial primary key,
    surname text);

create table familyName (
id serial primary key,
name text,
familyName_id int references surnameTable(id));

insert into surnameTable(surname) values ('Jankins');

insert into familyName (name, familyName_id) values('Alex', 1);
insert into familyName (name, familyName_id) values('Juliana', 1);

select * from familyName;
select * from surnameTable where id in (select id from familyName);