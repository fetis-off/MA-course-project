# Online Bookstore RESTful API

## Project Overview

The Online Bookstore RESTful API allows developers to integrate book browsing, 
purchasing, and user management features into their applications. 
This API supports standard CRUD operations, user authentication, and order handling.

## Technologies Used

- **Spring Boot** - Backend framework for building the core application
- **Spring Security** - For managing authentication and authorization
- **Spring Data JPA** - For interacting with the database
- **MySQL** - Database to store books, categories and users information
- **Docker** - Containerization for easy deployment
- **Swagger** - API documentation and testing
- **MapStruct** - Object mapping
- **Liquibase** - Database change management
- **JUnit & Mockito** - For testing

## Controllers Functionality

### For users

- **Book browsing**: Browse books and search them by category
- **Cart management**: Add books to shopping cart, remove them, or adjust the desired quantity.
- **Order creation**: Place orders for the books in shopping cart and track their status.

### For admins:
- **Book management**: Add books, change information about them, delete them.
- **Category management**: Add categories, change information about them, delete them.
- **Order creation**: Update orders status 

## Requirements

- **Java** version 17 and higher
- **Maven** for dependency management
- **Docker** to prepare the environment

## How to set up

1. Clone repository
    ```bash
       git clone https://github.com/fetis-off/course-project.git
   ```

2. Create .env file for environment by filling the .env.template
    ```
    MYSQLDB_DATABASE=
    MYSQLDB_USER=
    MYSQLDB_PASSWORD=
    MYSQLDB_ROOT_PASSWORD=

    MYSQLDB_PORT=
    DEBUG_PORT=

    MYSQLDB_LOCAL_PORT=
    MYSQLDB_DOCKER_PORT=
    SPRING_LOCAL_PORT=
    SPRING_DOCKER_PORT=
   ```
3. Build and then start the containers by using Docker Compose
    ```
   docker-compose build
   docker-compose up
   ```
4. The application will be accessible at `http://localhost:<YOUR_PORT>/api`.

## Testing

1. You can run tests to check if everything is working as it should be by executing following:
    ```bash
      mvn clean test
    ```
2. If there are any trouble of understanding
   the purpose of specific controller or
   endpoint you can check out swagger by visiting:
   http://localhost:8080/swagger-ui/index.html

## Challenges Faced

1. Data Modeling and Database Relationships
Building a clear and flexible data model for the bookstore was challenging, 
particularly in representing relationships among entities. 

2. Authentication and Authorization
Setting up secure, JWT-based authentication was crucial but challenging. 
Ensuring proper role-based access control.

3. Managing Environment Variables Securely
Handling environment variables in Docker can be tricky, 
especially when trying to prevent sensitive information 
from being hard-coded into Dockerfiles or images.

## Postman collection

I've provided Postman collection with all the API requests that can be used. 
It's located in `postman` folder.

### How to use it

1. **Locate the collection**: The Postman collection is located in the `postman` folder of the project.
    - File path: `./postman/Book Store.postman_collection.json"`
2. **Import the collection**
    - In the upper left corner of Postman use `File -> Import` or just press `ctrl + o` to import the collection

---

Working on the bookstore project was an invaluable experience that significantly deepened my understanding 
of application development. Through this project, I explored a diverse set of libraries and frameworks, 
gained a great understanding of their functionalities, and learned how to apply them effectively in real-world scenarios.