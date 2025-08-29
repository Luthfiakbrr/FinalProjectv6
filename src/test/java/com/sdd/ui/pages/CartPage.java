package com.sdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private By cartLink = By.className("shopping_cart_link");
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver d) {
        this.driver = d;
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}
