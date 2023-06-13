package com.mega.tweaks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan({"com.mega.tweaks.api.model.mapper","com.mega.tweaks.api.model.dao"})
public class TweaksApplication
{
    public static void main(String[] args) {
        SpringApplication.run(TweaksApplication.class, args);
    }
}
