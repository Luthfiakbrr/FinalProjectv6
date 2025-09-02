package com.sdd.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

/**
 * Utility class to handle unexpected popup/alert in tests
 */
public class PopupHandler {

    private WebDriver driver;

    public PopupHandler(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Check if an alert is present, and accept/dismiss it.
     * If no alert found, just ignore.
     */
    public void handlePopupIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Popup detected: " + alert.getText());
            alert.accept(); // bisa diganti alert.dismiss() kalau perlu
        } catch (NoAlertPresentException e) {
            // No popup found, do nothing
        }
    }
}
