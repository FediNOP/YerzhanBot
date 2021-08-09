package ru.nop.yerzhanbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class YerzhanBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(YerzhanBotApplication.class, args);
    }

}
