# Spring Boot and Kafka demo

A small demo implementation using spring-kafka according to https://www.baeldung.com/spring-kafka.
Producer and consumer are configured and messages can be sent via a controller.
Prerequisites: Apache Kafka and Zookeeper are running with default configuration locally.

A message filter is configured so that only messages containing 'bar' are processed. Send messages like so:

```
curl -X POST -H"Content-Type:application/json" -d '{"name":"holger","msg":"we should go to a bar"}' http://localhost:8080/messages
```