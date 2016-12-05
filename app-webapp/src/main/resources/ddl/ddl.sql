DROP TABLE role_entity;

create table role_entity (
	id INTEGER NOT NULL AUTO_INCREMENT,
	role varchar(99),
	company varchar(99),
	PRIMARY KEY (id)
);

DROP TABLE customer_entity;

create table customer_entity (
	id INTEGER NOT NULL AUTO_INCREMENT,
	first_name varchar(99),
	last_name varchar(99),
	role_id INTEGER (15),
	PRIMARY KEY (id),
	FOREIGN KEY (role_id) REFERENCES role_entity(id)
);




