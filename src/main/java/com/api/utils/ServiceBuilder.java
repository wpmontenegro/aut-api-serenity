package com.api.utils;

import lombok.Getter;

import static com.api.utils.Endpoints.*;

@Getter
public enum ServiceBuilder {
    USERS(API_BASE, PATH_USERS),
    SINGLE_USER(API_BASE, PATH_SINGLE_USER);

    private final String baseUrl;
    private final String path;

    ServiceBuilder(String baseUrl, String path) {
        this.baseUrl = baseUrl;
        this.path = path;
    }
}
