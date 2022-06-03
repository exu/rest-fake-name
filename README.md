# rest-fake-name Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Running application in docker container
Get release project version (without SNAPSHOT) from pom.xml or run scripts:
```shell
mvn versions:set -DremoveSnapshot -DgenerateBackupPoms=false
VERSION=$(mvn -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive exec:exec -q)
```

Build service as docker image with 
```shell
./mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.image=rest-fake-name:$VERSION
```

You can check docker image details with `docker images --filter=reference="*/*/rest-fake-name"`

Now you can run service in container
```shell
docker run -d -i --rm -p 8080:8080 --name rest-fake-name registry-1.docker.io/library/rest-fake-name:$VERSION
```
#### Building application inside another docker container
```shell
# run container with docker image includes correct java and maven versions
docker run -it --rm -v ${PWD}:/app -v /var/run/docker.sock:/var/run/docker.sock maven:3.8.4-openjdk-17-slim bash
# install docker inside this container
apt-get update -qq && apt-get install -y -qq docker.io
# switch to project folder
cd app
# build docker image with app code and specific version (1.1 in this case - check correct one in pom.xml)
mvn clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.image=rest-fake-name:1.1
# run a docker container using this image (make sure version is correct)
docker run -d -i --rm -p 8080:8080 --name rest-fake-name registry-1.docker.io/library/rest-fake-name:1.1
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/rest-fake-name-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
