package com.zheltouhov.test;

import com.zheltouhov.annotaions.TextBotCommand;
import com.zheltouhov.command.AbstractCommand;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

@TextBotCommand("start")
public class StartCommand extends AbstractCommand {

    public PartialBotApiMethod<Message> execute(Message message) {
        System.out.println(this.getClass().getName());
        return simpleTextResponse("Hello World", message.getChatId());
    }
}


