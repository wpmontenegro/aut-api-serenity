package com.api.tasks;

import com.api.integrations.Auth0;
import com.api.models.TestData;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.List;
import java.util.Map;

import static com.api.utils.Constants.EMPTY;

public class GetUserName implements Task {

    public static GetUserName byEmailInAuth0() {
        return Tasks.instrumented(GetUserName.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String email = TestData.getBodyData().get("email");
        actor.attemptsTo(Auth0.getUsersByEmail(email));
        List<Map<String, String>> listOfUsers = SerenityRest.lastResponse().body().jsonPath().getList(EMPTY);
        if (!listOfUsers.isEmpty()) TestData.getBodyData().put("name", listOfUsers.get(0).get("nickname"));
    }
}
