#!/bin/bash

ls -la 
../../java/apache-maven-3.8.1/bin/mvn package -f ./pom.xml
ls -la

../../Program\ Files/Docker/Docker/resources/bin/docker.exe build -t justinshagerty/kafka_as_a_service .
../../Program\ Files/Docker/Docker/resources/bin/docker.exe run -p 80:80 justinshagerty/kafka_as_a_service


