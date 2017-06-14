package com.zheltouhov.bot;

import com.zheltouhov.exception.BotException;

/**
 * Created by mazh0416 on 5/10/2017.
 */
public class StatelessBotCreator extends BotCreator{
    public static void create(String token, String name) throws BotException {
        BotMessageHandler messageHandler = new BotMessageHandler(null);
        CommonBot bot = new CommonBot(token, name, messageHandler);
        init(bot);
    }
}
