package com.example.telegram_webhook_maven.Bot;

import com.example.telegram_webhook_maven.IPLocator.IPLocator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

@Service
@AllArgsConstructor
public class TelegramHandler {
    private IPLocator ipLocator;

    public SendMessage handleUpdate(Update update) throws IOException {


        return new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(ipLocator.prettyViewJSON(ipLocator.readJsonFromUrl(update.getMessage().getText())));
    }
}
