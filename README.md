## To run all services with docker compose locally:
1. Install and start docker on the machine https://docs.docker.com/docker-for-mac/install/
2. To build the project execute form root folder (skipping test):
```
    mvn clean -Dmaven.test.skip=true install
```
3. To push created jars to dockerhub: 
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
 
 4. Run `doker-compose up` from root folder to start all services
 5. Go to eurika service to check services status:
 `http://root:root@localhost:7002/eureka/`
 6. To check user service sent POST to `http://localhost:7003/users-service/api/register` to create user
  with body:
  ```
     {
         "firstName":"John",
         "lastName": "Doe",
         "email": "test@gmail.com",
         "password": "12345678",
         "confirmPassword": "12345678"
     }
```
Note: ELK services commented because they might hang the system when running