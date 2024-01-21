package com.api.scenario;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AttachReport implements Interaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachReport.class);

    private final String requestDetails;
    private final String responseDetails;

    public AttachReport(String requestDetails, String responseDetails) {
        this.requestDetails = requestDetails;
        this.responseDetails = responseDetails;
    }

    public static Performable loadEvidence(String requestDetails, String responseDetails) {
        return instrumented(AttachReport.class, requestDetails, responseDetails);
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        if (ManageScenario.getScenario() == null)
            throw new IllegalArgumentException("There is no scenario configured");

        String scenarioDetail = "SCENARIO: " + ManageScenario.getScenario().getName() + "\n";
        String evidence = scenarioDetail + "\n"
                + requestDetails + "\n"
                + responseDetails;
        LOGGER.info("Attaching evidence into Cucumber Report");
        ManageScenario.getScenario().attach(evidence.getBytes(), "text/json", "evidence");
    }
}
