package com.zheltouhov.bot;

import com.zheltouhov.exception.BotException;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by mazh0416 on 5/10/2017.
 */
class BotMessageHandler {
    private StateProvider stateProvider;
    private CommandsMap commands;
    private boolean isStateful;
    private static final String CS = "/";

    BotMessageHandler(StateProvider stateProvider){
        this.stateProvider = stateProvider;
        this.isStateful = stateProvider != null;
        try {
            this.commands = new CommandsMap(isStateful);
        } catch (BotException e) {
            e.printStackTrace();
        }
    }
    public PartialBotApiMethod<Message> handleMessage(Message message){

        PartialBotApiMethod<Message> result = null;
        if (message.hasText()){
            String messageText = message.getText();
            System.out.println("new message: "+ messageText);
            if (messageText.contains(CS)) {
                String commandName = message.getText().split(" ")[0].replace(CS, "");
                result = commands.performTextCommand(commandName, message);
                if (result != null){
                    return result;
                }
            } else {
                result = commands.performRegexCommand(message);
                if (result != null){
                    return result;
                }
            }
        }
        if (isStateful){
            BotState botState = stateProvider.getBotState(message.getChatId());
            result = commands.performStateCommand(botState, message);
            if (result != null){
                return result;
            }
        }

        if (result == null) {
            result = commands.performDefaultCommand(message);
        }

        return result;
    }
}
