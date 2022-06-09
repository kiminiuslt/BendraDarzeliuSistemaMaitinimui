# Common Kindergarten Feeding System
WEB for Spring MVC project

### Requirements
* Language Adopt Open JDK 11 Hotspot
* PostgreSQL server

### Access the application
http://localhost:8080

## How to run application

### Run postgres

Start your postgesql server with username and password

### Initialize database

Run all SQL script's files in directory by this order:

1.  src/main/resources/db/initial-db-schema.sql

2.  src/main/resources/db/real-data.sql

3.  src/main/resources/db/demo_data.sql


### Run application

In program run configuration define VM options:

-DdbUser=yourPostgesqlUser 

-DdbPass=yourPostgesqlPassword
