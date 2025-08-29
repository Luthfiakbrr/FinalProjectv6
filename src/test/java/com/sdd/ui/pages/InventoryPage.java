package com.sdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inventoryList = By.className("inventory_list");
    private By addToCartFirst = By.cssSelector(".inventory_item button");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver d) {
        this.driver = d;
        this.wait = new WebDriverWait(d, Duration.ofSeconds(10));
    }

    public boolean isVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryList));
        return !driver.findElements(inventoryList).isEmpty();
    }

    // disamakan namanya dengan di step
    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartFirst)).click();
    }

    public String cartCount() {
        return driver.findElements(cartBadge).isEmpty()
                ? "0"
                : driver.findElement(cartBadge).getText();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public boolean isAtInventoryPage() {
        return isVisible();
    }
}
