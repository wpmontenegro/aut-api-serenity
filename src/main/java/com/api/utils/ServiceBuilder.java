package com.api.utils;

import static com.api.utils.Endpoints.*;

public enum ServiceBuilder {
    GET_USERS(API_BASE, PATH_USERS),
    GET_SINGLE_USER(API_BASE, PATH_SINGLE_USER);

    private final String baseUrl;
    private final String path;

    ServiceBuilder(String baseUrl, String path) {
        this.baseUrl = baseUrl;
        this.path = path;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPath() {
        return path;
    }
}
