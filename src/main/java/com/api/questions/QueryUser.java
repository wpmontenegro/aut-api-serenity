package com.api.questions;

import com.api.interactions.DataBase;
import com.api.models.TestData;
import com.api.models.User;
import net.serenitybdd.screenplay.Question;

public class QueryUser {

    public static String NAME_DB = "example-db";
    public static String USER_QUERY = "SELECT name FROM users WHERE id = :id";

    public static Question<String> validateName() {
        return actor -> {
            User user = DataBase.executeQuery(NAME_DB, USER_QUERY, TestData.getTestData(), User.class);
            return user.getFirstName();
        };
    }
}
