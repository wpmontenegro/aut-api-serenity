package com.api.scenario;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AttachReport implements Interaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachReport.class);

    public static AttachReport loadEvidence() {
        return instrumented(AttachReport.class);
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        if (ManageScenario.getScenario() == null)
            throw new IllegalArgumentException("There is no scenario configured");

        FilterableRequestSpecification requestSpecification = (FilterableRequestSpecification) SerenityRest.when();
        Response response = SerenityRest.lastResponse();

        String separator = "----------------------------------------------------------------";
        String scenarioDetail = "SCENARIO: " + ManageScenario.getScenario().getName() + "\n";
        String requestDetails = "REQUEST" + "\n"
                + "URL: " + requestSpecification.getURI() + "\n"
                + "Method: " + requestSpecification.getMethod() + "\n"
                + separator + "\n"
                + "REQUEST HEADERS" + "\n"
                + requestSpecification.getHeaders() + "\n"
                + separator + "\n"
                + "REQUEST QUERY PARAMETERS" + "\n"
                + requestSpecification.getQueryParams() + "\n"
                + separator + "\n"
                + "REQUEST PATH PARAMETERS" + "\n"
                + requestSpecification.getPathParams() + "\n"
                + separator + "\n"
                + "REQUEST BODY" + "\n"
                + requestSpecification.getBody() + "\n";
        String responseDetails = "RESPONSE" + "\n"
                + "Status Code: " + response.getStatusCode() + "\n"
                + "Content Type: " + response.getContentType() + "\n"
                + separator + "\n"
                + "RESPONSE HEADERS " + "\n"
                + response.getHeaders() + "\n"
                + separator + "\n"
                + "RESPONSE BODY" + "\n"
                + response.getBody().prettyPrint() + "\n";

        String evidence = scenarioDetail + "\n"
                + requestDetails + "\n"
                + responseDetails;
        LOGGER.info("Attaching evidence into Cucumber Report");
        ManageScenario.getScenario().attach(evidence.getBytes(), "text/json", "evidence");
    }
}