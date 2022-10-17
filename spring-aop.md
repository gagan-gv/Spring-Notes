# Aspect Oriented Programming (AOP)
> - Programming technique based on concept of Aspect.
>   - Aspect encapsulates cross cutting logic
>   - Aspect can be reused at multiple locations

![image](https://user-images.githubusercontent.com/60386381/195970790-d8b0f4da-6508-4dc5-a919-f4a8e30399e5.png)

## AOP Solutions
- Apply proxy design pattern

## Benefits of AOP
- Code for aspect is defined in a single class
- Code is cleaner
- Configurable

## AOP Use Cases
- Logging
- Security
- Transactions
- Audit Logging
    - Who
    - When
    - What
    - Where
- Exception Handling
- API Management
    - Analytics

## AOP Advantages
- Reusable Modules
- Resolve Code Tangling
- Resolve Code Scattering
- Applied Selectively based on Configuration

## AOP Disadvantages
- Too many aspects and app flow is hard to follow
- Minor performance cost for execution

## AOP Terminology
- Aspect: Module of code for cross cutting concern
- Advice: What action is taken and when should it be applied
- Join Point: When to apply code during execution
- Pointcut: A predicate expression for where advice should be applied
- Weaving: Connecting aspects to target objects to create advised objects

### Advice Types
- Before advice: Run before the method
- After finally advice: run after the finally
- After returning advice: run after successful execution
- After throwing advice: run after an exception is thrown
- Around advice: run before and after the method

### Weaving
- Types:
    - Compile Time
    - Run Time
    - Post Compile Time

## Spring AOP VS AspectJ (Both are AOP frameworks)
### Spring AOP Support
- Key Component of Spring
    - Security, transactions, caching, etc
- Uses run time weaving of aspects
- Advantages:
    - Simpler than AOP
    - Uses Proxy pattern
    - Can migrate to AspectJ when using `@Aspect` annotation
- Disadvantages:
    - Only supports method level join points
    - Can only apply aspects to beans created by spring app context
    - Minor performance cost for app execution
Refer Spring.mdRefer Spring.md
### Aspect J
- Original AOP framework
- Rich support for join points and weaving
- Advantages:
    - Supports all join points
    - Works with POJO and POJI, ([Refer Spring.md](./spring.md#advantages)), not just beans from spring context
    - Faster performance compared to Spring AOP
    - Complete AOP Support
-Disadvantages:
    - Compile time weaving requires extra compilation step
    - AspectJ pointcut syntax can be complex

## Best Practices
- Keep the code small
- Keep the code fast
- Do not perform any expensive / slow operations
- Get in and out as quickly as possible

## Pointcut Expression Language
![image](https://user-images.githubusercontent.com/60386381/196019981-375a2ce3-56e4-43c1-ae9c-99da9b52d986.png)

> **NOTE:** `?` denotes optional

### Parameter wild card patterns
- (): Matches a method with no arguments
- (*): Matches with one argument of any type
- (..): Matches with 0 or more arguments of any type

## Before Advice
- Use cases
    - Common ones
        - Logging
        - Security
        - Transactions
    - Audit Logging
    - API Management

### Development Process
1. Create target object: Overhere AccountDAO
    ```java
    //AccountDAO.java
    @Component
    public class AccountDAO {
        public void addAccount() {
            System.out.println("Adding account to DB");
        }
    }
    ```
2. Create spring java config class
    ```java
    // class demo config

    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan("code.example.aopdemo")
    public class DemoConfig {}
    ```
3. Create main app
    ```java
    // class Main
    public class Main {
        public static void main(String[] args) {

            // read spring config java class
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

            // get the bean from spring container
            AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

            // call the logic
            accountDAO.addAccount();

            // close context
            context.close();
        }
    }
    ```
4. Create the aspect
    ```java
    // AspectLogging.java
    @Aspect
    @Component
    public class AspectLogging {
        @Before("execution(public void addAccount())")
        public void beforeAddAccountAdvice() {
            System.out.println("Logging Before");
        }
    }
    ```

## AfterReturning Advice
- Use cases
    - Common ones
        - Logging
        - Security
        - Transactions
    - Audit Logging
    - Post Processing data

### Development Process
1. Create an account class
    ```java
    // Account.java
    public class Account {
        private String name;
        private String level;

        // constructors
        // getters and seeters
        // override toString
    }
    ```
2. Add method findAccount in AccountDAO
    ```java
    //AccountDAO.java
    @Component
    public class AccountDAO {

        private String name;
        private String serviceCode;

        public List<Account> findAccounts() {
            List<Account> accounts = new ArrayList<>();

            Account a1 = new Account("ABC", "Alpha");
            Account a2 = new Account("DEF", "Beta");
            Account a3 = new Account("GHI", "Alpha");

            accounts.add(a1);
            accounts.add(a2);
            accounts.add(a3);

            return accounts;
        }

        public void addAccount() {
            System.out.println("Adding account to DB");
        }
    }
    ```
3. Update main app to call findAccounts()
    ```java
    // class Main
    public class Main {
        public static void main(String[] args) {

            // read spring config java class
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

            // get the bean from spring container
            AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

            // call the logic
            accountDAO.addAccount();

            List<Account> accounts = accountDAO.findAccounts();
            System.out.println(accounts);

            // close context
            context.close();
        }
    }
    ```
4. Add AfterReturning Advice
    ```java
    // AspectLogging.java
    @Aspect
    @Component
    public class AspectLogging {
        @AfterReturning(pointcut="execution(public void addAccount())", returning="results")
        public void afterFindAccounts(List<Account> results) {
            System.out.println("Logging After returning");
        }
    }
    ```

## Pointcut Declarations
### Development Process
1. Create target object: Overhere AccountDAO
    ```java
    //AccountDAO.java
    @Component
    public class AccountDAO {
        public void addAccount() {
            System.out.println("Adding account to DB");
        }
    }
    ```
2. Create spring java config class
    ```java
    // class demo config

    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan("code.example.aopdemo")
    public class DemoConfig {

    }
    ```
3. Create main app
    ```java
    // class Main
    public class Main {
        public static void main(String[] args) {

            // read spring config java class
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

            // get the bean from spring container
            AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

            // call the logic
            accountDAO.addAccount();

            // close context
            context.close();
        }
    }
    ```
4. Create the pointcut declaration & apply to aspects
    ```java
    // AspectLogging.java
    @Aspect
    @Component
    public class AspectLogging {
        @Pointcut("execution(public * add*(..))")
        private void pointcutDec() {}

        @Before("pointcutDec()")
        public void beforeAddAccountAdvice() {
            System.out.println("Logging Before");
        }
    }
    ```

### Combining Pointcuts
- Used when we to execute an advice only if certain conditions are met
- Uses logical operators:
    - &&
    - ||
    - !
- Works like if statement

#### Development Process
1. Create multiple pointcuts
    ```java
    @Pointcut("execution(public * add*(..))")
    private void pointcutDec1() {}

    @Pointcut("execution(private * *())")
    private void pointcutDec2() {}
    ```
2. Combine Pointcut declarations
    ```java
    @Pointcut("pointcutDec1() && pointcutDec2()")
    private void pointcutAndDec() {}
    ```

    Or

    ```java
    @Pointcut("pointcutDec1() || pointcutDec2()")
    private void pointcutOrDec() {}
    ```

    Or

    ```java
    @Pointcut("!pointcutDec1() && !pointcutDec2()")
    private void pointcutNotDec() {}
    ```
3. Apply pointcut decalrations
    ```java
    @Before("pointcutAndDec()")
    public void beforeAddAccountAdvice() {
        System.out.println("Logging Before");
    }
    ```

### Benefits
- Easily reusable
- Update pointcut in one location
- Can share and combine pointcut expressions

## Ordering Aspects
### Development Process
1. Refactor advices in different aspects
2. Add @Order annotation to the aspects

```java
// AspectLogging.java
@Aspect
@Component
@Order(2)
public class AspectLogging {
    @Before("execution(public * add*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("Logging Before");
    }
}
```
```java
// LogToCloudAspect.java
@Aspect
@Component
@Order(1)
public class LogToCloudAspect {
    @Before("execution(public * add*(..))")
    public void logToCloud() {
        System.out.println("Logging Before");
    }
}
```

## Join Points
- Used to access method parameters

### Development Process
1. Access and display method signature
2. Access and display method arguments

```java
// AspectLogging.java
@Aspect
@Component
public class AspectLogging {
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("Logging Before");

        // accessing and displaying method sigs
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println(methodSignature);

        // accessing and displaying method arguments
        Object[] args = joinPoint.getArgs();

        for(Object arg: args) {
            System.out.println(arg);

            if(arg instanceOf Account) {
                Account account = (Account) arg;
                System.out.println(account.getName());
            }
        }
    }
}
```