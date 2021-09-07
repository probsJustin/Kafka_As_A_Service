# Kafka As A Service
Kafka as a service for java, I wanted a way to easily implement the kafka producer and consumer to return it via a request and add this as portfolio filler/example. 

### Current Status: 
Currently Done 

### Use Case: 
Used to demonstrate/test kafka hosts for APM (application performance monitoring) demonstrations to show tracing/examples to customers

# Install/Run Notes: 
Needs: Maven, docker, docker compose (included in docker in newer versions)

- clone this repository...... 

- go to kafka-docker directory 
- run the following comamnd: 

```docker-compose -f ./docker-compose-expose.yml up```
- go to the base directory of this repository...... 
- run build.sh 
- go to the following url: 

```http://localhost:8080/KAAS/kafkaConsumer?topic=topic_test&address=<YOUR LOCAL IP - TO ACCESS THE DOCKER CONTAINER>:9092```

```http://localhost:8080/KAAS/kafkaProducer?topic=topic_test&address=<YOUR LOCAL IP - TO ACCESS THE DOCKER CONTAINER>:9092&message=sure```
