package com.bdd.stepsdefinitions;

import com.api.questions.VerifyUser;
import com.api.tasks.Load;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static com.bdd.stepsdefinitions.CommonStepDefinition.callGetService;
import static com.bdd.stepsdefinitions.CommonStepDefinition.callPostService;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class UserStepDefinition {

    @Given("I load customer information")
    public void iLoadCustomerInformation(List<Map<String, String>> testDataList) {
        theActorInTheSpotlight().wasAbleTo(Load.queryParams(testDataList));
    }

    @When("I make the user query")
    public void iMakeTheUserQuery() {
        callGetService("USERS");
    }

    @And("I validate fields of get user response")
    public void iValidateFieldsOfGetUserResponse(List<Map<String, String>> testDataList) {
        theActorInTheSpotlight().wasAbleTo(Load.testData(testDataList));
        theActorInTheSpotlight().should(VerifyUser.responseGetUser());
    }

    @Given("I load data user to create")
    public void iLoadDataUserToCreate(List<Map<String, String>> testDataList) {
        theActorInTheSpotlight().wasAbleTo(Load.bodyData(testDataList));
    }

    @When("I make the creation of user")
    public void iMakeTheCreationOfUser() {
        callPostService("USERS");
    }

    @And("I validate fields of post user response")
    public void iValidateFieldsOfPostUserResponse() {
        theActorInTheSpotlight().should(VerifyUser.responsePostUser());
    }
}