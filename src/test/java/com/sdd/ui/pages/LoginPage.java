package com.sdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");
    private By inventoryPage = By.id("inventory_container");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameInput.clear();
        usernameInput.sendKeys(username);

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isAtHomePage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryPage)).isDisplayed();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
}
