package com.api.questions;

import com.api.models.TestData;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class VerifyUser {

    private static final String SUPPORT_URL = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral";
    private static final String SUPPORT_TEXT = "Tired of writing endless social media content? Let Content Caddy generate it for you.";
    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z$";

    public static ResponseConsequence responseGetSingleUser() {
        return ResponseConsequence.seeThatResponse("Validate Fields Get Single User",
                validateResponse -> validateResponse
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
                validateResponse -> validateResponse
                        .body("name", equalTo(TestData.getBodyData().get("name")))
                        .body("job", equalTo(TestData.getBodyData().get("job")))
                        .body("id", not(emptyString()))
                        .body("createdAt", matchesRegex(DATE_REGEX))
        );
    }

    public static ResponseConsequence responseGetUser() {
        return ResponseConsequence.seeThatResponse("Validate Fields Get User",
                validateResponse -> validateResponse
                        .body("page", equalTo(Integer.parseInt(TestData.getQueryParams().get("page"))))
                        .body("per_page", equalTo(Integer.parseInt(TestData.getTestData().get("per_page"))))
                        .body("total", equalTo(Integer.parseInt(TestData.getTestData().get("total"))))
                        .body("total_pages", equalTo(Integer.parseInt(TestData.getTestData().get("total_pages"))))
                        .body("data", hasSize(Integer.parseInt(TestData.getTestData().get("per_page"))))
                        .body("support.url", equalTo(SUPPORT_URL))
                        .body("support.text", equalTo(SUPPORT_TEXT))
        );
    }

    public static ResponseConsequence responsePutUser() {
        return ResponseConsequence.seeThatResponse("Validate Fields Put User",
                validateResponse -> validateResponse
                        .body("name", equalTo(TestData.getBodyData().get("name")))
                        .body("job", equalTo(TestData.getBodyData().get("job")))
                        .body("updatedAt", matchesRegex(DATE_REGEX))
        );
    }

    public static ResponseConsequence responseUserIsEmpty() {
        return ResponseConsequence.seeThatResponse("Validate Response User is empty",
                validateResponse -> validateResponse
                        .body(is(emptyOrNullString()))
        );
    }

    public static ResponseConsequence responseIdOfEachUserFromGetUser() {
        return ResponseConsequence.seeThatResponse("Validate id of each user from Get User",
                validateResponse -> {
                    List<Map<String, Object>> users = validateResponse.extract().path("data");
                    boolean isMatch = users.stream().allMatch(
                            user -> user.get("id").toString().matches("\\d+")
                    );
                    Assertions.assertThat(isMatch).isTrue();
                });
    }
}