# Vaadin Login
VAADIN 8 + SPRING BOOT + GRADLE + GUAVA

A simple login application created for my own curiosity and for the sole purpose of learning.
There are two methods of calling the authentication logic: by directly calling the method responsible for it or by using Guava event bus.

### Getting started

Download the project from Github.

Compile the project using the gradle task build (with "gradlew.bat build" or "gradlew build").

Run the project using the gradle task bootRun.

Access the app at http://localhost:8080.

### Configuration

H2 in memory databse settings are set in application.properties.
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

![alt text](https://user-images.githubusercontent.com/32642970/32060608-f0da373c-ba77-11e7-966f-b25dd84d0b0a.PNG)
![alt text](https://user-images.githubusercontent.com/32642970/32060609-f0f49776-ba77-11e7-875e-8041ccac1d9b.PNG)
![alt text](https://user-images.githubusercontent.com/32642970/32060610-f1213f9c-ba77-11e7-8161-26789c6b6832.PNG)
