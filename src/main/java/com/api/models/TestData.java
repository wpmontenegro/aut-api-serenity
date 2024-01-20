package com.api.models;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TestData {

    @Getter
    private static final Map<String, String> testData = new HashMap<>();
    @Getter
    private static final Map<String, String> headers = new HashMap<>();
    @Getter
    private static final Map<String, String> bodyData = new HashMap<>();
    @Getter
    private static final Map<String, String> pathParams = new HashMap<>();
    @Getter
    private static final Map<String, String> queryParams = new HashMap<>();

    public static void clear() {
        TestData.testData.clear();
        TestData.headers.clear();
        TestData.pathParams.clear();
        TestData.queryParams.clear();
    }
}