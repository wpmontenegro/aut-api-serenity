package com.bdd.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/postUserExample.feature",
        glue = {"com.bdd.stepsdefinitions", "com.bdd.hooks"},
        stepNotifications = true
)

public class TestPostUserRunner {
}
