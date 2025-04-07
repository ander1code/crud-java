# CrudJava
![Java](https://img.shields.io/badge/Java-red?logo=openjdk)
![Database: SQLite](https://img.shields.io/badge/SQLite-blue?logo=sqlite)
![Platform: Windows](https://img.shields.io/badge/Windows-blue?logo=windows) ![Linux](https://img.shields.io/badge/Linux-black?logo=linux&logoColor=yellow) 
![Last Commit](https://img.shields.io/github/last-commit/example/crudjava?color=yellow&logo=github) ![Size](https://img.shields.io/github/repo-size/example/crudjava?color=blue&logo=files) ![License](https://img.shields.io/github/license/example/crudjava?color=black&logo=open-source-initiative)

## 1. Description
A **CRUD application prototype** developed in **pure Java**, utilizing an **SQLite3 database** for individual registration. The project is built for simplicity and functionality, featuring robust data handling and validation.

## 2. Features
- **Data Validation:** Ensures input accuracy and consistency.
- **Transactional Support:** Maintains data integrity during operations.
- **Data Registration:** Allows the registration of individuals with relevant information.
- **Search Functionality:** Enables searches by name and ID for streamlined data access.

## 3. Project Structure (CrudJava Folder)
- **IDE:** Developed using NetBeans 8.2.
- **Components:**
  - Project interfaces and classes.
  - **Database Connection:** Utilizes `sqlite-jdbc-3.21.0.jar` for connecting to SQLite3.
  - **Database:** Includes `db.sqlite3` for storing project data.

## 4. Deployment Structure (BIN Folder)
- **Executable Files:**
  - `crudjava.jar` - Main application file.
  - `crudjava.exe` - Executes the JAR file in a Windows environment using Java.
- **Database File:** Includes `db.sqlite3` for application data.
- **Libraries:**
  - `sqlite-jdbc-3.21.0.jar` used for database connection.

## 5. Tools and Technologies Used
- **IDE:** NetBeans 8.2
- **Java Development Kit (JDK):** Version 1.8.0_152
- **Database Driver:** SQLite JDBC Driver 3.21.0
- **Database Management:** SQLite3
- **Database Utility:** SQLiteStudio v3.1.1
