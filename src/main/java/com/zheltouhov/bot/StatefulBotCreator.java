package com.zheltouhov.bot;

import com.zheltouhov.exception.BotException;
import org.telegram.telegrambots.generics.LongPollingBot;

/**
 * Created by mazh0416 on 5/10/2017.
 */
public class StatefulBotCreator extends BotCreator{

    public static void create(String token, String name, StateProvider stateProvider) throws BotException {
        BotMessageHandler messageHandler = new BotMessageHandler(stateProvider);
        CommonBot bot = new CommonBot(token, name, messageHandler);
        init(bot);
    }
}
