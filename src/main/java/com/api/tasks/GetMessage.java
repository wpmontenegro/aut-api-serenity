package com.api.tasks;

import com.api.integrations.Mailsac;
import com.api.models.TestData;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.List;
import java.util.Map;

import static com.api.utils.Constants.EMPTY;

public class GetMessage implements Task {

    public static GetMessage byEmailInMailsac() {
        return Tasks.instrumented(GetMessage.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String email = TestData.getBodyData().get("email");
        actor.attemptsTo(Mailsac.getMessages(email));
        List<Map<String, String>> listOfMessages = SerenityRest.lastResponse().body().jsonPath().getList(EMPTY);
        if (!listOfMessages.isEmpty()) {
            TestData.getBodyData().put("name", listOfMessages.get(0).get("savedBy"));
            TestData.getBodyData().put("message", listOfMessages.get(0).get("subject"));
        }
    }
}