-- Group 1 Jaci, Austin, and Jon
-- CSD 460 Moffat Bay Lodge db setup

-- establish easy re-set, uncomment to start over. 
 DROP DATABASE mbLodge;
 DROP USER group1_dev@localhost;

-- create database and admin account
CREATE DATABASE mbLodge;
USE mbLodge;
CREATE USER group1_dev@localhost IDENTIFIED BY 'pass';
GRANT ALL ON mbLodge.* TO group1_dev@localhost;

-- Create table for customer data
CREATE TABLE customer_data (
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL PRIMARY KEY,
    age INT,
    phone VARCHAR(12) NOT NULL,
    hashedPass VARCHAR(128) NOT NULL,
    customerID INT NOT NULL UNIQUE AUTO_INCREMENT
);
-- provide some starter data
INSERT INTO customer_data (firstName, lastName, email, age, phone, hashedPass)
VALUES 
    ('Bob', 'Barker', 'bobker@example.com', 99, '555-123-1234', 'test'),
    ('Mary', 'Lamb', 'littlebah@example.com', 34, '555-124-1234', 'test'),
    ('Winston', 'Candelabra', 'beourguest@example.com', 47, '555-142-1234', 'test'),
    ('James', 'Patrick', 'oreo@whitefudge.com', 42, '555-867-5309', '20801734176305237020310068239086505145815731288986330710045825798527104737195'); -- For testing the pw is Hotel8one 

-- Store reservation details
CREATE TABLE Reservations (
    reservation_number INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    bed_size VARCHAR(50) NOT NULL,
    party_size INT NOT NULL,
    FOREIGN KEY (email) REFERENCES customer_data(email)
);

-- sample data
INSERT INTO reservations (email, check_in_date, check_out_date, bed_size, party_size)
VALUES 
    ('bobker@example.com', '2024-04-15', '2024-04-18', 'KING', 2), 
    ('bobker@example.com', '2023-07-20', '2024-07-25', '2QUEEN', 3);
 
 
 -- Table to hold info from contact us
 CREATE TABLE customer_messages (
fullName VARCHAR(30) NOT NULL, 
email VARCHAR(45) UNIQUE NOT NULL PRIMARY KEY,
phone VARCHAR(12),
resNum int,
subj VARCHAR(50) NOT NULL,
message mediumtext NOT NULL,
submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


