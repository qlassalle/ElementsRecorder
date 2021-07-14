# Installation

You'll need JDK 16 to run this project. You can install a working JDK with sdkman. The version that has been used for development 
is the `16.0.1.9.1-amzn`.

To ensure everything's working properly, run
* `./mvnw clean install`
* `./mvnw test`

# How to run

Locally, place yourself one level above back and front and run docker-compose up

On GCP, make sure env variables are set up. To set up environment variables, do the following:

```
vi .bashrc
export MY_ENV_VAR=xxxxx
source .bashrc
printenv # to make sure that your env var has been set up
```

To deploy
* `mvn package -DskipTests`
* cp target/elements-recorder-SNAPSHOT.jar back/target/
* gcloud compute scp --recurse back remember-me-back:~

You must expose one of the ports (currently only the db) from your router. Go to 192.168.1.1 and open a NAT port.

# Docker troubleshooting
If you consistently see that your changes are not reported to your containers, consider deleting all the images and run the following command 
```
docker-compose up --build --force-recreate
```

# Access local db
* `sudo -u postgres -i`
* `\l` to list databases
* Don't forget the `;` at the end of your queries!!

# Features to add
Rating should have a maximum
...
# Bugs to fix
* Change postgresql port on Pi 
* Timestamps for dates aren't set on adding and updating

# Things that I'd like to have on this project
* CI/CD including
    * Dockerization
    * automated deployment on AWS
    * good integration tests with Docker
* Gradle as a build tool
* Fancy badges on Github repo

# Things added
* PostgreSQL
* Flyway