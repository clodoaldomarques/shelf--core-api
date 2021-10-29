CREATE TABLE Category(
	id SERIAL PRIMARY KEY,
	name varchar(100) NOT NULL
);

CREATE TABLE Measure(
	id SERIAL PRIMARY KEY,
	name varchar(100) NOT NULL,
	initials varchar(50) NOT NULL
);

CREATE TABLE Store(
	id SERIAL PRIMARY KEY,
	name varchar(100) NOT NULL,
	registration_date timestamp NOT NULL
);

CREATE TABLE Product(
	bar_code varchar(50) PRIMARY KEY,
	category_id integer NOT NULL REFERENCES Category,
	name varchar(100) NOT NULL,
	description varchar(1000),
	balance decimal(12,2),
	measure_id Integer NOT NULL REFERENCES Measure,
	deny_negative_balance boolean NOT NULL,
	registration_date timestamp NOT NULL
);

CREATE TABLE Inventory(
	id SERIAL PRIMARY KEY,
	store_id Integer NOT NULL REFERENCES Store,
	bar_code varchar(50) NOT NULL REFERENCES Product,
	purchase_price decimal(12,2),
	sale_price decimal(12,2),
	balance decimal(12,2),
	registration_date timestamp NOT NULL
);

