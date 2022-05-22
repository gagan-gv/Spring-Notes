# Spring

## Java Framework Principle
> Abides Hollywood Principle --> "Don't call us, we'll call you!"

![Java Frameworks](https://user-images.githubusercontent.com/60386381/169201186-29699fca-57e1-4293-adf6-320350008299.png)

## Spring Framework
> Spring is a complete and modular framework for developing enterprise applications in java.

> Spring can be used for multi-layer and particular layer real-time applications.

### Features
- Open source
- Light weight
- Comprehensive
- Framework of frameworks
- Array of resources

### Advantages
- Simplicity
    - Because it's non-invasive, i.e., not coupled with any technology
    - Uses POJO (Plain Old Java Object) or POJI (Plain Old Java Interface)
- Testability
    - Has integrated testing frameworks like JUnit.
- Java is loosely coupled and so is Spring

### Architecture / Module List
![Architecture or Module List](https://user-images.githubusercontent.com/60386381/169217351-2bf7e492-df19-4195-badf-8c64f73fed21.png)

## Aspect Oriented Programming (AOP)
- Need of implementing cross-cutting concerns

## Model View Controller
![MVC](https://user-images.githubusercontent.com/60386381/169265090-eff4bb08-0bbc-4f16-866a-d8568528422a.png)

## Auto wiring
- This is done to deal with tight coupling
- Steps:
    
    1. Identify the beans, i.e., similar classes
    2. Identify the dependency, i.e., how beans are related
    3. Use @Component for beans and @Autowire for dependencies
    
## Debug Command
- In resources folder go to application properties and type:
```
logging.level.org.springframework=debug
```
