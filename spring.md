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
```JSP
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

### Form tags and Data binding
#### Text Fields
1. Create getters and setters
    - For e.g., Student Class

```java
//student class
public class Stduent {
    private String firstName;
    private String lastName;

    public Student() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
```
2. Create controller class
```java
// STudent Controller
@Controller
@RequestMapping("/student")
public class StudentController {
    @RequestMapping("/")
    public String showForm(Model model) {

        //create a student object
        Student student = new Student();

        //add student object to the model
        model.addAttribute("student", student);

        return "student-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {
        // log input data
        //System.out.println(student.getFirstName() + " " + student.getLastName());
        return "student-confirmation";
    }
}
```

3. Create HTML form using JSP
```JSP
<!-- student-form.jsp -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Student Registration form</title>
    </head>
    <body>
        <form:form action="processForm" modelAttribute="student">
            First Name: <form:input path="firstName" />
            Last Name: <form:input path="lastName" />

            <input type="submit" value="Submit" />
        </form:form>
    </body>
</html>
```

4. Create a confirmation page using JSP
```JSP
<!--student-confirmation.jsp-->
<!DOCTYPE html>
<html>
    <head>
        <title>Student Confirmation</title>
    </head>
    <body>
        Student: ${student.firstName} ${student.lastName}
    </body>
</html>
```

#### Dropdown
1. Update Student class with a variable nationality
```java
import java.util.Locale;
//student class
public class Stduent {
    private String firstName;
    private String lastName;
    private String country;

    private LinkedHashMap<String, String> countryNames;

    public Student() {
        // populate countryNames: used ISO country code
        String[] countryCodes = Locale.getISOCountries();
        countryNames = new LinkedHashMap<>();

        for (String cc : countryCodes) {
            // country name , country code map
            countryNames.put(cc.toUpperCase(), new Locale("", cc).getDisplayCountry());
        }

        //countryNames.forEach((k, v) -> System.out.println(k + " " + v));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public LinkedHashMap<String, String> getCountryNames() {
        return countryNames;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
```

2. Update the JSP form
```JSP
<!-- student-form.jsp -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Student Registration form</title>
    </head>
    <body>
        <form:form action="processForm" modelAttribute="student">
            First Name: <form:input path="firstName" />
            Last Name: <form:input path="lastName" />
            Country: 
            <form:select path="country">
                <form:option items="${student.countryNames}" />
            </form:select>
            <input type="submit" value="Submit" />
        </form:form>
    </body>
</html>
```

3. Update Confirmation Page
```JSP
<!--student-confirmation.jsp-->
<!DOCTYPE html>
<html>
    <head>
        <title>Student Confirmation</title>
    </head>
    <body>
        Student: ${student.firstName} ${student.lastName}
        Country: ${student.country}
    </body>
</html>
```

#### Radio Buttons and checkboxes
1. Similarly create variables in Student class and generate Getters and Setters
2. Add `<form:radiobutton path="favLang" items="${student.favLang}" />` or `<form:checkbox path="OS" items="${student.OS}">` in the JSP form
3. Update the confirmation page

```JSP
<!--student-confirmation.jsp-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Student Confirmation</title>
    </head>
    <body>
        Student: ${student.firstName} ${student.lastName}
        Country: ${student.country}
        Fav Lang: ${student.favLang}
        <!--To display arrays-->
        <ul>
            <c:forEach var="temp" items="${student.OS}">
                <li>${temp}</li>
            </c:forEach>
        </ul>
    </body>
</html>
```

## Form Validation
- Standard Bean Validation API
    - Defines metadata model and API for entity validation
    - Not tied to either the web tier or the persistence tier
- Features
    - Required
    - Validation Length
    - Validate Numbers
    - RegEx
    - Custom Validation
- Annotations
    - @NotNull
    - @Min
    - @Max
    - @Size
    - @Pattern
    - @Future
    - @Past
    - etc

### Handle String input for Integer classes
1. Create custom error messages
    - src/resources/messages.properties
    ```properties
    #messages.properties

    typeMismatch.customer.freePasses = Should be a number
    typeMismatch.customer.postalCode = Should be a number
    ```
2. Load custom message resource in config file
    - WebContent/WEB-INF/spring-mvc-demo-servlet.xml
    ```xml
    <!--spring-mvc-demo-servlet.xml-->
    <!--Add follwing codes to it-->
    <!--Load custom message resources-->
    <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="basenames" value="resources/messages" />
    </bean>
    ```

### Custom Validation
1. Create custom validation rule
    ```java
    //CourseCode annotation interface
    @Constraint(validatedBy=CourseCodeConstraintValidator.class)
    @Target( {ElementType.METHOD, ElementType.FIELD} )
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CourseCode {
        //define default course code
        public String value() default "GAG";

        //define default error message
        public String message() default "Must start with GAG";

        //define default groups
        public Class<?>[] groups() default {};

        //define default payload
        public Class<? extends Payload>[] payload() default {};
    }
    ```
2. Create constraint validator
    ```java
    //CourseCodeConstraintValidator class
    public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
        private String coursePrefix;

        @Override
        public void initialize(CourseCode courseCode) {
            coursePrefix = courseCode.value();
        }

        @Override
        public boolean isValid(String code, ConstraintValidatorContext message) {
            boolean res;

            if(code != null) {
                res = code.startsWith(coursePrefix)
            }else {
                res = true;
            }

            return res;
        }
    }
    ```

### Development Process
1. Add validation rule to the class
    ```java
    // customer class
    public class Customer {
        @NotNull(message="Is required")
        @Size(min=2, message="Min name size should be of length 2")
        private String firstName;

