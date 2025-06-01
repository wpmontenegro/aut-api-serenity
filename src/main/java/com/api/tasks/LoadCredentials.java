package com.api.tasks;

import com.api.models.TestData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LoadCredentials implements Task {

    public static LoadCredentials toAuthenticate() {
        return Tasks.instrumented(LoadCredentials.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String apiKey = System.getenv("REQRES_API_KEY");
        TestData.getHeaders().put("x-api-key", apiKey);
    }
}
