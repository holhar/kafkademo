package de.holhar.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(
            //topicPartitions = @TopicPartition(topic = "baeldung", partitions = {"0", "1"}),
            topics = "baeldung",
            groupId = "foo",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(@Payload Greeting greeting,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOGGER.info("Received Message in group foo: " + greeting + " from partition: " + partition);
    }
}
