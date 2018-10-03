# Recruit Module
* This application provides APIs for creation of job offers and enabling candidates to apply them.
* It uses Spring boot, Spring data and h2 for in-memory persistence

## API information
* This application has Swagger integrated into it. Visit(once the application is started/deployed) either `http://localhost:8080` or `http://localhost:8080/swagger-ui.html` in the browser. It will provide all available API end-points.

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
## Sample requests
* To create a job offer `POST` request to `http://localhost:8080/v1/offers`

```
{
	"jobTitle":"Senior Software Engineer I",
	"startDate":"2018-10-23T09:21:59.332043"
}
```
The start date is a required.

* To apply for a job offer `POST` request to `http://localhost:8080/v1/jobapplications/apply`

```
{
  "email": "candidate101@mail.com",
  "offerDTO": {
    "id": 1001
  }
}
```
  
* To view/track the status and details of a job application `GET` request to `/v1/jobapplications/10001`
* To view the number of applications for an offer and other details about a job offer `GET` request to `/v1/offers/1001` will work.

```
{
  "id": 1001,
  "jobTitle": "Senior Software Engineer I",
  "startDate": "2018-10-23T09:21:59.332043",
  "jobApplicationsCount": 3,
  "jobApplications": []
}
```
* For any status change regarding the job application, a notification will be triggered through the `NotificationService#notify`. This will be logged in the console.
* The `src/main/resources/data.sql` file has some sample data available. They are inserted automatically when the application starts.

## Design
* This application has used Spring boot to provide the REST-API interface.
* This has controllers providing the mapping between the endpoints and services.
* Services and their implementation provide the business logic to perform necessary operations on job applications, offers.
* Spring data is used as the layer of persistence.
* All the services and controllers have integration tests.
* An ExceptionHandlingController(ControllerAdvice) is added which would intercept all the exceptions bubbling up from the controllers and return appropriate error codes based on their types. They can be extended to log the exceptions/errors to any external system.
* This implementation does not have security implemented currently. Security is of utmost importance in this application. Except for the end point to apply a job offer, all others must be secured and should be available based on the ROLE of the user and ownership of the resource(job application) in question. 

## Persistence
* If you want to check the tables or schema please go to `localhost:8080/h2-console` and provide the connection URL/credentials based on the profile that the application is running. By default it is dev, so the url would be `jdbc:h2:mem:devdb`. The credentials can be found in the application-dev.properties for this. *We should never store the credentials of a database(any credentials for that matter) in the properties file.* I have done that in this application for demonstration purposes.
* One important thing to remember here is that since the database is in-memory, we can not access the database when the application is not running.

## Formatter
The formatter for the application is available in the `/etc` folder. Please use that to format the application when importing into an IDE. I have used `Eclipse` to develop the application.
