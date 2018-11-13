# Appótekið
Project for Software Project class in the University of Iceland.

## How do I run this ?
This project is setup using [Maven](https://maven.apache.org/what-is-maven.html) as a dependency manager, so if your IDE does not manage that, or you don't have it installed you can look [here](https://maven.apache.org/install.html) for further information.
When all the dependencies are downloaded, you can run the project by running the ``main()`` method in the class ``Application`` and then enter [localhost:8080](http://localhost:8080) into the address bar of your favorite web browser.

### Database
This project runs a postgres database initialized with medicines after first build. After that will only update it. If the project is restarted, then all data will be NOT reset, i.e. data will persist between restarts.
If you look at the [application.properties](https://github.com/rafno/hugbo-1/blob/master/src/main/resources/application.properties) file.
It is perfectly fine to drop all databases and rerun the project. All medicines will be imported via the data.sql file.
Users and user accounts are not imported via the data.sql and as such will be lost if the users database is dropped.

## What is this application showing ?  
This application is a demonstration of using [Spring Boot](https://projects.spring.io/spring-boot/) to handle the full stack for the webapp. It handles the frontend using Jasper for jsp pages, which 
allows for dynamic pages. It handles the backend with MVC pattern and easy to use annotations, and interfaces to a repository of our choice. All login and security is handled via Spring Security.

You can search for any medicine for sale in Iceland, with a select few showing what we would have done with additional information.
Searching for 'Sobril' and 'Viagra' will include a link to serlyjaskra.is pdf files with information about that medication

If the user creates an account, the information about that user is stored in a database and his password encrypted.
The user can then login and add medicines to his account and be reminded of when to take said medicine via an email service.

