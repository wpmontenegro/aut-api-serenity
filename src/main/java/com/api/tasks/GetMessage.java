package com.api.tasks;

import com.api.integrations.Mailsac;
import com.api.models.TestData;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;


public class GetMessage implements Task {

    public static GetMessage byEmailInMailsac() {
        return Tasks.instrumented(GetMessage.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String email = TestData.getBodyData().get("email");
        actor.attemptsTo(Mailsac.getMessages(email));
        JsonPath listOfMessages = SerenityRest.lastResponse().body().jsonPath();
        TestData.getBodyData().put("name", listOfMessages.get("[0].from[0].name"));
        TestData.getBodyData().put("message", listOfMessages.get("[0].subject"));
    }
}