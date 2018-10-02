# Recruit Module
* This application provides APIs for creation of job offers and enabling candidates to apply them.
* It uses Spring boot, Spring data and h2 for in-memory persistence
# Build and deploy
## Method 1 - Spring boot-Maven plugin
* Simply type `mvn spring-boot:run`
## Method 2 - Packaging the application using maven and running them as a Java jar
* `mvn clean package` will run the tests, and package the application into a jar and put it in the target folder
* We can run it through the command `java -jar target/recruit.jar
## Method 3 - Using Docker
* Make sure Docker is installed in the machine.
* Execute the command `mvn install dockerfile:build`
* After the image is built, you can check it by `docker images`. This will list the images in the machine and an image named `hr/recruit` should be present in the list
* Finally run it by `docker run -p 8080:8080 -t hr/recruit`
* If the service is not responding check if your firewall is blocking the requests from the container.
## Managing different profiles
* An application has multiple profiles. Different configurations for different environments. This can be managed by using the profile management mechanism provided by Spring.
* This application has dev, stage and prod profiles. By default `dev` is always set. But we can pass our own profiles too.
* When running the docker container we can pass the profile by using the following command
 
```
$ docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t hr/recruit
```

* When using the jar command

```
java -jar -Dspring.profiles.active=prod target/recruit.jar
```

* When using spring boot-maven plugin

```
 mvn spring-boot:run -Dspring.profiles.active=stage
```
