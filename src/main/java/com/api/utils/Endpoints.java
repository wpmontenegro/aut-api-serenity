package com.api.utils;

import com.api.models.TestData;

public class Endpoints {
    public static final String API_BASE_URL = TestData.getTestData().get("apiBaseUrl");
    public static final String PATH_USERS = "api/users";
    public static final String PATH_SINGLE_USER = "api/users/{id}";
}
