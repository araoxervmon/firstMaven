CREATE TABLE contact (
	id INTEGER IDENTITY,
	firstName VARCHAR(10) NOT NULL,
	lastName VARCHAR(10) NOT NULL,
	phone VARCHAR(10) NOT NULL,
	email VARCHAR(25) NOT NULL
);
	CREATE TABLE user (
	id INTEGER IDENTITY,
	username VARCHAR(10) NOT NULL,
	password VARCHAR(35) NOT NULL,
	email VARCHAR(35) NOT NULL
);
INSERT INTO "PUBLIC"."CONTACT"( "FIRSTNAME", "LASTNAME", "PHONE" , "EMAIL" )
VALUES ( 'John', 'Doe', '+123456789', 'abhi@csu.com'),( 'Jane', 'Doe', '+987654321', 'doe@csu.com');


INSERT INTO "PUBLIC"."USER"( "USERNAME", "PASSWORD", "EMAIL" )
VALUES ( 'abhilash', 'abhilash', 'abc@abc.com'),( 'arnold', 'Doe', 'arnold@abc.com');