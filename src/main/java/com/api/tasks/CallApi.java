package com.api.tasks;

import com.api.models.TestData;
import com.api.interactions.Api;
import com.api.utils.EnumUtil;
import com.api.utils.ServiceBuilder;
import com.api.utils.TemplateUtil;
import io.restassured.http.Method;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CallApi implements Task {

    private final String serviceName;
    private final Method method;

    public CallApi(String serviceName, Method method) {
        this.serviceName = serviceName;
        this.method = method;
    }

    public static CallApi withName(String serviceName, Method method) {
        return instrumented(CallApi.class, serviceName, method);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        ServiceBuilder service = EnumUtil.searchServiceByName(serviceName);
        String body;
        switch (method) {
            case GET:
                actor.attemptsTo(Api.get(service.getBaseUrl(), service.getPath(), TestData.getHeaders(), TestData.getPathParams(), TestData.getQueryParams()));
                break;
            case POST:
                body = TemplateUtil.mergeWithFieldsFrom(serviceName, TestData.getBodyData());
                actor.attemptsTo(Api.post(service.getBaseUrl(), service.getPath(), TestData.getHeaders(), body));
                break;
            case PUT:
                body = TemplateUtil.mergeWithFieldsFrom(serviceName, TestData.getBodyData());
                actor.attemptsTo(Api.put(service.getBaseUrl(), service.getPath(), TestData.getHeaders(), body));
                break;
            case DELETE:
                actor.attemptsTo(Api.delete(service.getBaseUrl(), service.getPath(), TestData.getHeaders()));
                break;
        }
    }
}
