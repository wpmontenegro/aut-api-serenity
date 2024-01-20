package com.api.models;

import lombok.Getter;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Getter
@Setter
public class User {

    @ColumnName("first_name")
    private String firstName;
}
