package com.api.utils;

import com.api.exceptions.AutomationException;

public class EnumUtil {

    public static ServiceBuilder searchServiceByName(String serviceName) {
        try {
            return ServiceBuilder.valueOf(serviceName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AutomationException("Service not found: " + serviceName);
        }
    }
}
