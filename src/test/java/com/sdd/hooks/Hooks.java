package com.sdd.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    private static WebDriver driver;

    @Before
    public void beforeScenario() {
        // ✅ otomatis download driver yg sesuai
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");              // buka di incognito mode
        options.addArguments("--disable-notifications");  // blokir notifikasi
        options.addArguments("--disable-popup-blocking"); // cegah popup
        options.addArguments("--remote-allow-origins=*"); // biar kompatibel versi Chrome terbaru

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
