package com.api.screenplay.questions;

import com.api.screenplay.models.TestData;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;

import static org.hamcrest.Matchers.*;

public class Verify {

    private static final String SUPPORT_URL = "https://reqres.in/#support-heading";
    private static final String SUPPORT_TEXT = "To keep ReqRes free, contributions towards server costs are appreciated!";

    public static ResponseConsequence responseGetUser() {
        return ResponseConsequence.seeThatResponse("Validate Fields Get User",
                validatableResponse -> validatableResponse
                        .body("data.id", equalTo(Integer.parseInt(TestData.getTestData().get("id"))))
                        .body("data.email", equalTo(TestData.getTestData().get("email")))
                        .body("data.first_name", equalTo(TestData.getTestData().get("first_name")))
                        .body("data.last_name", equalTo(TestData.getTestData().get("last_name")))
                        .body("data.avatar", equalTo(TestData.getTestData().get("avatar")))
                        .body("support.url", equalTo(SUPPORT_URL))
                        .body("support.text", equalTo(SUPPORT_TEXT))
        );
    }

    public static ResponseConsequence responsePostUser() {
        return ResponseConsequence.seeThatResponse("Validate Fields Post User",
                validatableResponse -> validatableResponse
                        .body("name", equalTo(TestData.getBodyData().get("name")))
                        .body("job", equalTo(TestData.getBodyData().get("job")))
                        .body("id", not(emptyString()))
                        .body("createdAt", not(emptyString()))
        );
    }
}