        private String lastName;

        @Min(value = 1, message="Should be greater than 0")
        @Max(value = 10, message="Should be lesser than or equal to 10")
        private int freePasses;

        @NotNull(message="Is required")
        @Pattern(regexp="^[1-9][0-9]{5}", message="Postal Code should be of length 6")
        private Integer postalCode;

        @CourseCode
        private String courseCode;

        // getter and setter methods
        ...
    }
    ```

2. Display error message on the form
    ```JSP
    <!--customer-form.jsp-->
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!--Add HTML and BODY and STYLE tags -->
    <form:form action="processForm" modelAttribute="customer">
        First Name(*): <form:input path="firstName" />
        <form:errors path="firstName" cssClass="error" />

        <br/>

        Last Name: <form:input path="lastName" />

        <br/>

        Free Passess: <form:input path="freePasses" />
        <form:errors path="freePasses" cssClass="error" />

        <br/>

        Postal Code(*): <form:input path="postalCode" />
        <form:errors path="postalCode" cssClass="error" />

        <br/>

        Course Code(*): <form:input path="courseCode" />
        <form:errors path="courseCode" cssClass="error" />

        <input type="submit" value="Submit" />
    </form:form>
    ```

3. Perform validation in controller class
    ```java
    //customer controller class
    @Controller
    @RequestMapping("/customer")
    public class CustomerController {

        // Adding initbinder to trim the whitespaces
        @InitBinder
        public void initBinder(WebDataBinder db) {
            StringTrimmerEditor editor = new StringTrimmerEditor(true);

            db.registerCustomEditor(String.class, editor);
        }

        @RequestMapping("/showForm")
        public String showForm(Model model) {
            model.addAttribute("customer", new Customer());

            return "customer-form";
        }

        @RequestMapping("/processForm")
        public String processForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
            if(bindingResult.hasErrors()) {
                return "customer-form";
            }
            return "customer-confirmation";
        }
    }
    ```

4. Update confirmation page
    ```JSP
    <!--customer-confirmation.jsp-->
    <!DOCTYPE html>
    <html>
        <head>
            <title>Customer Confirmation</title>
        </head>
        <body>
            Customer: ${customer.firstName} ${customer.lastName}
            Passes: ${customer.freePasses}
            Postal Code: ${customer.postalCode}
            Course Code: ${customer.courseCode}
        </body>
    </html>
    ```

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

## Actuator --> Monitoring Tool (Collect Dependency XML Codes from Maven Repo)
1. Starter Actuator --> Collects all API Method (http://localhost:8080/actuator)
2. Hal Browser --> To visualize data (http://localhost:8080/browser/index.html#)

## 2 Ways to connect with the database
- EntityManager --> Needs a manual set-up
- JpaRepository --> Internally communicates to the EntityManager
