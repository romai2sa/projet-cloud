# Frontend

## Run avec Docker 

### Build docker image
* docker build -t front-app:groupe5 -f .\Dockerfile .

### Create docker container
* docker run -p 8080:8080 front-app:groupe5

## Run en local : 

* décommenter la ligne 14 et commenter la ligne 15 du Dockerfile
* installer nodeJS si vous ne l'avez pas (verification avec node -v)
* npm install -g @angular/cli
* npm run start local
* aller sur http://localhost:4200/
