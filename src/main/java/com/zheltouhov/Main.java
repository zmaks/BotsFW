package com.zheltouhov;

import com.zheltouhov.bot.BotCreator;
import com.zheltouhov.bot.StatefulBotCreator;
import com.zheltouhov.bot.StatelessBotCreator;
import com.zheltouhov.exception.BotException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        try {
            StatelessBotCreator.create("207572767:AAGIfFfsL5DIC6vRpAtzb1pkEgAXgi3ESVM", "zheltbot");
            //StatefulBotCreator.create("BOT_TOKEN", "MyBot", new MyStateProvider);
        } catch (BotException e) {
            e.printStackTrace();
        }
    }
}
