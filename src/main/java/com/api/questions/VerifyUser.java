package com.api.questions;

import com.api.models.TestData;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;

import static org.hamcrest.Matchers.*;

public class VerifyUser {

    private static final String SUPPORT_URL = "https://reqres.in/#support-heading";
    private static final String SUPPORT_TEXT = "To keep ReqRes free, contributions towards server costs are appreciated!";
    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z$";

    public static ResponseConsequence responseGetUser() {
        return ResponseConsequence.seeThatResponse("Validate Fields Get User",
                validateResponse -> validateResponse
                        .body("data.id", equalTo(Integer.parseInt(TestData.getTestData().get("id").toString())))
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
                validateResponse -> validateResponse
                        .body("name", equalTo(TestData.getBodyData().get("name")))
                        .body("job", equalTo(TestData.getBodyData().get("job")))
                        .body("id", not(emptyString()))
                        .body("createdAt", matchesRegex(DATE_REGEX))
        );
    }
}