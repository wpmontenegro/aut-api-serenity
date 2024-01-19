package com.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class StatusCode implements Question<Boolean> {
    private final int code;

    public StatusCode(int statusCode) {
        this.code = statusCode;
    }

    public static Question<Boolean> is(int statusCode) {
        return new StatusCode(statusCode);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(seeThatResponse("Validation of Response Status Code",
                validatableResponse -> validatableResponse
                        .statusCode(code)
        ));
        return true;
    }
}