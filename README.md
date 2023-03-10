# avro-kafka-producer

### _Kafka message producer with avro serializer for load test_

## To run in terminal ##

#### In the project directory ####

    docker-compose up --build -d

#### To follow the logs ####

    docker logs --follow avro-kafka-producer-app

## To run in IDE ##

#### In application settings ####

Set active profiles to: local

## To start the load test ##

#### Customer endpoint ####

    localhost:3000/v1/load-tests/customers

#### It is necessary to send 3 mandatory parameters
| Request Parameter | What is It                                                          |
|------------------|---------------------------------------------------------------------|
| message-quantity | Total messages you want to send to kafka                            |  
| quantity-per-second | Number of messages you want to send to kafka every second           |  
| failure-percentage-per-second | Percentage of error messages you want to send to kafka every second |  

##### In this case, the CPF will be generated correctly or wrong as on the percentage of failures seted on the parameter 'failure-percentage-per-second'
###### CPF - It is a individual registration, unique number (ID), in Brazil

The postman collection can be imported using a file found in the project folder - *Kafka Producer.postman_collection.json*