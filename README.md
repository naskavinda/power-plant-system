## Power Plant Sysytem

##### How To Run

Step 01:

First you need to run [setup-database-docker-compose.yml](https://github.com/naskavinda/power-plant-system/blob/master/docker/setup-database-docker-compose.yml) file located in `docker` directory. Then That will create a MySQL database for you.

Step 02:

Then Go to the Project Root directory and run below cmd.
```
mvn spring-boot:run
```

You can use swagger endpoint to check the api endpoints

swagger url: 
```
http://localhost:8080/swagger-ui.html#
```

#### Assumption

Assume Barrey name is unique. based on that asssumption I add the calidation to the Save list of battery endpoint.

