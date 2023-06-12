package com.mega.tweaks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@SpringBootApplication
@EnableWebMvc
public class TweaksApplication
{

    public static void main(String[] args) {
        SpringApplication.run(TweaksApplication.class, args);
    }

    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return (args) -> {
            System.out.println("Let's inspect the bean provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
}
