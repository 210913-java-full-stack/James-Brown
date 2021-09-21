###########################################################
################## CREATE FRESH DATABASE ##################
###########################################################
DROP DATABASE IF EXISTS Customers;
CREATE DATABASE Customers;

USE Customers;

DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS accounts_customers;


CREATE TABLE accounts_customers 
(
	junction_id INT AUTO_INCREMENT,
	account_id 	INT NOT NULL ,
	customer_id INT NOT NULL,
	INDEX (account_id),
	INDEX (customer_id),
	CONSTRAINT accounts_customers_fk PRIMARY KEY (junction_id)
);


CREATE TABLE accounts
(
    account_id 		INT NOT NULL,
    balance 		DECIMAL (10, 2),
    CONSTRAINT accounts_pk PRIMARY KEY (account_id), 
    CONSTRAINT accounts_accounts_customers_fk FOREIGN KEY (account_id) REFERENCES accounts_customers (account_id)
);


CREATE TABLE address
(
	address_id		INT AUTO_INCREMENT,
	address			VARCHAR(200),
	city			VARCHAR(200),
	state			CHAR(2),
	zip				INT NOT NULL,
	CONSTRAINT address_pk PRIMARY KEY (address_id)
);


CREATE TABLE customers
(
    customer_id 	INT NOT NULL,
    name 			VARCHAR(200),
    address_id		INT NOT NULL,
    CONSTRAINT customers_pk PRIMARY KEY (customer_id), 
    CONSTRAINT customers_accounts_customers_fk FOREIGN KEY (customer_id) REFERENCES accounts_customers (customer_id),
    CONSTRAINT customers_address_fk FOREIGN KEY (address_id) REFERENCES address (address_id)
);






###########################################################
################# POPULATE FRESH DATABASE #################
###########################################################

INSERT INTO accounts_customers (customer_id, account_id) VALUES (0001, 900001);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0001, 900002);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0002, 900003);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0002, 900004);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0003, 900005);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0004, 900006);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0005, 900007);

INSERT INTO address (address, city, state, zip) VALUES ("1 Way st.", "Tampa", "FL", 12345);
INSERT INTO address (address, city, state, zip) VALUES ("144 Bleeker st.", "Austin", "TX", 55447);
INSERT INTO address (address, city, state, zip) VALUES ("86 fuzzy lane", "Dallas", "TX", 55445);
INSERT INTO address (address, city, state, zip) VALUES ("212 1st ave", "Albany", "NY", 12210);
INSERT INTO address (address, city, state, zip) VALUES ("74 Daytona Ave.", "Albany", "NY", 12210);

INSERT INTO customers (customer_id, name, address_id) VALUES (0001, "Jason Smith", 2);
INSERT INTO customers (customer_id, name, address_id) VALUES (0002, "Amanda Smith", 4);
INSERT INTO customers (customer_id, name, address_id) VALUES (0003, "John Cross", 1);
INSERT INTO customers (customer_id, name, address_id) VALUES (0004, "Marty Gras", 3);
INSERT INTO customers (customer_id, name, address_id) VALUES (0005, "Jason A. Lastname", 5);

INSERT INTO accounts (account_id, balance) VALUES (900001, 1500.50);
INSERT INTO accounts (account_id, balance) VALUES (900002, 2780.25);
INSERT INTO accounts (account_id, balance) VALUES (900003, 150);
INSERT INTO accounts (account_id, balance) VALUES (900004, 13.33);
INSERT INTO accounts (account_id, balance) VALUES (900005, 100000.01); 
INSERT INTO accounts (account_id, balance) VALUES (900006, 12345.67);
INSERT INTO accounts (account_id, balance) VALUES (900007, 1345.67);


###########################################################
##################### TEST YOUR SKILLS ####################
###########################################################

# Get a list of all customers with the last name "Smith".
Select name 
FROM customers 
WHERE name LIKE '%Smith';

# Get the total balance of all accounts held by the Smiths.
SELECT name, balance
FROM accounts a
JOIN accounts_customers ac ON ac.account_id = a.account_id 
JOIN customers c ON c.customer_id = ac.customer_id 
WHERE c.name LIKE '%Smith'

# Get the name and address of any customer with less than $50 in an account. (No duplicates!)
#REVISIT THIS SO YOU CAN MENTALLY MAKE SENSE OF IT BOY
SELECT c.name, ad.address, balance 
FROM customers as c
JOIN address as ad on ad.address_id = c.address_id
JOIN accounts_customers acu on acu.customer_id = c.customer_id 
JOIN accounts as acc on acc.account_id = acu.account_id 
WHERE balance < 50
GROUP BY c.customer_id 

# Get a list of all the customers who live in Texas.
Select c.name, CONCAT(a.address,' ', a.city, ', ', a.state,', ', a.zip) as 'Full Address'
FROM customers c
JOIN address a on a.address_id = c.address_id 
WHERE a.state = 'TX'

# Add $100 gift to any accounts belonging to customers in New York
#SELECT *
#FROM customers c
#JOIN address a on c.address_id = a.address_id 
#JOIN accounts_customers ac on ac.customer_id = c.customer_id 
#JOIN accounts a2 on a2.account_id = ac.account_id 
#WHERE state = 'NY'

UPDATE accounts ac
JOIN accounts_customers ac1 on ac.account_id = ac1.account_id
JOIN customers c on c.customer_id = ac1.customer_id 
JOIN address a on a.address_id = c.address_id 
SET balance = balance + 100
WHERE a.state = 'NY'


# Transfer $199.99 from Jason Smith to Amanda Smith (This requires two statements)
UPDATE accounts ac
JOIN accounts_customers ac1 on ac.account_id = ac1.account_id
JOIN customers c on c.customer_id = ac1.customer_id 
SET balance = balance - 199.99
WHERE name = 'Jason Smith'

UPDATE accounts ac
JOIN accounts_customers acc on acc.account_id = ac.account_id 
JOIN customers c on c.customer_id = acc.customer_id 
SET balance = balance + 199.99
WHERE name = 'Amanda Smith'

# Change Amanda Smith's last name to "Lastname"
UPDATE customers 
SET name = 'Amanda Lastname'
WHERE name = 'Amanda Smith'
