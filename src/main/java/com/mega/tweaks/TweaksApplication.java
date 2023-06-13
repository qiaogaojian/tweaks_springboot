package com.mega.tweaks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableMongoRepositories
@ConfigurationPropertiesScan
@MapperScan({"com.mega.tweaks.model.mapper","com.mega.tweaks.model.dao"})
public class TweaksApplication
{
    public static void main(String[] args) {
        SpringApplication.run(TweaksApplication.class, args);
    }
}
