package com.sdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartLink = By.className("shopping_cart_link");
    private By checkoutBtn = By.id("checkout");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postal = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By completeText = By.className("complete-header");

    public CheckoutPage(WebDriver d) {
        this.driver = d;
        this.wait = new WebDriverWait(d, Duration.ofSeconds(10));
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void checkout(String fn, String ln, String postalCode) {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(ln);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postal)).sendKeys(postalCode);
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean isOrderComplete() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeText)).isDisplayed();
    }
}
