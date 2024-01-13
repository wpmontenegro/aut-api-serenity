package com.api.screenplay.models;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    private static final Map<String, Object> testData = new HashMap<>();
    private static final Map<String, Object> headers = new HashMap<>();
    private static final Map<String, Object> pathParams = new HashMap<>();
    private static final Map<String, Object> queryParams = new HashMap<>();

    public static Map<String, Object> getTestData() {
        return testData;
    }

    public static Map<String, Object> getHeaders() {
        return headers;
    }

    public static Map<String, Object> getPathParams() {
        return pathParams;
    }

    public static Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public static void clear() {
        TestData.testData.clear();
        TestData.headers.clear();
        TestData.pathParams.clear();
        TestData.queryParams.clear();
    }
}