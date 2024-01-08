package com.bdd.stepsdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;


import static net.serenitybdd.screenplay.actors.OnStage.*;

public class Hook {

    private EnvironmentVariables environmentVariables;

    @Before
    public void init() {
        setTheStage(new OnlineCast());
        theActorCalled("User");

        String apiBaseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("base.url");
        theActorInTheSpotlight().whoCan(CallAnApi.at(apiBaseUrl));
    }
}
