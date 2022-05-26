# TMS-backend
Is a Test Management System where users can store test cases.
The goal of this project is to learn how spring microservices works and interacts with each other.

Backend contains of services:
- config-service - stores configuration in github repository
- discovery-service - Eureka discovery service
- gateway-service - routes requests to downstream services, validates request token
- users-service - handles authorization, authentication and management users in the system
- tests-service - handles test cases
- export-service, export-service - todo


## To run all services with docker compose locally:

1. Install and start docker on the machine https://docs.docker.com/docker-for-mac/install/
2. To build the project execute form root folder (skipping test):

```
    mvn clean -Dmaven.test.skip=true install
```

3. To push created jars to dockerhub (optional):

 ``` 
    mvn dockerfile:push 
```

Note:
Dockerhub credentials should be provided on `~/.m2/settings.xml`:

 ```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>registry.hub.docker.com</id>
            <username>USERNAME</username>
            <password>PASSWORD</password>
            <configuration>
                <email>EMAIL</email>
            </configuration>
        </server>
    </servers>
</settings>
```

4. Run docker-compose from root folder:

```
export GIT_REPO_URL="https://github.com/shavrova/TMSConfigRepository" && export GIT_USERNAME="shavrova"  && export GIT_PASSWORD="<github-access-token>" && docker-compose up --build -d
```

5. Go to eureka service to check services status:
   `http://root:root@localhost:7002`
6. To check user service send POST to `http://localhost:7003/users-service/api/register` to create user with body:

  ```
     {
         "firstName":"John",
         "lastName": "Doe",
         "email": "test@gmail.com",
         "password": "12345678",
         "confirmPassword": "12345678"
     }
```

7. To get token send POST to `http://localhost:7003/users-service/api/login` with body:

```
{
    "email": "test@gmail.com",
    "password": "12345678"
}
```
