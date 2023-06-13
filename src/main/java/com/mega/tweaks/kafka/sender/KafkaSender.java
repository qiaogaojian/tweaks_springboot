package com.mega.tweaks.kafka.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mega.tweaks.kafka.message.Message;
import com.mega.tweaks.util.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import static com.mega.tweaks.consts.KafkaTopic.KAFKA_MESSAGE_TEST;

@Slf4j
@Component
public class KafkaSender
{
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());

        try {
            log.info("+++++++++++++++++++++  message = {}", JsonUtils.toJson(message));
            kafkaTemplate.send(KAFKA_MESSAGE_TEST, JsonUtils.toJson(message));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException:", e);
        }
    }
}
