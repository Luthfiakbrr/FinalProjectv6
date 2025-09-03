package com.sdd.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class WebDriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

            switch (browser) {
                case "firefox":
                    io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions ffOptions = new FirefoxOptions();

                    if (isHeadless || System.getenv("CI") != null) {
                        ffOptions.addArguments("--headless");
                        ffOptions.addArguments("--width=1920");
                        ffOptions.addArguments("--height=1080");
                    }

                    driver = new FirefoxDriver(ffOptions);
                    break;

                case "edge":
                    io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();

                    if (isHeadless || System.getenv("CI") != null) {
                        edgeOptions.addArguments("--headless=new");
                        edgeOptions.addArguments("--disable-gpu");
                        edgeOptions.addArguments("--window-size=1920,1080");
                    }

                    driver = new EdgeDriver(edgeOptions);
                    break;

                default: // chrome
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

                    if (isHeadless || System.getenv("CI") != null) {
                        options.addArguments("--headless=new");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--window-size=1920,1080");
                    }

                    driver = new ChromeDriver(options);
                    break;
            }

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
