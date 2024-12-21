package com.api.integrations;

import com.api.interactions.Api;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

public class Mailsac {
    public static final String MAILSAC_URL = "https://mailsac.com/api/";
    public static final String PATH_MESSAGES = "addresses/{email}/messages";
    public static final String API_KEY = System.getenv("MAILSAC_API_KEY");

    public static Performable getMessages(String email) {
        return Task.where(actor -> {
            Map<String, String> headers = new HashMap<>();
            headers.put("Mailsac-Key",API_KEY);
            Map<String, String> pathParams = new HashMap<>();
            pathParams.put("email",email);
            actor.attemptsTo(Api.get(MAILSAC_URL, PATH_MESSAGES, headers, pathParams, new HashMap<>()));
        });
    }
}
