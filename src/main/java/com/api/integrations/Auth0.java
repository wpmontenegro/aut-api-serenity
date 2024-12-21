package com.api.integrations;

import com.api.interactions.Api;
import com.api.models.TestData;
import com.api.utils.TemplateUtil;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

public class Auth0 {
    public static final String AUTH0_URL = TestData.getTestData().get("auth0Url");
    public static final String PATH_TOKEN = "/oauth/token";
    public static final String PATH_EMAIL = "/api/v2/users-by-email";
    public static final String TEMPLATE_GET_TOKEN_AUTH0 = "GET_TOKEN_AUTH0";
    public static final String FORMAT_AUTHORIZATION = "%s %s";
    public static final String AUTHORIZATION = "authorization";

    private static Performable getAccessToken() {
        return Task.where(actor -> {
            Map<String, String> credentials = new HashMap<>();
            credentials.put("clientId", System.getenv("AUTH0_CLIENT_ID"));
            credentials.put("clientSecret", System.getenv("AUTH0_CLIENT_SECRET"));
            credentials.put("audience", AUTH0_URL.concat("/api/v2/"));
            credentials.put("grantType", "client_credentials");
            String body = TemplateUtil.mergeWithFieldsFrom(TEMPLATE_GET_TOKEN_AUTH0, credentials);
            actor.attemptsTo(Api.post(AUTH0_URL, PATH_TOKEN, new HashMap<>(), body));
            JsonPath lastResponse = SerenityRest.lastResponse().body().jsonPath();
            String accessToken = lastResponse.get("access_token").toString();
            String tokenType =  lastResponse.get("token_type").toString();
            actor.remember(AUTHORIZATION, String.format(FORMAT_AUTHORIZATION, tokenType, accessToken));
        });
    }

    public static Performable searchUsersByEmail(String email) {
        return Task.where(actor -> {
            actor.attemptsTo(getAccessToken());
            Map<String, String> headers = new HashMap<>();
            headers.put(AUTHORIZATION, actor.recall(AUTHORIZATION));
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("email", email);
            actor.attemptsTo(Api.get(AUTH0_URL, PATH_EMAIL, headers, new HashMap<>(), queryParams));
        });
    }
}