package com.zheltouhov.bot;

import com.zheltouhov.command.AbstractCommand;
import com.zheltouhov.exception.BotException;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendAudio;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by mazh0416 on 5/10/2017.
 */
class CommonBot extends TelegramLongPollingBot {
    private String token;
    private String name;
    private AbstractCommand command;
    BotMessageHandler messageHandler;

    CommonBot(String token, String name, BotMessageHandler messageHandler) throws BotException {
        if (token == null || name == null){
            throw new BotException("Token or name is null");
        }
        this.name = name;
        this.token = token;
        this.messageHandler = messageHandler;
    }
    public void onUpdateReceived(Update update) {

        try {
            PartialBotApiMethod<Message> result = messageHandler.handleMessage(update.getMessage());
            if (result != null) {
                SendMessage sendMessage = (SendMessage) result;
                sendMessage(sendMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return name;
    }

    public String getBotToken() {
        return token;
    }
}
