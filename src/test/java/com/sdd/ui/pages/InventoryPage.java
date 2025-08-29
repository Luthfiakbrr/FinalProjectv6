package com.sdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;
    private By inventoryList = By.className("inventory_list");
    private By addToCartFirst = By.cssSelector(".inventory_item button");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver d) {
        this.driver = d;
    }

    public boolean isVisible() {
        return !driver.findElements(inventoryList).isEmpty();
    }

    // disamakan namanya dengan di step
    public void addFirstProductToCart() {
        driver.findElement(addToCartFirst).click();
    }

    public String cartCount() {
        return driver.findElements(cartBadge).isEmpty()
                ? "0"
                : driver.findElement(cartBadge).getText();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    // perbaikan: method untuk validasi halaman inventory
    public boolean isAtInventoryPage() {
        return isVisible(); // cukup cek apakah list produk tampil
    }
}
