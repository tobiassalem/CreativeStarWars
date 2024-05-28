## StarWarsAPI

A simple application to illustrate how to build a backend API. It is built using Spring Boot, Java and the Maven build manager.
This simple API is based on the Star Wars universe and currently supports the following requests:
* Health check: `GET /`
* Get Spring profile info: `GET /profile`
* List all characters: `GET /characters`
* Get a character by id: `GET /characters/{id}`
* List all films: `GET /films`
* Get film by id: `GET /films/{id}`


### Requirements
* Spring Boot 3.3+
* Java 17+
* Maven 3


### Building
* Install Maven if not already present on your system (if you have an IDE like IntelliJ you're all set).
* Build with `mvn install` .


### Running
* Run the Spring Boot application file in your favorite IDE. The main class is `ApiApplication`.
* Run from the terminal with the maven command `mvn spring-boot:run`
* Access the REST services on http://localhost:8080/
* To perform the service calls a REST client like Postman is highly recommended. A Postman collection is included for
  your convenience.


### References
* Spring Boot - @See https://spring.io/projects/spring-boot
* Maven build manager - @See https://maven.apache.org/
* Postman REST client - @See https://www.postman.com/
* Clean Code by Robert C. Martin - @See https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882
