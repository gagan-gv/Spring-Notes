# Spring REST

## What are REST APIs?
- REST: ***Re***presentational ***S***tate ***T***ransfer
- Lightweight approach for communicating between applications
- Can use any data format, JSON is commonly used

### What is JSON?
- JSON: ***J***ava***S***cript ***O***bject ***N***otation
- It's a lightweight data format
- It's language independent
- Data Types
    - Numeric
    - String
    - Boolean / Logical
    - Arrays
    - Null
    - Object
- Eg
    ```json
    {
        "id": 2,
        "name": "John Doe",
        "active": true
    }
    ```

## Java JSON Data Binding
- Process of converting JSON to Java POJO and vice-versa
- Spring uses [Jackson Project](github.com/FasterXML/jackson-databind) BTS