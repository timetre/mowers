Mower project
=====

Contains implementation of the Mowers technical test

BlaBlaCar Code Test 2018.pdf

# Few info
- This project has been developed using Java 8, maven 3.5.3, Git, Docker, IntelliJ Idea on a fedora workstation
- Only docker and Git are required to install and run this project
- Both tools are available on Mac, Windows or Linux
- Program will take a well-formatted file named mower-input as input parameter
- Once project have been pulled from Gitlab, you can still modify the file if you want to

### Technologies ####
* Java 8
* Maven 3.5.3
* TestNG
* Git
* Docker
* IntelliJ Idea

# Docker Installation
https://docs.docker.com/install

# Git Installation
https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

# Fetching repository

```shell
mkdir ~/working-dir && cd ~/working-dir
git clone git@gitlab.jeremyvincent.net:perso/mowers.git .
```

# Modifying input file to fit requirements
```shell
vi mower-input
```

Edit file as you want
```shell
5 5
1 2 N
LFLFLFLFF
3 3 E
FFRFFRFRRF
```

# There are many ways to run the project

## Running project on workstation (make sure to have java installed)

### Compilation
```
mvn clean install
```
This will run tests and then create a jar file **mowers-1.0.1-SNAPSHOT-jar-with-dependencies.jar** in the */target* directory.

### Running ###
```
java -jar target/mowers-1.0.1-SNAPSHOT-jar-with-dependencies.jar mower-input
```

## Running project on Docker image

## Start docker service
```shell
sudo service docker start
```

### Build docker image based on Dockerfile at project root
```shell
docker build -t blabla-dev .
```

### Build docker image based on Dockerfile at project root
```shell
docker build -t blabla-dev .
```
The docker image containing all the project will fetch all of its dependencies and add proper content
After a while, you should see something like

```shell
[INFO] Installing /var/blabla-mowers/target/application.jar to /root/.m2/repository/com/blablacar/mower/mowers/1.0.1-SNAPSHOT/mowers-1.0.1-SNAPSHOT.jar
[INFO] Installing /var/blabla-mowers/pom.xml to /root/.m2/repository/com/blablacar/mower/mowers/1.0.1-SNAPSHOT/mowers-1.0.1-SNAPSHOT.pom
[INFO] Installing /var/blabla-mowers/target/mowers-1.0.1-SNAPSHOT-jar-with-dependencies.jar to /root/.m2/repository/com/blablacar/mower/mowers/1.0.1-SNAPSHOT/mowers-1.0.1-SNAPSHOT-jar-with-dependencies.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 18.498 s
[INFO] Finished at: 2018-06-10T13:52:27Z
[INFO] ------------------------------------------------------------------------
 ---> c90c65bc7ebe
Removing intermediate container 7b445f3d4179
Step 9/9 : CMD java -jar target/mowers-1.0.1-SNAPSHOT-jar-with-dependencies.jar src/test/resources/test-input &&     while true; do sleep 1000; done
 ---> Running in 612c2dfece49
 ---> 17cb2d6c81a4
Removing intermediate container 612c2dfece49
Successfully built 17cb2d6c81a4
```

### Run built docker image
```shell
docker run blabla-dev
```
The program will start by itself and display the following result

```shell
13:53:33.960 [main] INFO  com.blablacar.mowers.Runner - ------ Moving Mower located at 1 2 N -----
13:53:33.972 [main] INFO  com.blablacar.mowers.Runner - Final mower position 1 3 N
13:53:33.972 [main] INFO  com.blablacar.mowers.Runner - ------ Moving Mower located at 3 3 E -----
13:53:33.972 [main] INFO  com.blablacar.mowers.Runner - Final mower position 5 1 E
```
