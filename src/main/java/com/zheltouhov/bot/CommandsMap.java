package com.zheltouhov.bot;

import com.zheltouhov.annotaions.RegexBotCommand;
import com.zheltouhov.annotaions.StateBotCommand;
import com.zheltouhov.annotaions.TextBotCommand;
import com.zheltouhov.command.AbstractCommand;
import com.zheltouhov.exception.BotException;
import com.zheltouhov.exception.CommandInstantiationException;
import org.reflections.Reflections;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.objects.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mazh0416 on 5/10/2017.
 */
class CommandsMap {

    private Map<String, AbstractCommand> textCommands;
    private Map<Pattern, AbstractCommand> regexCommands;
    private Map<BotState, AbstractCommand> stateCommands;

    private AbstractCommand defaultCommand;

    public CommandsMap(boolean isStateful) throws BotException {
        load(isStateful);
    }

    public PartialBotApiMethod<Message> performTextCommand(String commandName, Message message){
        System.out.println("try to find "+commandName);
        if (textCommands.containsKey(commandName)) {
            System.out.println("Ok: "+commandName);
            return textCommands.get(commandName).execute(message);
        }
        return null;
    }

    public PartialBotApiMethod<Message> performRegexCommand(Message message) {
        for (Pattern pattern : regexCommands.keySet()){
            Matcher matcher = pattern.matcher(message.getText());
            if (matcher.find()){
                return regexCommands.get(pattern).execute(message);
            }
        }
        return null;
    }

    public PartialBotApiMethod<Message> performStateCommand(BotState botState, Message message) {
        if (stateCommands.containsKey(botState)){
            return stateCommands.get(botState).execute(message);
        }
        return null;
    }

    public PartialBotApiMethod<Message> performDefaultCommand(Message message) {
        if (defaultCommand != null) {
            return defaultCommand.execute(message);
        }
        return null;
    }

    private void load(boolean isStateful) throws BotException {
        Reflections reflections = new Reflections();
        Set<Class<?>> textCommandTypes = reflections.getTypesAnnotatedWith(TextBotCommand.class);
        Set<Class<?>> regexCommandTypes = reflections.getTypesAnnotatedWith(RegexBotCommand.class);
        Set<Class<?>> stateCommandTypes = null;
        if (isStateful) {
            stateCommandTypes = reflections.getTypesAnnotatedWith(StateBotCommand.class);
        }

        try {
            if (textCommandTypes != null && !textCommandTypes.isEmpty()) {
                loadTextCommands(textCommandTypes);
            }
            if (regexCommandTypes != null && !regexCommandTypes.isEmpty()) {
                loadRegexCommands(regexCommandTypes);
            }
            if (stateCommandTypes != null && !stateCommandTypes.isEmpty()) {
                loadStateCommands(stateCommandTypes);
            }
        } catch (CommandInstantiationException e){
            throw new BotException(String.format("Cannot perform command %s", e.getCommandName()), e);
        }
    }

    private void loadTextCommands(Set<Class<?>> commands) throws CommandInstantiationException {
        textCommands = new HashMap<String, AbstractCommand>();
        for(Class<?> commandClass : commands){
            try {
                String commandValue = commandClass.getAnnotation(TextBotCommand.class).value();
                System.out.println(commandValue+" - inited");
                AbstractCommand commandInstance = (AbstractCommand) commandClass.newInstance();
                textCommands.put(commandValue, commandInstance);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new CommandInstantiationException(e.getMessage(), commandClass.getName(), e);
            }
        }
    }

    private void loadRegexCommands(Set<Class<?>> commands) throws CommandInstantiationException {
        Reflections reflections = new Reflections();
        for(Class<?> commandClass : commands){
            try {
                String regex = commandClass.getAnnotation(RegexBotCommand.class).regex();
                Pattern commandPattern = Pattern.compile(regex);
                AbstractCommand commandInstance = (AbstractCommand) commandClass.newInstance();
                regexCommands.put(commandPattern, commandInstance);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new CommandInstantiationException(e.getMessage(), commandClass.getName(), e);
            }
        }
    }

    private void loadStateCommands(Set<Class<?>> commands) throws CommandInstantiationException {
        stateCommands = new HashMap<BotState, AbstractCommand>();
        for(Class<?> commandClass : commands){
            try {
                BotState botState = commandClass.getAnnotation(StateBotCommand.class).value();
                AbstractCommand commandInstance = (AbstractCommand) commandClass.newInstance();
                stateCommands.put(botState, commandInstance);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new CommandInstantiationException(e.getMessage(), commandClass.getName(), e);
            }
        }
    }


}
