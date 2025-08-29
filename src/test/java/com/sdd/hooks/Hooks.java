package com.sdd.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void beforeScenario() {
        // global setup if needed
    }

    @After
    public void afterScenario() {
        // global teardown if needed
    }
}
