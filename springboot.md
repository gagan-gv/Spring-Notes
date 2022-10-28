# Spring Boot

## Problem with Spring?
- Which archetype to choose
- Which dependencies
- Configuration (With/Without XML)
- Which Server & Installation
- Error prone
- etc...

## Spring Boot Solutions
- Easier to start with spring development
- Minimal Configs
- No dependency conflicts (Maven or Gradle)
- Provides embedded server
- Provides [Spring Initializer](https://start.spring.io)
    - Quickly create spring projects
- Includes auto config for template engines like
    - Thymeleaf
    - FreeMarker
    - Mustache

## Spring Boot Dependencies
> **NOTE:** They are one stop shop for multiple dependencies

- Spring Boot Starters Web (spring-boot-starter-web)
    - Web MVC
    - Tomcat
    - Spring Web
    - Hibernate Validator
    - JSON
    - etc ...
- Security (spring-boot-starter-security)
- Data JPA (spring-boot-starter-data-jpa)
    - Hibernate
    - JPA
- etc...

## Spring Boot Dev Tools
- Automatically restarts application on updates

## Spring Boot Actuator
- Exposes endpoints to monitor and manage
- DevOps functionality
- There are 10+ actuator end points

## Spring Boot Core Properties
> **NOTE:** Used in `application.properties` file
- Logging
    - Logging Levels
        - DEBUG
        - TRACE
        - INFO
        - WARN
        - ERROR
        - OFF
        - FATAL
    ```properties
    logging.level.org.springframework=DEBUG
    #storing all the logs
    logging.file=application.log
    ```
- Server
    ```properties
    # changing server port number
    server.port=7070
    # changing default context path
    server.servlet.context-path=/my-app
    # setting session time out // default is 30mins
    server.servlet.session.timeout=15m
    ```
- Actuator
    ```properties
    # include end points by name or wildcard
    management.endpoints.web.exposure.include=*
    # exclude end points by name or wildcard
    management.endpoints.web.exposure.exclude=beans,info
    # basepath for actuator endpoints
    management.endpoints.web.base-path=/actuator
    ```
- Security
    ```properties
    # setting default username
    spring.security.user.name=admin
    # setting default password
    spring.security.user.password=secret
    ```
- JDBC
    ```properties
    # jdbc url
    spring.datasource.url=jdbc:mysql://localhost:3306/db
    # login username
    spring.datasource.username=alpha
    # login password
    spring.datasource.password=beta
    ```
## Java Persistence API (JPA)
- Standard API for ORM
- Only a specification
    - Defines a set of interfaces
    - Requires an implementation to be usable

### Advantages
- Maintain portable & flexible code
- Auto datasource config

## Resource
[**Spring Boot Reference Manual**](https://spring.io/projects/spring-boot)