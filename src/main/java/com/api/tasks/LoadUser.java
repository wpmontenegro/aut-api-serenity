package com.api.tasks;

import com.api.models.TestData;
import com.api.utils.CSVReader;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class LoadUser {
    private static final String USER_CSV = "user.csv";

    public static Performable fromCSV() {
        return Task.where(actor -> {
            String name = CSVReader.readRandomValueFromCSV(USER_CSV, "name");
            String job = CSVReader.readRandomValueFromCSV(USER_CSV, "job");
            TestData.getBodyData().put("name", name);
            TestData.getBodyData().put("job", job);
        });
    }
}
