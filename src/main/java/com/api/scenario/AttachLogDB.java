package com.api.scenario;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AttachLogDB implements Interaction {
    private final String nameDB;
    private final String queryDB;
    private final String resultDB;
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachLogDB.class);

    public AttachLogDB(String nameDB, String queryDB, String resultDB) {
        this.nameDB = nameDB;
        this.queryDB = queryDB;
        this.resultDB = resultDB;
    }

    public static AttachLogDB loadEvidence(String nameDB, String queryDB, String resultDB) {
        return instrumented(AttachLogDB.class, nameDB, queryDB, resultDB);
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        if (ManageScenario.getScenario() == null)
            throw new IllegalArgumentException("There is no scenario configured");

        String scenarioDetail = "SCENARIO: " + ManageScenario.getScenario().getName() + "\n";
        String logBD = "LOG DB" + "\n"
                + "Name DB: " + nameDB + "\n"
                + "Query: " + queryDB + "\n"
                + "Result: " + resultDB + "\n";

        String evidence = scenarioDetail + "\n"
                + logBD;
        LOGGER.info("Attaching DB log into Cucumber Report");
        ManageScenario.getScenario().attach(evidence.getBytes(), "text/plain", "evidence");
    }
}