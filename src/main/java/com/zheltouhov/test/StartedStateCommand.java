package com.zheltouhov.test;

import com.zheltouhov.annotaions.StateBotCommand;
import com.zheltouhov.bot.BotState;
import com.zheltouhov.command.AbstractCommand;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Message;

@StateBotCommand(BotState.STARTED)
public class StartedStateCommand extends AbstractCommand{

    public PartialBotApiMethod<Message> execute(Message message) {
        return simpleTextResponse("Bot is started", message.getChatId());
    }
}
