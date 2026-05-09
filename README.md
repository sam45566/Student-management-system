# Student Management System

This is a Java-based Student Management System with a GUI interface (Swing) and a MySQL database backend.

## Prerequisites

* **Java Development Kit (JDK)** - Version 11 or higher
* **MySQL Server** - Version 8.0 or higher
* **MySQL Connector/J** - Use the provided `mysql-connector-j-9.3.0.jar`

## How to Set Up the Project

### 1. **Set up the Database:**

* Make sure your MySQL server is running.
* Open a MySQL client or Workbench and run these commands:
```sql
-- Create the database
CREATE DATABASE student_management;

-- Use the new database
USE student_management;

-- Import the database schema from the provided SQL file
SOURCE /path/to/your/project/student_management.sql;

```


*(Note: Ensure the database user has the correct credentials. Based on the code, the default is user `root` with password `Alex@1234`. You can update this in `DatabaseManager.java` and other frame files.)*

### 2. **Compile the Java Code:**

* Open a terminal in the project root directory.
* Run the following command to compile all Java files into a `bin/` directory:
```sh
javac -d bin -cp .;lib/mysql-connector-j-9.3.0.jar *.java

```


*(Note: Ensure the JAR file is in a folder named `lib` or adjust the path accordingly.)*

### 3. **Run the Application:**

* After compiling, run this command to start the application:
```sh
-- For Windows:
java -cp bin;lib/mysql-connector-j-9.3.0.jar StudentManagementPrototype

-- For Linux/Mac:
java -cp bin:lib/mysql-connector-j-9.3.0.jar StudentManagementPrototype

```



## Key Features

* **Student Registration:** Add new students with details such as First Name, Last Name, Department, Date of Birth, and Entry Year.
* **Search Functionality:** Find specific students by searching their first or last names through an interactive table.
* **Record Management:** View a complete list of all registered students and perform deletions if necessary.
* **Database Integration:** Persistent storage using JDBC and MySQL for reliable data handling.

## Project Structure

* `StudentManagementPrototype.java`: The main entry point of the application.
* `mainPage.java`: The primary navigation dashboard.
* `registerFrame.java`: GUI for adding new student records.
* `searchStudentFrame.java`: Interface for searching and filtering student data.
* `showAllStudents.java`: Table view to display the entire student database.
* `DatabaseManager.java`: Utility class for handling SQL connections and data operations.

---
