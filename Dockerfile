# pull base image
FROM centos:7

# maintainer details
MAINTAINER Jeremy Vincent "vincent.jerem@gmail.com"

#Define maven home directory
ENV M2_HOME /opt/apache-maven-3.5.3

# Install wget, mvn, java 8
RUN yum install -y wget which && \
    yum install -y java-1.8.0-openjdk-devel && \
    wget http://apache.crihan.fr/dist/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.tar.gz && \
    tar zxf apache-maven-3.5.3-bin.tar.gz -C /opt/ && \
    alternatives --install /usr/bin/mvn mvn /opt/apache-maven-3.5.3/bin/mvn 2 && \
    yum install -y git && \
    rm -f apache-maven-3.5.3-bin.tar.gz

# Creating working directory
RUN mkdir -p /var/blabla-mowers

# Copying project on image
COPY . /var/blabla-mowers

#Define as working directry
WORKDIR /var/blabla-mowers

# Install project
RUN mvn clean install

#Run program with input file located at the root of the project
CMD java -jar target/mowers-1.0.1-SNAPSHOT-jar-with-dependencies.jar mower-input && \
    while true; do sleep 1000; done