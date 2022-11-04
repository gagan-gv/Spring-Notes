# Microservices
- Layman definition: Break a monolith service into multiple smaller services
    ![image](https://user-images.githubusercontent.com/60386381/199781905-87f87246-98c3-4c02-8960-d35dadb6edc1.png)
- Microservices are interdependent & follow a pattern
- One of the robust ways to build microservices in Java is using ***Spring Cloud***
- Example: Movie Catalog like IMDB uses microservices

## Services VS Microservices
- **Service** is basically creating an application, which can be used in multiple applications.
    - Example: Payments gateway APIs used by multiple different kinds of platforms.
- **Microservices** is basically specific to a particular application.
    - Example: Shopping cart microservice used only by e-commerce applications.

## Development Process
**For Movie Catalog Application**
1. Build Movie Catalog Service
2. Build Movie Info Service
3. Build Ratings Service
4. Make movie catalog call other two services

![image](https://user-images.githubusercontent.com/60386381/199886481-d8654ce4-9765-439e-bc78-4cb06840ae03.png)

***[Development Code](./microservice-example/)*** 

## Communication between APIs
- Uses `RestTemplate`
- Create a RestTemplate --> Get the Object for the particular class for the template --> Stream the result to the URL