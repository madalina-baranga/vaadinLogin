# Vaadin Login
VAADIN 8 + SPRING BOOT + GRADLE + GUAVA

### Description
A simple login application created for my own curiosity and for the sole purpose of learning.
There are two methods of calling the authentication logic: by directly calling the method responsible for it or by using Guava event bus.

### Getting started

Download the project from Github.

Compile the project using the gradle task build (with "gradlew.bat build" or "gradlew build").

Run the project using the gradle task bootRun.

Access the app at http://localhost:8080.

### Configuration

H2 in memory databse settings are set in application.properties
Use import.sql to add more users to the application

### Run the project

Run the main class LoginApplication or run the gradle task bootRun. Then access it at 
http://localhost:8080.

### Built With
* Gradle
* Vaadin 8
* SpringBoot
* Spring Data JPA
* H2
