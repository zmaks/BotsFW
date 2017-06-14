package com.zheltouhov.annotaions;

import com.zheltouhov.bot.BotState;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mazh0416 on 5/10/2017.
 */

@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface StateBotCommand {
    BotState value();
}


