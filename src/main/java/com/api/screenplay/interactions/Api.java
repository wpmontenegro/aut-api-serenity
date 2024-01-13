package com.api.screenplay.interactions;

import com.api.exceptions.AutomationException;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.Map;

import static io.restassured.http.Method.*;

public class Api implements Interaction {
    private final String baseUrl;
    private final String resource;
    private final Method method;
    private final Map<String, String> headers;
    private final String body;
    private final Map<String, String> pathParams;
    private final Map<String, String> queryParams;

    public Api(String baseUrl, String resource, Method method, Map<String, String> headers, String body, Map<String, String> pathParams, Map<String, String> queryParams) {
        this.baseUrl = baseUrl;
        this.resource = resource;
        this.method = method;
        this.headers = headers;
        this.body = body;
        this.pathParams = pathParams;
        this.queryParams = queryParams;
    }

    public static Api get(String baseUrl, String resource, Map<String, String> headers, Map<String, String> pathParams, Map<String, String> queryParams) {
        return Tasks.instrumented(Api.class, baseUrl, resource, GET, headers, null, pathParams, queryParams);
    }

    public static Api post(String baseUrl, String resource, Map<String, String> headers, String body) {
        return Tasks.instrumented(Api.class, baseUrl, resource, POST, headers, body, null, null);
    }

    public static Api put(String baseUrl, String resource, Map<String, String> headers, String body) {
        return Tasks.instrumented(Api.class, baseUrl, resource, PUT, headers, body, null, null);
    }

    public static Api delete(String baseUrl, String resource, Map<String, String> headers) {
        return Tasks.instrumented(Api.class, baseUrl, resource, DELETE, headers, null, null, null);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.can(CallAnApi.at(baseUrl));
        switch (method) {
            case GET:
                actor.attemptsTo(
                        Get.resource(resource).with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .headers(headers)
                                .pathParams(pathParams)
                                .queryParams(queryParams)
                                .relaxedHTTPSValidation().log().all())
                );
                break;
            case POST:
                actor.attemptsTo(
                        Post.to(resource).with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .headers(headers)
                                .body(body)
                                .relaxedHTTPSValidation().log().all())
                );
                break;
            case PUT:
                actor.attemptsTo(
                        Put.to(resource).with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .headers(headers)
                                .body(body)
                                .relaxedHTTPSValidation().log().all())
                );
                break;
            case DELETE:
                actor.attemptsTo(
                        Delete.from(resource).with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .headers(headers)
                                .relaxedHTTPSValidation().log().all())
                );
                break;
            default:
                throw new AutomationException("Unsupported HTTP method: " + method);
        }
        SerenityRest.lastResponse().prettyPrint();
    }
}
