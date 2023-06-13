package com.mega.tweaks;

import com.mega.tweaks.kafka.receiver.KafkaReceiver;
import com.mega.tweaks.kafka.sender.KafkaSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableMongoRepositories
@ConfigurationPropertiesScan
@MapperScan({"com.mega.tweaks.model.mapper", "com.mega.tweaks.model.dao"})
public class TweaksApplication
{
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TweaksApplication.class, args);
        runKafkaSender(context);
    }

    private static void runKafkaSender(ConfigurableApplicationContext context) {
        KafkaSender sender = context.getBean(KafkaSender.class);
        for (int i = 0; i < 3; i++) {
            //调用消息发送类中的消息发送方法
            sender.send();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
