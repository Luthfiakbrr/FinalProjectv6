package com.sdd.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.sdd.api.stepdefinitions", "com.sdd.ui.stepdefinitions", "com.sdd.hooks"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // 🔥 penting untuk Allure
        },
        tags = "@api or @web"
)
public class CombinedTestRunner {
}
