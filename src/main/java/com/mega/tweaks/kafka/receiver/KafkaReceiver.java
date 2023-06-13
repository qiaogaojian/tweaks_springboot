package com.mega.tweaks.kafka.receiver;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import static com.mega.tweaks.consts.KafkaTopic.KAFKA_MESSAGE_TEST;

@Slf4j
@Component
public class KafkaReceiver
{
    @KafkaListener(topics = {KAFKA_MESSAGE_TEST}, groupId = "group_id")
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }
    }
}
