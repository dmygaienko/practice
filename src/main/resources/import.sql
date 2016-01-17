drop table if EXISTS city;
--drop table if EXISTS author;
--drop table if EXISTS author_address;
drop table if EXISTS country;

--  city table
create TABLE city (id int PRIMARY KEY, city_type int, name varchar(255), country_id1 int, country_id2 int, country_name varchar(255));

insert into city (id, city_type, name, country_id1, country_id2, country_name) values (1, 0, 'Kyiv', 1, 1, 'Ukraine');
insert into city (id, city_type, name, country_id1, country_id2, country_name) values (2, 1, 'Dnipropetrovsk', 1, 1, 'Ukraine');
insert into city (id, city_type, name, country_id1, country_id2, country_name) values (3, 2, 'Odessa', 1, 1, 'Ukraine');
insert into city (id, city_type, name, country_id1, country_id2, country_name) values (4, 3, 'Lviv', 1, 1, 'Ukraine');

--  author table
/*create TABLE author (id int PRIMARY KEY, name varchar(255), date_of_birth date);

insert into author (id, name, date_of_birth) values (1, 'Dmytro', '1990-09-11');
insert into author (id, name, date_of_birth) values (2, 'Sergiy', '1990-02-28');

--  author_address table
create TABLE author_address (author_id int PRIMARY KEY, street varchar(255), number int, flat int);

insert into author_address (author_id, street, number, flat) values (1, 'Melnykova', '63', '13');
insert into author_address (author_id, street, number, flat) values (2, 'Lykyanivka', '1', '128');*/

--  country table
create TABLE country (id1 varchar(255), id2 varchar(255), name varchar(255), code1 varchar(255), code2 varchar(255), PRIMARY KEY(id1, id2));

insert into country (id1, id2, name, code1, code2) values (1, 1, 'Ukraine', '040', '50');
insert into country (id1, id2, name, code1, code2) values (2, 2, 'Belarus', '077', '77');


-- product table

create TABLE product (id int PRIMARY KEY, version int, name varchar(255), code varchar(255));

insert into product (id, version, name, code) values (1, 1, 'apple macbook pro 13', '13');
insert into product (id, version, name, code) values (2, 1, 'apple macbook pro 15', '15');
insert into product (id, version, name, code) values (3, 1, 'apple macbook 12', '12');
