# Spring Boot CRUD API with Angular UI

This sample project implements an application that create, update, delete and list users using rest calls. An in-memory database for the application is included. Data is created everytime the application starts and gets destroyed after the application stops. It consists of two modules, 
* usermanagement-backend which is generated with spring boot version 2.2.2. and spring data version 2.2.3.
* usermanagement-frontend which is generated with [Angular CLI](https://github.com/angular/angular-cli) version 8.3.21.
## Prerequisites
`Java 8 and Node.js`
## Build
To run the whole project with maven, cd into the project folder and run:
`mvn clean install`
The jar file is created in usermanagement-backend/target folder, then run:
`java -jar UserManagement.jar`
Navigate to below url:
`http://localhost:8080`
## Running unit tests
To test the backend rest calls, run the following command in usermanagement-backend folder:
`mvn test -Dtest=UserManagementApplicationTests#testCrudOperation`
The test method creates two users, updates the first, and deletes the second, and finally lists the users.
