package com.zheltouhov.exception;

/**
 * Created by mazh0416 on 5/10/2017.
 */
public class BotException extends Exception {
    public BotException(String message) {
        super(message);
    }

    public BotException(String message, Throwable cause) {
        super(message, cause);
    }
}
