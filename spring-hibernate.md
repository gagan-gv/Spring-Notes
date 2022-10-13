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

## Mappings
- One to One
    - Ex: An instructor can have an "instructor detail entity"
    ![image](https://user-images.githubusercontent.com/60386381/195039067-2aa65a7a-bce2-4bca-93b3-1ea9969d0966.png)
- Many to One
    - Ex: N-Courses can belong to 1 instructor
- One to Many
    - Ex: An instructor can have many courses
    ![image](https://user-images.githubusercontent.com/60386381/195038922-c08c7275-a0b4-4c34-85ec-971b4c7f80f9.png)
- Many to Many
    - Ex: A course can have many students and one student can have many courses
    ![image](https://user-images.githubusercontent.com/60386381/195038794-b0d5f5ad-434d-46d9-a88d-5f8558a61f8c.png)

### Database concepts related to Mappings
- Primary and Foreign Keys
- Cascade

### One to One
- Uses `@OneToOne` for mapping

#### Entity Lifecycle
- Detach: If entity is detached, it is not associated with hibernate session
- Merge: If instance is detached from the session, then merge will reattach it to the session
- Persist: Transitions new instances to managed state. Next flush/commit will save it in database
- Remove: Transitions managed instances will be removed. Next flush/commit will remove it from database
- Refresh: Reload/sync object with data from db. Prevents stale data

![image](https://user-images.githubusercontent.com/60386381/195041145-18cdbfeb-6c1e-4e3c-9617-de187ea40f3e.png)

#### Cascade Types:
- Persist: If entity is persisted/saved, related entity will also be persisted
- Remove: If entity is removed/deleted, releated entity will also be removed
- Refresh: If entity is refreshed, related entity will also be refreshed
- Detach: If entity is detached, related entity will also be detached
- Merge: If entity is merged, related entity will also be merged
- All: All of the above cascade types

> **NOTE:** By default no operations are cascaded.

> **NOTE:** Multiple cascade types can be configured

#### Development Process (Uni-directional)
**Setup hibernate config file and JDBC connect file**
1. Define Database Tables
    ```sql
    CREATE TABLE `instructor_details` (
        `id` int(11) PRIMARY KEY AUTO_INCREMENT,
        `yt_channel` varchar(256) DEFAULT NULL,
        `hobby` varchar(50) DEFAULT NULL
    )

    CREATE TABLE `instructor` (
        `id` int(11) PRIMARY KEY AUTO_INCREMENT,
        `name` varchar(50) DEFAULT NULL,
        `email` varchar(70) DEFAULT NULL,
        `instructor_detail_id` int(11) DEFAULT NULL,
        FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_details`(`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    )
    ```
2. Create InstructorDetail Class
    ```java
    @Entity
    @Table(name="instructor_detail")
    public class InstructorDetail {

        @Id
        @Column(name="id")
        private int id;

        @Column(name="yt_channel")
        private String ytChannel;

        @Column(name="hobby")
        private String hobby;

        // create constructors
        // generate getter and setter
        // override toString()
    }
    ```
3. Create Instructor Class
    ```java
    @Entity
    @Table(name="instructor")
    public class Instructor {
        @Id
        @Column(name="id")
        private int id;

        @Column(name="name")
        private String name;

        @Column(name="email")
        private String email;

        @OneToOne(cascade=CascadeType.ALL)
        @JoinColumn(name="instructor_detail_id")
        private String instructorDetailId;

        // create constructors
        // generate getter and setter
        // override toString()
    }
    ```
4. Create Main App
    ```java
    // Demo.java
    public class Demo {
        public static void main(String[] args) {
            // create session factory
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buidSessionFactory();
            
            //create session
            Session session = factory.getCurrentSession();

            try {
                Instructor instructor = new Instructor("Chad", "chad@gmail.com");

                InstructorDetail instructorDetail = new InstructorDetails("youtube.com/myChannel", "Kuch Nahi");

                instructor.setInstructorDetail(instructorDetail);

                session.beginTransaction();

                session.save(instructor)// this will save instructor detail as well, due CASCADE.ALL

                session.getTransaction().commit();
            }finally {
                factory.close();
            }
        }
    }
    ```

#### Development Process (Bi-directional)
> **NOTE:** With Respect to above set of code

1. Update the InstructorDetail Class:
    1. Add new field to reference instructor
    2. Add getter and setter methods for instructor
    3. Add one to one annotation
    ```java
    @Entity
    @Table(name="instructor_detail")
    public class InstructorDetail {

        @Id
        @Column(name="id")
        private int id;

        @Column(name="yt_channel")
        private String ytChannel;

        @Column(name="hobby")
        private String hobby;

        @OneToOne(mappedBy="instructorDetail", cascase=CascadeType.ALL)
        private Instructor instructor;

        // create constructors
        // generate getter and setter
        // override toString()
    }
    ```
2. Create main app
    ```java
    // Demo.java
    public class Demo {
        public static void main(String[] args) {
            // create session factory
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buidSessionFactory();
            
            //create session
            Session session = factory.getCurrentSession();

            try {

                session.beginTransaction();

                int id = 1;
                InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

                System.out.println(instructorDetail);
                System.out.println(instructorDetail.getInstructor());

                session.getTransaction().commit();
            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                session.close();
                factory.close();
            }
        }
    }
    ```

##### Bi directional - Delete only data from one table
1. Change the cascade type in the table, here instructor detail
    ```java
    @Entity
    @Table(name="instructor_detail")
    public class InstructorDetail {

        @Id
        @Column(name="id")
        private int id;

        @Column(name="yt_channel")
        private String ytChannel;

        @Column(name="hobby")
        private String hobby;

        @OneToOne(mappedBy="instructorDetail", cascase={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
        private Instructor instructor;

        // create constructors
        // generate getter and setter
        // override toString()
    }
    ```
2. Update the Delete code by breaking the bidirectional link
    ```java
    // DeleteDemo.java
    public class DeleteDemo {
        public static void main(String[] args) {
            // create session factory
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buidSessionFactory();
            
            //create session
            Session session = factory.getCurrentSession();

            try {

                session.beginTransaction();

                int id = 1;
                InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

                System.out.println(instructorDetail);
                System.out.println(instructorDetail.getInstructor());

                // break bi-directional link
                instructorDetail.getInstructor().setInstructorDetails(null);
                session.delete(instructorDetail);

                session.getTransaction().commit();
            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                session.close();
                factory.close();
            }
        }
    }
    ```
