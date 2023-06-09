# User Location Problem

---

***Problem statement -*** 

``` 
Create a spring boot project using Gradle with the following requirements
1. There will be 2 users (ADMIN, READER).
2. ADMIN can perform CRUDE operations like POST, DELETE, PATCH, etc.
3. READER can perform only GET operation.
4. Create a REST API called create_data which creates a table in the database with the
   name 'user_location'.
5. user_location will have three fields NAME, Latitude, and Longitude.
6. user can update the user_location table using another REST API called update_data
7. READER can call the get_users/N which returns the nearest N users from the location
   (0,0).
   E.g GET http://base_url/get_users/5 will return the nearest 5 users from (0,0);
   You need to create 3 rest API
   a) create_data
   b) update_data
   c) get_users/N
   
   Note: Use HSQL in-memory database for the database work. your code should be able
to create a table with no manual script required.
```
---

***Requirements:***
- Java: jdk11
- Spring Boot: 2.7.10
- Gradle: 7.6.1
- Dependencies:
  - spring-boot-starter-web
  - spring-boot-starter-validation
  - javax.servlet-api
  - hsqldb
  - spring-boot-starter-data-jpa
  - lombok
  - spring-boot-starter-security
  - spring-boot-starter-test
  - spring-security-test

***Project Structure:***
user_location_ambula\src\main\java\com\example\user_location_ambula\
- Controllers: Contains *AuthController & TestController*
- Services: Contains *UserService* i.e. the business logic for all the APIs 
- Entities: Contains *UserLocation* table that we are going to store in database
- Repositories: Contains *UserRepository* that helps to perform CRUD operations on tables
- Config: Contains *Spring Security Configurations*
- Utils: Contains *Calculate Distance* helper method


