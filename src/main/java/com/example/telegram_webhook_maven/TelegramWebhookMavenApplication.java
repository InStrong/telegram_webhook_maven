package com.example.telegram_webhook_maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TelegramWebhookMavenApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();

        SpringApplication.run(TelegramWebhookMavenApplication.class, args);
    }

}
