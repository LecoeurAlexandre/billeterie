use billeterie;
CREATE TABLE client (
id INT PRIMARY KEY AUTO_INCREMENT,
last_name VARCHAR(50) NOT NULL,
first_name VARCHAR(50) NOT NULL,
email VARCHAR(255) NOT NULL
 );
 CREATE TABLE event (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 date DATE NOT NULL,
 time TIME NOT NULL,
 price FLOAT NOT NULL,
 sales int NOT NULL,
 place_id INT NOT NULL,
 FOREIGN KEY (place_id) REFERENCES place(id)
 );
 
 CREATE TABLE place (
 id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 address VARCHAR(255) NOT NULL,
 capacity INT NOT NULL
 );
 
 CREATE TABLE event_client (
 id INT PRIMARY KEY AUTO_INCREMENT,
 event_id INT NOT NULL,
 client_id INT NOT NULL,
 FOREIGN KEY (event_id) REFERENCES event(id),
 FOREIGN KEY (client_id) REFERENCES client(id)
 );
 