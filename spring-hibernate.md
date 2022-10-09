# Spring - Hibernate
- What is hibernate?
    > A framework for persisting / saving Java objects in a database
- Benefits of hibernate?
    - Handles all low level SQL
    - Minimizes the amount of JDBC code
    - Provides Object-to-Relational Mapping (ORM)

### Saving the java object with hibernate
```java
// create a java object
Student student = new Student("John", "Doe", "john.doe@example.com");

// save it to the db
int id = (Integer) session.save(student);
```

### Retrieve data as a java object with hibernate

```java
Student student = session.get(Student.class, id);
```

### Querying for java objects
```java
Query query = session.createQuery("from Student");
List<Student> students = query.list();
```

## Hibernate and JDBC
- Hibernate uses JDBC for all DB communications
![image](https://user-images.githubusercontent.com/60386381/194746933-704c3995-db31-4dc3-b52b-84020ef1195f.png)