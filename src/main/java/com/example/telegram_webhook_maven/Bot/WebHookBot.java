package com.example.telegram_webhook_maven.Bot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebHookBot extends TelegramWebhookBot {

    String botPath;
    String botUsername;
    String botToken;

    private TelegramHandler telegramHandler;

    public WebHookBot(DefaultBotOptions options, TelegramHandler telegramHandler) {
        super(options);
        this.telegramHandler = telegramHandler;
    }

    @SneakyThrows
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramHandler.handleUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }
}
