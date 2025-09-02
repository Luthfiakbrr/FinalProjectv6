package com.sdd.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // aktifkan incognito
            options.addArguments("--incognito");

            // cegah popup notifikasi & password manager
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-translate");
            options.addArguments("--disable-blink-features=AutomationControlled");

            // benar-benar disable password manager
            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
                put("credentials_enable_service", false);
                put("profile.password_manager_enabled", false);
                put("profile.password_manager_leak_detection", false);
                put("autofill.profile_enabled", false);
                put("autofill.credit_card_enabled", false);
            }});

            // cek apakah headless mode diminta (via Gradle atau CI)
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            if (isHeadless || System.getenv("CI") != null) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
