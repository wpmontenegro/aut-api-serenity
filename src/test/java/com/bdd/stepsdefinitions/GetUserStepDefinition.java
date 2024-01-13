package com.bdd.stepsdefinitions;

import com.api.screenplay.tasks.Load;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static com.bdd.stepsdefinitions.CommonStepDefinition.callGetService;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class GetUserStepDefinition {

    @Given("I load customer information")
    public void iLoadCustomerInformation(List<Map<String, String>> testDataList) {
        theActorInTheSpotlight().wasAbleTo(Load.queryParams(testDataList));
    }

    @When("I make the user query")
    public void iMakeTheUserQuery() {
        callGetService("GET_USERS");
    }

    @And("I validate fields of user response")
    public void iValidateFieldsOfUserResponse() {
    }
}