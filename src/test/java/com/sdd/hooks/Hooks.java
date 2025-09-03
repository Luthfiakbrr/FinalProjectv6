package com.sdd.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import com.sdd.ui.WebDriverManager;

public class Hooks {

    private static WebDriver driver;

    @Before
    public void beforeScenario() {
        // Ambil driver dari WebDriverManager (bisa Chrome, Firefox, Edge, tergantung -Dbrowser)
        driver = WebDriverManager.getDriver();
    }

    @After
    public void afterScenario() {
        // Tutup driver setelah scenario selesai
        WebDriverManager.quitDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
