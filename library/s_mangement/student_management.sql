CREATE DATABASE student_management;

USE student_management;

CREATE TABLE stud_data_db (
    id INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    department VARCHAR(100),
    DOB DATE,
    entryYear YEAR
);
select * from stud_data_db