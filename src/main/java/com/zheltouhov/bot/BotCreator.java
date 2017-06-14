package com.zheltouhov.bot;

import com.zheltouhov.exception.BotException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;

/**
 * Created by mazh0416 on 5/10/2017.
 */
public abstract class BotCreator {

    protected static void init(LongPollingBot bot) throws BotException {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(bot);

        } catch (TelegramApiException e) {
            throw new BotException(e.getMessage(), e);
        }
    }


}
