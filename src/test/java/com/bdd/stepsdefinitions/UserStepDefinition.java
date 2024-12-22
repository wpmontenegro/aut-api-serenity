package com.bdd.stepsdefinitions;

import com.api.questions.VerifyUser;
import com.api.tasks.Load;
import com.api.tasks.LoadUser;
import com.api.tasks.SaveUser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static com.bdd.stepsdefinitions.CommonStepDefinition.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class UserStepDefinition {

    @Given("I load user information")
    public void iLoadUserInformation(List<Map<String, String>> testDataList) {
        theActorInTheSpotlight().wasAbleTo(Load.pathParams(testDataList));
    }

    @When("I make the single user query")
    public void iMakeTheSingleUserQuery() {
        callGetService("SINGLE_USER");
    }

    @And("I validate fields of get single user response")
    public void iValidateFieldsOfGetSingleUserResponse(List<Map<String, String>> testDataList) {
        theActorInTheSpotlight().wasAbleTo(Load.testData(testDataList));
        theActorInTheSpotlight().should(VerifyUser.responseGetSingleUser());
    }

    @Given("I load page information")
    public void iLoadPageInformation(List<Map<String, String>> testDataList) {
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
        theActorInTheSpotlight().attemptsTo(SaveUser.userId());
    }

    @When("I make the update of user")
    public void iMakeTheUpdateOfUser() {
        callPutService("SINGLE_USER");
    }

    @And("I validate fields of put user response")
    public void iValidateFieldsOfPutUserResponse() {
        theActorInTheSpotlight().should(VerifyUser.responsePutUser());
    }

    @When("I make the delete of user")
    public void iMakeTheDeleteOfUser() {
        callDeleteService("SINGLE_USER");
    }

    @And("I validate body is empty")
    public void iValidateBodyIsEmpty() {
        theActorInTheSpotlight().should(VerifyUser.responseUserIsEmpty());
    }

    @When("I make the creation of user with message")
    public void iMakeTheCreationOfUserWithMessage() {
        callPostService("USERS_MESSAGE");
    }

    @And("I validate each user of get user response")
    public void iValidateEachUserOfGetUserResponse() {
        theActorInTheSpotlight().should(VerifyUser.responseIdOfEachUserFromGetUser());
    }

    @Given("I load data from csv")
    public void iLoadDataFromCsv() {
        theActorInTheSpotlight().wasAbleTo(LoadUser.fromCSV());
    }
}