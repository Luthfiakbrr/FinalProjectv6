package com.sdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Inventory Page - halaman utama setelah login
 */
public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstAddToCartButton = By.cssSelector(".inventory_item button.btn_primary");
    private By backpackAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");
    private By inventoryContainer = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void addFirstProductToCart() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton));
        addBtn.click();
    }

    public void addBackpackToCart() {
        WebElement addBackpack = wait.until(ExpectedConditions.elementToBeClickable(backpackAddButton));
        addBackpack.click();
    }

    public String getCartCount() {
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon));
        return cart.getText().trim();
    }

    public void goToCart() {
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cart.click();
    }

    public boolean isOnInventoryPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer)).isDisplayed();
    }
}
