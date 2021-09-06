package com.example.telegram_webhook_maven.AppConfig;

import com.example.telegram_webhook_maven.Bot.TelegramHandler;
import com.example.telegram_webhook_maven.Bot.WebHookBot;
import com.example.telegram_webhook_maven.BotConfig.BotConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
public class AppConfig {

    private BotConfig botConfig;

    public AppConfig(BotConfig webHookBotConfig) {
        this.botConfig = webHookBotConfig;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public WebHookBot webHookBot(TelegramHandler telegramHandler) {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);
        WebHookBot webHookBot = new WebHookBot(options, telegramHandler);
        webHookBot.setBotUsername(botConfig.getUserName());
        webHookBot.setBotPath(botConfig.getWebHookPath());
        webHookBot.setBotToken(botConfig.getBotToken());

        return webHookBot;
    }

}
