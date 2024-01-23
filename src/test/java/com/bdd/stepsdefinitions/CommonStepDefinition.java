package com.bdd.stepsdefinitions;

import com.api.questions.Schema;
import com.api.questions.StatusCode;
import com.api.tasks.CallApi;
import io.cucumber.java.en.Then;

import static io.restassured.http.Method.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CommonStepDefinition {

    public static void callGetService(String serviceName) {
        theActorInTheSpotlight().attemptsTo(CallApi.withName(serviceName, GET));
    }

    public static void callPostService(String serviceName) {
        theActorInTheSpotlight().attemptsTo(CallApi.withName(serviceName, POST));
    }

    public static void callPutService(String serviceName) {
        theActorInTheSpotlight().attemptsTo(CallApi.withName(serviceName, PUT));
    }

    public static void callDeleteService(String serviceName) {
        theActorInTheSpotlight().attemptsTo(CallApi.withName(serviceName, DELETE));
    }

    @Then("I should see the status code {int}")
    public void iShouldSeeTheStatusCode(int statusCode) {
        theActorInTheSpotlight().should(seeThat(StatusCode.is(statusCode)));
    }

    @Then("I validate schema response {string}")
    public void iValidateSchemaResponse(String schemaResponse) {
        theActorInTheSpotlight().should(seeThat(Schema.isExpected(schemaResponse)));
    }
}
