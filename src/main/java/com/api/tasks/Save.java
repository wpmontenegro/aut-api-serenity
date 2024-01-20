package com.api.tasks;

import com.api.models.TestData;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class Save {

    public static Performable userId() {
        return Task.where(actor -> {
            String userId = SerenityRest.lastResponse().body().jsonPath().get("id").toString();
            TestData.getTestData().put("id", userId);
        });
    }
}
