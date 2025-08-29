package com.sdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartLink = By.className("shopping_cart_link");
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver d) {
        this.driver = d;
        this.wait = new WebDriverWait(d, Duration.ofSeconds(10));
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public boolean isAtCartPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn)).isDisplayed();
    }
}
