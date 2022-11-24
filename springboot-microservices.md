# Microservices
- Layman definition: Break a monolith service into multiple smaller services
    ![image](https://user-images.githubusercontent.com/60386381/199781905-87f87246-98c3-4c02-8960-d35dadb6edc1.png)
- Microservices are interdependent & follow a pattern
- One of the robust ways to build microservices in Java is using ***Spring Cloud***(is Synchronus in nature)
- Example: Movie Catalog like IMDB uses microservices

## Services VS Microservices
- **Service** is basically creating an application, which can be used in multiple applications.
    - Example: Payments gateway APIs used by multiple different kinds of platforms.
- **Microservices** is basically specific to a particular application.
    - Example: Shopping cart microservice used only by e-commerce applications.

## Development Process

1. Build all services
2. Start discovery of services
3. Configure them

### Example: Basic E-Commerce
- Services:
    - Product Service: Create and view products, acts as product catalog
    - Order Service: Can order products
    - Inventory Service: Can check if product is in stock or not
    - Notification Service: Can send notifications, after order is placed
- Sync & Async communications will be used

![image](https://user-images.githubusercontent.com/60386381/203799007-c24a5a48-5127-4412-86ae-4660e3ef4f24.png)

***[Development Code](./microservice-example/)*** 

## Communication between APIs
- Uses `RestTemplate`
- Create a RestTemplate --> Get the Object for the particular class for the template --> Stream the result to the URL

## Why not to return a list in API?
- Deserialization
- Enhancement/changes can break the code
    - So, we use an object wrapper while returning

## Why Hardcoded URLs are bad?
- Changes require code updates
- Dynamic URLs in the cloud
- Load balancing
- Multiple Environments

## Service Discovery
- Service discovery is a pattern when you build microservices, each microservice talks to each other & how they know who to talk too.
- Due to the above reasons we use service discovery.
- Spring cloud integrates with Eureka for service discovery.
![image](https://user-images.githubusercontent.com/60386381/200472720-4c7ccc24-6c62-471b-a2aa-4524b2728a0f.png)

### Development Process
1. Start Eureka Server
2. Have microservices register/publish using Eureka client
3. Have microservices locate/consume using Eureka client