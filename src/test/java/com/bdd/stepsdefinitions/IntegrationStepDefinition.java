package com.bdd.stepsdefinitions;

import com.api.tasks.GetMessage;
import com.api.tasks.GetUserName;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;

public class IntegrationStepDefinition {
    @And("I get the user in auth0")
    public void iGetTheUserInAuth0() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetUserName.byEmailInAuth0());
    }

    @And("I get the message in mailsac")
    public void iGetTheMessageInMailsac() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetMessage.byEmailInMailsac());
    }
}
