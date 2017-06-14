package com.zheltouhov.bot;

/**
 * Created by mazh0416 on 5/10/2017.
 */
public abstract class StateProvider {
    public abstract BotState getBotState(Long chatId);
}
