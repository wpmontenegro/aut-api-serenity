package com.bdd.stepsdefinitions;

import com.api.screenplay.models.TestData;
import com.api.screenplay.tasks.Load;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

public class GetStepDefinition {

    @Given("I load customer information")
    public void iLoadCustomerInformation(List<Map<String, String>> listTestData) {
        OnStage.theActorInTheSpotlight().wasAbleTo(Load.TestData(listTestData));
    }

    @When("I make the user query")
    public void iMakeTheUserQuery() {
        System.out.println("Data:: " + TestData.getTestData());
    }

    @Then("I should see the status code {int}")
    public void iShouldSeeTheStatusCode(int int1) {

    }

    @Then("I validate schema response {string}")
    public void iValidateSchemaResponse(String string) {

    }

    @Then("I validate fields of get response")
    public void iValidateFieldsOfGetResponse() {

    }
}