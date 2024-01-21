package com.api.scenario;

import io.cucumber.java.Scenario;
import lombok.Getter;

public class ManageScenario {

    @Getter
    private static Scenario scenario;

    public static void setScenario(Scenario scenario){
        ManageScenario.scenario = scenario;
    }
}
