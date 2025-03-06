# Backend commands

## Run avec Docker

### Build docker image
* docker build -t back-app:groupe5 -f .\Dockerfile .

### Create docker container
* docker run -p 8080:8080 back-app:groupe5

## Run en local 
* Ouvrir le dossier back avec un IDE
* Ouvrir la classe BackAppApplication qui se trouve dans back\src\main\java\com\groupe5\backapp\BackAppApplication.java
* Run le main

### Swagger links

* Env de DEV : equipe5-dev.eastus.azurecontainer.io:8080/swagger-ui/index.html
* Env de PROD : equipe5-prod.eastus.azurecontainer.io:8080/swagger-ui/index.html
 