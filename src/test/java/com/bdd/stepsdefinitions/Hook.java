package com.bdd.stepsdefinitions;

import com.api.screenplay.models.TestData;
import io.cucumber.java.Before;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Hook {

    private EnvironmentVariables environmentVariables;

    @Before
    public void init() {
        setTheStage(new OnlineCast());
        theActorCalled("User");

        TestData.clear();
        String apiBaseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("base.url");
        TestData.getTestData().put("apiBase", apiBaseUrl);
    }
}
