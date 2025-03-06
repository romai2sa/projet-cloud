# PROJET CLOUD - EQUIPE 5

## Objectif de l'application
* Affichage de films ghibli

### Prérequis
* Disposer du CLI terraform dans le terminal de votre machine
* Cloner le projet `` git clone https://github.com/Enchanteee/projetCloud.git``

## URL officielles 

### Environnement DEV
* Back : [equipe5-dev.eastus.azurecontainer.io:8080](equipe5-dev.eastus.azurecontainer.io:8080)
* Front :  [equipe5-dev.eastus.azurecontainer.io:9090](equipe5-dev.eastus.azurecontainer.io:9090)

### Environnement PROD
* Back : [equipe5-prod.eastus.azurecontainer.io:8080](equipe5-prod.eastus.azurecontainer.io:8080)
* Front : [equipe5-prod.eastus.azurecontainer.io:9090](equipe5-prod.eastus.azurecontainer.io:9090)

## Lancer l'applicaton avec Docker
### Back
* ``cd back``
* ``docker build -t back-app:groupe5 -f .\Dockerfile . ``
* `` docker run -p 8080:8080 back-app:groupe5 ``

### Front
* `` cd front ``
* `` docker build -t front-app:groupe5 -f .\Dockerfile . ``
* `` docker run -p 4200:9090 front-app:groupe5 ``

## Lancer l'application en local

### Back
* Ouvrir le dossier back avec un IDE
* Ouvrir la classe BackAppApplication qui se trouve dans back\src\main\java\com\groupe5\backapp\BackAppApplication.java
* Run le main

### Front
* `` cd front ``
* décommenter la ligne 14 et commenter la ligne 15 du Dockerfile
* installer nodeJS si vous ne l'avez pas (verification avec node -v)
* `` npm install -g @angular/cli ``
* `` npm run start local ``
* aller sur http://localhost:4200/


## Déploiement

### Déploiement sur l'environnement de développement

#### Etape 1 : Push en dev
* `` git checkout dev ``
* `` git push ``
* Se rendre sur l'onglet action et le workflow 'deploy to developement' et récupéré la valeur `` githubsha `` lors de l'étape "Retrieve information"
* Garder cette valeur dans le presse-papier

#### Etape 2 :  Déployer en utilsant terraform
* se placer dans le repertoire terraform ``cd terraform/dev``
* `` terraform init -upgrade ``
* `` terraform plan  -var="tag=[github sha récupéré plus tôt]"``
* `` terraform apply -var="tag=[github sha récupéré plus tôt]" ``


### Déploiement sur l'environnement de production

#### Etape 1 : Push en production
 * `` git checkout main `` /!\ bien mettre les modifcations validé de dev vers main
 * `` git tag -a vX.X.X -m "Version X.X.X" `` /!\ Faire attention la norme semver est utilisé
 * `` git push origin vX.X.X ``
 * (possibilité de faire une release sur github)
 * Bien garder la référence du tag, elle sera utile plus tard


#### Etape 2 : Déployer en utilsant terraform
* se placer dans le repertoire terraform ``cd terraform/prod``

* `` terraform init -upgrade ``

* `` terraform plan  -var="tag=[tag récupéré plus tôt]" ``

* `` terraform apply -var="tag=[tag récupéré plus tôt]" ``


## Crédits
* Merci à [https://github.com/janaipakos](https://github.com/janaipakos) pour l'api Ghibli