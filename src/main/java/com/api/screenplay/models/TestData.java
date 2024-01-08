package com.api.screenplay.models;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    private static Map<String, Object> testData = new HashMap<>();

    public static Map<String, Object> getTestData() {
        return testData;
    }

    public static void setTestData(Map<String, Object> testData) {
        TestData.testData = testData;
    }
}
