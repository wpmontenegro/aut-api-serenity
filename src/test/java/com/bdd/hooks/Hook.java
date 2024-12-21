package com.bdd.hooks;

import com.api.models.TestData;
import com.api.scenario.ManageScenario;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Hook {

    private EnvironmentVariables environmentVariables;

    @Before(order = 1)
    public void init() {
        setTheStage(new OnlineCast());
        theActorCalled("User");

        TestData.clear();
        String apiBaseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("base.url");
        String auth0Url = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("auth0.url");
        TestData.getTestData().put("apiBaseUrl", apiBaseUrl);
        TestData.getTestData().put("auth0Url", auth0Url);
    }

    @Before(order = 2)
    public void handleScenario(Scenario scenario) {
        ManageScenario.setScenario(scenario);
    }
}
