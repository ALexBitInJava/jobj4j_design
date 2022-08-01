create table categories(
id serial primary key,
title varchar(100));

create table drivers(
id serial primary key,
    name varchar(100));
    
create table categories_drivers(
    id serial primary key,
    title_id int references categories(id),
    name_id int references drivers(id)
);

insert into categories(title) values('A');
insert into categories(title) values('B');
insert into categories(title) values('C');

insert into drivers(name) values('Ivan');
insert into drivers(name) values('Kirill');
insert into drivers(name) values('Romam');
insert into categories_drivers(title_id, name_id) values(1,1);
insert into categories_drivers(title_id, name_id) values(1,2);
insert into categories_drivers(title_id, name_id) values(2,2);
insert into categories_drivers(title_id, name_id) values(3,1);