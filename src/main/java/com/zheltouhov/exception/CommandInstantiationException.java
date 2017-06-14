package com.zheltouhov.exception;

/**
 * Created by Maksim on 03.06.2017.
 */
public class CommandInstantiationException extends Exception{
    private String commandName;
    public CommandInstantiationException(String message, String commandName, Throwable cause) {
        super(message, cause);
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
