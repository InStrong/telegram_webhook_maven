package com.example.telegram_webhook_maven.BotConfig;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "telegrambot")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig {
    String webHookPath;
    String userName;
    String botToken;
}
