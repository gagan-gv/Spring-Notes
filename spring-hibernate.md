# Spring - Hibernate
- What is hibernate?
    > A framework for persisting / saving Java objects in a database
- Benefits of hibernate?
    - Handles all low level SQL
    - Minimizes the amount of JDBC code
    - Provides Object-to-Relational Mapping (ORM)

## Hibernate and JDBC
- Hibernate uses JDBC for all DB communications
![image](https://user-images.githubusercontent.com/60386381/194746933-704c3995-db31-4dc3-b52b-84020ef1195f.png)

## Working with Hibernate
### Creating config files
```xml
<!--Hibernate config file: hibernate.cfg.xml-->
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>

    <session-factory>

        <!-- JDBC Database connection settings -->
        <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/student_tracker?useSSL=false&amp;serverTimezone=UTC</property>
        <property name="connection.username">root</property>
        <property name="connection.password"></property>

        <!-- JDBC connection pool settings ... using built-in test pool -->
        <property name="connection.pool_size">1</property>

        <!-- Select our SQL dialect -->
        <property name="dialect">org.hibernate.dialect.MySQLDialect</property>

        <!-- Echo the SQL to stdout -->
        <property name="show_sql">true</property>

		<!-- Set the current session context -->
		<property name="current_session_context_class">thread</property>
 
    </session-factory>

</hibernate-configuration>
```

### Setting Hibernate Annotations
#### Terminologies
- Entity Class: Java class mapped to the table in DB
    - Map class to db table
        - Uses `@Entity` and `@Table(name="tableName")`
    - Map fields to db columns
        - Uses `@Id` for marking the primary key and `@Column(name="columnName")` to determine the column name
- ID Generation Strategies
    - GenerationType.AUTO: Pick an appropriate strategy for a particular db
    - GenerationType.IDENTITY: Assign primary keys using db identity column
    - GenerationType.SEQUENCE: Assign primary keys using db sequence
    - GenerationType.TABLE: Assign primary keys using an underlying database table to ensure uniqueness
```java
// Model: Student.java
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="_name")
    private String name;

    @Column(name="email")
    private String email;

    public Student() {}

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student [id = " + id + " name = " + name + " email = " + email + "];
    }

    // generate getters and setters too
    
}
```
### Hibernate CRUD
#### Terminologies
- SessionFactory: 
    - Creates session objects
    - Reads hibernate config file
    - Heavy-weight object
    - Create only once in app
- Session:
    - Wraps a JDBC connection
    - Main object used to save/retrieve objects
    - Short lived object
    - Retrieved from SessionFactory

#### Saving object in DB (C)
```java
//HibernateCreateDemo.java
public class HibernateCreateDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buidSessionFactory();
        
        //create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object
            // create a student object
            Student s = new Student("ABC", "abc@example.com");

            // start a transaction
            session.beginTransaction();

            // save the object in DB
            session.save(s);

            //commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
```

#### Retrieving an Object (R)
```java
//HibernateReadDemo.java
public class HibernateReadDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buidSessionFactory();
        
        //create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object
            // create a student object
            Student s = new Student("ABC", "abc@example.com");

            // start a transaction
            session.beginTransaction();

            // save the object in DB
            session.save(s);

            //commit transaction
            session.getTransaction().commit();


            // code for retrieving data
            System.out.println("Get student ID: " + s.getId());

            // create a new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve data
            Student myStudent = session.get(Student.class, s.getId());

            //end session
            session.endTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
```

##### Hibernate Query Language (HQL)
- Used for retrieving data
- Similar in nature to SQL
- Helps to retrieve multiple rows of data

```java
/*QueryStudentDemo.java*/
public class QueryStudentDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buidSessionFactory();
        
        //create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> students = session.createQuery("from Student").getResultList();

            // display each student data
            for(Object o: students) {
                System.out.println(o);
            }

            // With a specific name
            students = session.createQuery("from Student s where s.name='John Doe'").getResultList();

            // display each student data
            for(Object o: students) {
                System.out.println(o);
            }

            // with a specific email domain
            students = session.createQuery("from Student s where s.email LIKE '%example.com'").getResultList();

            // display each student data
            for(Object o: students) {
                System.out.println(o);
            }

            //commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
```

#### Updating an Object (U)
```java
//HibernateUpdateDemo.java
public class HibernateUpdateDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buidSessionFactory();
        
        //create session
        Session session = factory.getCurrentSession();

        try {
            int studentID = 1;
            // begin a transaction
            session.beginTransaction();

            // retrieve data
            Student myStudent = session.get(Student.class, s.getId());

            // update data
            myStudent.setName("Shaggy");

            // end session
            session.endTransaction().commit();

            // query updation
            session = factory.getCurrentSession();

            // begin a transaction
            session.beginTransaction();

            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            // end session
            session.endTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
```

#### Deleting Object(s) (D)
```java
//HibernateDeleteDemo.java
public class HibernateDeleteDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buidSessionFactory();
        
        //create session
        Session session = factory.getCurrentSession();

        try {
            int studentID = 1;
            // begin a transaction
            session.beginTransaction();

            // retrieve data
            Student myStudent = session.get(Student.class, s.getId());

            // delete data
            myStudent.delete(myStudent);

            // end session
            session.endTransaction().commit();

            // query deletion
            session = factory.getCurrentSession();

            // begin a transaction
            session.beginTransaction();

            session.createQuery("delete from Student where id = 2").executeUpdate();

            // end session
            session.endTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
```
