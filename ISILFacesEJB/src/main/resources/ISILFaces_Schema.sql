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
INSERT INTO Student (Name, LastName, Sex, Age, ProfilePicture, DNI) VALUES ('Dua', 'Lipa', 'F', 22, 'dua_lipa2.jpg', 74232212);
-- TODO: MOVE PICS TO OTHER TABLE, DNI IS UNIQUE
INSERT INTO Student (Name, LastName, Sex, Age, ProfilePicture, DNI) VALUES ('Julia', 'Michaels', 'F', 25, 'julia_michaels.jpg', 78763655);
INSERT INTO Student (Name, LastName, Sex, Age, ProfilePicture, DNI) VALUES ('Julia', 'Michaels', 'F', 25, 'julia_michaels2.jpg', 78763656);
INSERT INTO Student (Name, LastName, Sex, Age, ProfilePicture, DNI) VALUES ('Dua', 'Lipa', 'F', 22, 'dua_lipa.jpg', 74232211);
INSERT INTO Student (Name, LastName, Sex, Age, ProfilePicture, DNI) VALUES ('Dua', 'Lipa', 'F', 22, 'dua_lipa.jpg', 74232211);

