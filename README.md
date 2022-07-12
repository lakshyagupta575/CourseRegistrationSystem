# Course Registration System

## Description

The app provides backend support for Student Registration. It provides the following features:

* An admin who can: 
    * **addStudent**
    * **addCourse** 
    * **addTeacher**  
    
    *NOTE: add the course prior to adding a teacher since a teacher needs to be assigned a course while being registered.*

* Students can:
    * view their details
    * register for a course
    * view total fees
    * view all the registered courses
    * view grade for a particular course

* Teachers can: 
    * view the list of courses assigned to the teacher
    * view the students who have registered under his course
    * provide grade to a student
    * change the course being taught by him/her

## Pre-requisites to running the App

* Create a new database **crs_db**.
* Add the tables given in the attatched MySQL file to the database.
* Go to the **confguration.yml** file and change the MySQL **username** and **password** as required.

## How to start the App?

* Download the source code.
* Run maven install and update project folder.
* Create a new run confguration with **server** and **configuration.yml** as the arguments.
* Run the created configuration.

## How to use the App?

* Download Postman and hit the desired APIs!
