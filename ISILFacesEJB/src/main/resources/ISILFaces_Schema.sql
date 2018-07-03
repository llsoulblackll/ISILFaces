CREATE DATABASE IF NOT EXISTS ISILFaces;

USE ISILFaces;

CREATE TABLE IF NOT EXISTS Student
(
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(300),
	LastName VARCHAR(300),
	Sex CHAR,
	Age INT,
	ProfilePicture TEXT,
	DNI CHAR(8) UNIQUE
);

INSERT INTO Student (Name, LastName, Sex, Age, ProfilePicture, DNI) VALUES ('Dua', 'Lipa', 'F', 22, 'dua_lipa.jpg', 74232211);