package com.api.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

import java.util.concurrent.TimeUnit;

public class Sleep implements Interaction {

    private final long seconds;

    public Sleep(long seconds) {
        this.seconds = seconds;
    }

    public static Sleep forSecond(long seconds) {
        return Tasks.instrumented(Sleep.class, seconds);
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}