CREATE DATABASE crsDb;

USE crsDb;

CREATE TABLE course (
	courseId VARCHAR(10),
    name VARCHAR(100),
    seats INT,
    fee INT
);

CREATE TABLE teacher (
	userId VARCHAR(10),
    name VARCHAR(30),
    qualification VARCHAR(30),
    department VARCHAR(30),
    courseId VARCHAR(10)
);

CREATE TABLE student (
	userId VARCHAR(20),
    name VARCHAR(30),
    department VARCHAR(30)
);

CREATE TABLE student_course (
	studentId VARCHAR(10),
    courseId VARCHAR(10),
    grade VARCHAR(10)
);


SELECT * FROM course;
SELECT * FROM teacher;
SELECT * FROM student;
SELECT * FROM student_course;