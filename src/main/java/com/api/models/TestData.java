package com.api.models;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    private static final Map<String, String> testData = new HashMap<>();
    private static final Map<String, String> headers = new HashMap<>();
    private static final Map<String, String> bodyData = new HashMap<>();
    private static final Map<String, String> pathParams = new HashMap<>();
    private static final Map<String, String> queryParams = new HashMap<>();

    public static Map<String, String> getTestData() {
        return testData;
    }

    public static Map<String, String> getHeaders() {
        return headers;
    }
    public static Map<String, String> getBodyData() {
        return bodyData;
    }

    public static Map<String, String> getPathParams() {
        return pathParams;
    }

    public static Map<String, String> getQueryParams() {
        return queryParams;
    }

    public static void clear() {
        TestData.testData.clear();
        TestData.headers.clear();
        TestData.pathParams.clear();
        TestData.queryParams.clear();
    }
}