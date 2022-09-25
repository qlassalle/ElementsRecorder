# Installation

You'll need JDK 17 to run this project. You can install a working JDK with sdkman. The version that has been used for development 
is the `17.0.2.8.1-amzn`.

To ensure everything's working properly, run
* `./mvnw clean install`
* `./mvnw test`

# Infrastructure
Docker images are stored in a private registry on the raspberry. It's not secured at the moment, so we have to use the `insecure-registry` flag.

If you have a running minikube cluster, delete it.

`minkube` should be started with this tag: `mk start --insecure-registry "192.168.1.21/32"`

## Raspberry setup
* Use a 64 bit image: https://www.raspberrypi.com/software/operating-systems/#raspberry-pi-os-64-bit
* Install docker
* Create a registry: `docker run -d -p 5000:5000 --restart=always --name registry registry:2` https://docs.docker.com/registry/deploying/
* Build image on local machine, tag it and push it to the registry:
```shell
docker build -t 192.168.1.21:5000/elements-recorder-api .
docker push 192.168.1.21:5000/elements-recorder-api
```
* Install k3s (pay attention to a message saying something about adding `...memory...` to `/boot/cmdline.txt` if you're on a raspberry)
* create a dedicated namespace on k3s
* apply db-pv and db-svc files: `k apply -f db-pv.yml`
* create the database
  * enter the container hosting the db: `k exec -ti pod-name -- bash`
  * connect to the database: `psql -h localhost -p 5432 -U elements_recorder_app -W`
  * create the database: `create database elements_recorder_db`
* apply app-deployment: `k apply -f app-deployment.yml`

# Docker troubleshooting
If you consistently see that your changes are not reported to your containers, consider deleting all the images and run the following command 
```
docker-compose up --build --force-recreate
```

# Publish a new version
* `docker build --no-cache -t 192.168.1.21:5000/elements-recorder-api .`
* `docker tag 192.168.1.21:5000/elements-recorder-api:latest 192.168.1.21:5000/elements-recorder-api:3b4e07b3`
where `3b4e07b3` is the latest commit
* `docker push 192.168.1.21:5000/elements-recorder-api:3b4e07b3`
* edit `_infra/app-deployment.yml` deployed in your environment with the path to the latest image

# Access local db
* `sudo -u postgres -i`
* `\l` to list databases
* Don't forget the `;` at the end of your queries!!

# Backup prod db
### In the Raspberry
* Connect to db pod: `k exec -it elements-recorder-db-757fbf45ff-jsmd2 -- bash`
* `cd` to the folder of your choice `cd /home`
* Run the backup command `pg_dump elements_recorder_db -U elements_recorder_app > db.sql`
* Copy the dump from the pod to the Raspberry: `k cp elements-recorder-db-757fbf45ff-jsmd2:home/db.sql /db.sql`
### On your computer
* Copy the file from the Raspberry to your local machine: `scp qlassalle@192.168.1.21:/home/qlassalle/ElementsRecorder/db.sql .`

# Features to add
* Rating should have a maximum
* Expired JWT should not throw any exception
...
# Bugs to fix 
* Timestamps for dates aren't set on adding and updating

# Things that I'd like to have on this project
* CI/CD including
    * automated deployment on AWS
    * good integration tests with Docker
* Gradle as a build tool
* Fancy badges on Github repo