package com.zheltouhov.command;

import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by mazh0416 on 5/10/2017.
 */
public abstract class AbstractCommand {
    public abstract PartialBotApiMethod<Message> execute(Message message);

    public PartialBotApiMethod<Message> simpleTextResponse(String text, Long chatId) {
        return new SendMessage()
                .setChatId(chatId)
                .setText(text);
    }
}
