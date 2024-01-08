package com.api.screenplay.tasks;

import com.api.screenplay.models.TestData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Load implements Task {
    private final List<Map<String, String>> TestDataList;

    public Load(List<Map<String, String>> TestDataList) {
        this.TestDataList = TestDataList;
    }

    public static Performable TestData(List<Map<String, String>> TestDataList) {
        return Tasks.instrumented(Load.class, TestDataList);
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        if (!TestDataList.isEmpty()) {
            Set<Map.Entry<String, String>> testDataSet = TestDataList.get(0).entrySet();
            TestData.setTestData(testDataSet.stream().collect(Collectors.toMap(Map.Entry::getKey,
                    Map.Entry::getValue)));
        }
    }

}
