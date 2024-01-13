package com.api.utils;

import com.api.screenplay.models.TestData;

public class Endpoints {
    public static final String API_BASE = TestData.getTestData().get("apiBase");
    public static final String PATH_USERS = "api/users";
    public static final String PATH_SINGLE_USER = "api/users/{id}";
}
