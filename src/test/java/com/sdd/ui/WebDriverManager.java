package com.sdd.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();

            // Headless mode untuk CI/CD (GitHub Actions, Linux runner)
            options.addArguments("--headless=new"); // gunakan headless baru (lebih stabil di Chrome v109+)
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--window-size=1920,1080"); // ukuran default window

            driver = new ChromeDriver(options);

            // Tunggu element maksimal 10 detik (implicit wait)
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
