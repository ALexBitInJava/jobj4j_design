create table surnameTable (
id serial primary key,
    surname text);

create table familyName (
id serial primary key,
familyMember text,
familyName_id int references surnameTable(id));

insert into familyMembers(role) values ('Jankins');

insert into familyName (name) values('Alex', 1);
insert into familyName (name) values('Juliana', 1);

select * from familyName;
select * from surnameTable where id in (select id from familyName);