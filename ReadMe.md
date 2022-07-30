# Laboremus Software Developer Case

This project was initialized with spring initiallizer using maven(3.6.3), Java 11 and Spring Boot(2.7.1)

## How to Run 

You can change directory into the project folder from a terminal and run

```
        mvn spring-boot:run 
```
The First time you run this it should install all the necessary dependencies and build the project

* Check the stdout file to make sure no exceptions are thrown

Once the application runs successfully you should see something like this

```
2022-07-18 03:54:46.075  INFO 25433 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-07-18 03:54:46.115  INFO 25433 --- [           main] c.l.sdcase.SdcaseApplication             : Started SdcaseApplication in 12.141 seconds (JVM running for 13.304)
```

## How to run Tests

From your IDE that is to say IntelliJ or VsCode with 'Extension Pack for Java' and 'Estension pack for Spring-boot' installed. 
Run tests by clicking on the Test Icon that should be visible once the project is opened and loaded within the IDE. 
This will run all the different tests in the various test packages.
Alternatively you can run tests per file by opening a test file and clicking on the test icon for each test case within the file


## API Documentation

API documentation is available at the following url http://localhost:8080/swagger-ui/index.html

