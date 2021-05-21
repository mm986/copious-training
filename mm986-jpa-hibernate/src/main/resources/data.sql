DROP TABLE IF EXISTS person;

CREATE TABLE person
(
  id integer not null,
  name varchar(255) NOT NULL,
  location varchar(255),
  birth_date timestamp,
  primary key(id)
);

INSERT INTO person(`id`,`name`,`location`,`birth_date`)
VALUES(101,'Mahesh','Mangaon',sysdate());

INSERT INTO person(`id`,`name`,`location`,`birth_date`)
VALUES(102,'Praja','Mangaon',sysdate());

INSERT INTO person(`id`,`name`,`location`,`birth_date`)
VALUES(103,'Shubhu','Mangaon',sysdate());
