insert into TODO ("ID", "NAME", "DESCRIPTION", "DUEDATE", "DONE") values (10000, 'prepare application', 'many more usecases are needed', DATE '2012-12-31', false);
insert into TODO ("ID", "NAME", "DESCRIPTION", "DUEDATE", "DONE") values (10001, 'document code', 'this is alway important', DATE '2011-12-31', false);
insert into TODO ("ID", "NAME", "DESCRIPTION", "DUEDATE", "DONE") values (10002, 'call mum', 'dont forget her birthday this year', DATE '2012-08-25', false);
insert into TODO ("ID", "NAME", "DESCRIPTION", "DUEDATE", "DONE") values (10003, 'finish usecase', 'update on homepage is still not working', DATE '2011-05-01', false);


insert into TAG ("ID", "NAME", "DESCRIPTION") values (20000, '@work', 'everything at work');
insert into TAG ("ID", "NAME", "DESCRIPTION") values (20001, '@phone', 'all phone calls to make');
insert into TAG ("ID", "NAME", "DESCRIPTION") values (20002, '@code', 'everything related to source code');
insert into TAG ("ID", "NAME", "DESCRIPTION") values (20003, '@jax', 'for the jax');

insert into TODO_TAG ("TODO_ID", "TAGS_ID") values (10000, 20000);
insert into TODO_TAG ("TODO_ID", "TAGS_ID") values (10000, 20002);
insert into TODO_TAG ("TODO_ID", "TAGS_ID") values (10001, 20002);
insert into TODO_TAG ("TODO_ID", "TAGS_ID") values (10002, 20001);
insert into TODO_TAG ("TODO_ID", "TAGS_ID") values (10003, 20003);
insert into TODO_TAG ("TODO_ID", "TAGS_ID") values (10003, 20002);
