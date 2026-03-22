# Processing Service

## API
GET /{orderId}/status

## Description
Consumes order events from Kafka, Process the order and sends notifications

## API
GET /heartbeat

## Description
checks the heartbeat of the service

## Flow
Processing Service -> Consumes event -> Processes order -> Publishes Event (OrderProcessed)

## Run
mvn clean package
docker build -t processing-service .
docker run -p 8082:8082 processing-service