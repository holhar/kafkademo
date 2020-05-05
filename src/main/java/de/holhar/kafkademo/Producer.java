package de.holhar.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static de.holhar.kafkademo.KafkaTopicConfig.TOPIC_NAME;

@Component
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, Greeting> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, Greeting> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Greeting greeting) {
        ListenableFuture<SendResult<String, Greeting>> future = kafkaTemplate.send(TOPIC_NAME, greeting);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Greeting>>() {

            @Override
            public void onSuccess(SendResult<String, Greeting> result) {
                LOGGER.info("Sent message=[" + greeting + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Unable to send message=[" + greeting + "] due to: " + ex.getMessage());
            }
        });
    }
}
