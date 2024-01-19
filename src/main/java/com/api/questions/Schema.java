package com.api.questions;

import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static com.api.utils.Constants.SCHEMA_FORMAT;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class Schema implements Question<Boolean> {

    private final String schema;

    public Schema(String schemaResponse) {
        this.schema = schemaResponse;
    }

    public static Question<Boolean> isExpected(String schemaResponse) {
        return new Schema(schemaResponse);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(seeThatResponse("Validation of Response Schema",
                validatableResponse -> validatableResponse
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(String.format(SCHEMA_FORMAT, schema)))
        ));
        return true;
    }
}
