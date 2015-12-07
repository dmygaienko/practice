drop table if EXISTS city;
drop table if EXISTS author;
drop table if EXISTS country;

--  city table
create TABLE city (id int PRIMARY KEY, name varchar(255), country_name varchar(255));

insert into city (id, name, country_name) values (1, 'Kyiv', 'Ukraine');
insert into city (id, name, country_name) values (2, 'Dnipropetrovsk', 'Ukraine');
insert into city (id, name, country_name) values (3, 'Odessa', 'Ukraine');
insert into city (id, name, country_name) values (4, 'Lviv', 'Ukraine');

--  author table
create TABLE author (id int PRIMARY KEY, name varchar(255));

insert into author (id, name) values (1, 'Dmytro');
insert into author (id, name) values (2, 'Sergiy');

--  country table
create TABLE country (id1 varchar(255), id2 varchar(255), name varchar(255), code1 varchar(255), code2 varchar(255), PRIMARY KEY(id1, id2));

insert into country (id1, id2, name, code1, code2) values (1, 1, 'Ukraine', '040', '50');
insert into country (id1, id2, name, code1, code2) values (2, 2, 'Belarus', '077', '77');
