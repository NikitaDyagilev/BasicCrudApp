-- Meant as a reference NOT as an autogeneration of databases

:create schema account;

:use account;

:CREATE TABLE USER(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(60),
last_name VARCHAR(60),
bio VARCHAR(60)
);