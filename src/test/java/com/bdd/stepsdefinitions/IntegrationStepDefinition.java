package com.bdd.stepsdefinitions;

import com.api.tasks.GetUser;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;

public class IntegrationStepDefinition {
    @And("I get the user in auth0")
    public void iGetTheUserInAuth0() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetUser.byEmailInAuth0());
    }
}
