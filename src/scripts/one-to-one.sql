create table rubleBankAccountVTB(
id serial primary key,
account int);

create table tableUser (
id serial primary key,
name varchar(100),
tableUser_id int references rubleBankAccountVTB(id) unique);

create table rubleBankAccountVTB_tableUser(
id serial primary key,
rubleBankAccountVTB_id int references rubleBankAccountVTB(id) unique,
tableUser_id int references tableUser(id) unique);