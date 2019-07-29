package org.example.webapp.gamedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.example.webapp" })
@EnableAutoConfiguration
public class GameDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameDemoApplication.class, args);
    }
}
