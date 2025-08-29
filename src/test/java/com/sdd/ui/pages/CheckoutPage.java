package com.sdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;
    private By cartLink = By.className("shopping_cart_link");
    private By checkoutBtn = By.id("checkout");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postal = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By completeText = By.className("complete-header");

    public CheckoutPage(WebDriver d) { this.driver = d; }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    public void checkout(String fn, String ln, String postalCode) {
        driver.findElement(checkoutBtn).click();
        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(this.postal).sendKeys(postalCode);
        driver.findElement(continueBtn).click();
        driver.findElement(finishBtn).click();
    }

    public boolean isOrderComplete() {
        return !driver.findElements(completeText).isEmpty();
    }
}
