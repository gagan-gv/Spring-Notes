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

## Model View Controller (MVC)
![MVC](https://user-images.githubusercontent.com/60386381/169265090-eff4bb08-0bbc-4f16-866a-d8568528422a.png)

### Model
- Contains your data
- Data can be any Java object or collection

### View template
- Spring MVC is flexible
    - Supports multiple view templates
    - Most common is JSP(Java Server Pages) + JSTL(JSP Standard Tag Library)
    - Other templates:
        - Thymeleaf
        - Groovy
        - Velocity
        - Freemaker
- Displays data

### Controller
- Contains the business logic
    - Handles the request
    - Store and retrieve data
    - Place data in model
- Send appropriate view template
- Uses `@Controller` annotation

## Reading Data from HTML
1. **Create Controller class**
2. **Show HTML Form using JSP**
    1. Create controller method to show form
    2. Create view page for form
3. **Process HTML Form** *(Call from action attribute of form tag from HTML with request params in jsp as example shown below)*
    1. Create controller method to process form
    2. Develop view page for confirmation

    `Student Name: {params.studentName}`

### Adding data to Spring Model
- Access the HttpServletRequest class and Model class

**Java Code**
```java
@RequestMapping("/processForm")
public String processForm(HttpServletRequest request, Model model) {
    String name = request.getParameter("studentName");
    name = name.toUpperCase();
    model.addAttribute("message", name);
    return "helloworld"; //sending it to the required view template
}
```
**JSP Code**
- Reading the parameter
```html
<html>
    <body>
        Hello the world of Spring
        <hr />
        Name: {params.studentName}
    </body>
</html>
```
- Reading after the parameter is customized
```html
<html>
    <body>
        Hello the world of Spring
        <hr />
        Name: ${message}
    </body>
</html>
```

### Binding Request Params
- Using `@RequestParam()` annotation

**Java Code**
```java
@RequestMapping("/processForm")
public String processForm(@RequestParam("studentName") String name, Model model) {
    
    name = name.toUpperCase();
    model.addAttribute("message", name);
    return "helloworld";//sending it to the required view template
}
```
**JSP Code**
```html
<html>
    <body>
        Hello the world of Spring
        <hr />
        Name: ${message}
    </body>
</html>
```

### Controller Level Request Mapping
- Creating a parent path for each method in a class to group them
- This helps to resolve conflicting paths

**Java Code**
```java
@Controller
@RequestMapping("/parent")
public class HelloController {
    @RequestMapping("/showForm")
    public String showForm() {
        ...
    }
    @RequestMapping("/processForm")
    public String processForm(@RequestParam("studentName") String name, Model model) {
        ...
    }
}
```
- The URL paths would be `/parent/showForm` and `/parent/processForm`

## Auto wiring
- This is done to deal with tight coupling
- Steps:
    
    1. Identify the beans, i.e., similar classes
    2. Identify the dependency, i.e., how beans are related
    3. Use @Component for beans and @Autowire for dependencies

- Uses `@Autowired` annotation
    
## Debug Command
- In resources folder go to application properties and type:
```
logging.level.org.springframework=debug
```

## Actuator --> Monitoring Tool (Collect Dependency XML Codes from Maven Repo)
1. Starter Actuator --> Collects all API Method (http://localhost:8080/actuator)
2. Hal Browser --> To visualize data (http://localhost:8080/browser/index.html#)

## 2 Ways to connect with the database
- EntityManager --> Needs a manual set-up
- JpaRepository --> Internally communicates to the EntityManager
