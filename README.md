# chota-url-app

The project is based on a small web service which uses the following technologies:

Java 1.8

Spring MVC with Spring Boot

Spring data JPA

thymeleaf template engine

Database H2 (In-Memory)

Maven

Funtionality:

As a user I will be able to go to a page, type in a URL and get a short URL out of it. I will also
be shown all existing short URLs and the number of times it was used. When anyone opens
the short URL, they will be redirected to the initial URL that was used to generate a short
URL.

The architecture of the web service is built with the following components:

DataTransfer Business Objects: Objects which are used for outside communication via the API
Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
Service: Implements the business logic and handles the access to the EntityObjects.
EntityObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.

How to start the app
You should be able to start the example application by executing com.bsahu.chotaurl.ChotaurlAppApplication, which starts a webserver on port 8080 (http://localhost:8017)

