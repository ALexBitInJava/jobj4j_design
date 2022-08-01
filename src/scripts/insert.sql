insert into role(name) values ('proger');
insert into role(name) values ('analyst');
insert into role(name) values ('project manager');
insert into role(name) values ('office manager');

insert into users(name, role_id) values ('Tom', 1);
insert into users(name, role_id) values ('Stiv', 2);
insert into users(name, role_id) values ('Max', 3);
insert into users(name, role_id) values ('Bob', 4);

insert into rules(name) values ('manage');
insert into rules(name) values ('sprint');
insert into rules(name) values ('BP analysis');
insert into rules(name) values ('code');
insert into rules(name) values ('draw up documents');
insert into rules(name) values ('protect');
insert into rules(name) values ('arrange meeting');

insert into role_rules(role_id, rule_id) values (1, 7);
insert into role_rules(role_id, rule_id) values (1, 8);
insert into role_rules(role_id, rule_id) values (2, 4);
insert into role_rules(role_id, rule_id) values (2, 2);
insert into role_rules(role_id, rule_id) values (2, 6);
insert into role_rules(role_id, rule_id) values (3, 4);
insert into role_rules(role_id, rule_id) values (3, 5);
insert into role_rules(role_id, rule_id) values (4, 2);
insert into role_rules(role_id, rule_id) values (4, 3);

insert into category(name) values ('client servise');
insert into category(name) values ('client issue');
insert into category(name) values ('assignment');
insert into category(name) values ('request');

insert into state(name) values ('selected for development');
insert into state(name) values ('at work');
insert into state(name) values ('done');

insert into item(description, user_id, category_id, state_id) values ('make an express examination', 3, 1, 1);
insert into item(description, user_id, category_id, state_id) values ('send an express survey to the customer', 2, 3, 2);
insert into item(description, user_id, category_id, state_id) values ('accept payment for express examination', 4, 1, 2);
insert into item(description, user_id, category_id, state_id) values ('Meet on the topic of system refinement', 3, 4, 3);

insert into comments(comment, item_id) values ('minimum priority', 1);
insert into comments(comment, item_id) values ('selected for development', 1);
insert into comments(comment, item_id) values ('In operation until 01.10.', 2);
insert into comments(comment, item_id) values ('maximum priority', 3);