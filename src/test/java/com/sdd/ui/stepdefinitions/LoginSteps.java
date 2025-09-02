package com.sdd.ui.stepdefinitions;

import com.sdd.helpers.PopupHandler;
import com.sdd.hooks.Hooks;
import com.sdd.ui.pages.LoginPage;
import com.sdd.ui.pages.InventoryPage;
import com.sdd.ui.pages.CartPage;
import com.sdd.ui.pages.CheckoutPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public LoginSteps() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);

        // Handle popup jika muncul
        new PopupHandler(driver).handlePopupIfPresent();
    }

    // ---------------- LOGIN ----------------
    @Given("I open the SauceDemo login page")
    public void iOpenTheSauceDemoLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);

        // Handle popup setelah login
        new PopupHandler(driver).handlePopupIfPresent();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage() {
        Assert.assertTrue("User not redirected to inventory page!", inventoryPage.isOnInventoryPage());
    }

    @Then("I should see a login error message")
    public void iShouldSeeALoginErrorMessage() {
        Assert.assertTrue("Login error message not displayed!", loginPage.isErrorMessageDisplayed());
    }

    @Given("I am logged in with {string} and {string}")
    public void iAmLoggedInWithAnd(String username, String password) {
        driver.get("https://www.saucedemo.com/");
        loginPage.login(username, password);
        Assert.assertTrue("Failed to login with provided credentials!", inventoryPage.isOnInventoryPage());
    }

    // ---------------- SHOPPING CART ----------------
    @When("I add the first product to the cart")
    public void iAddFirstProductToCart() {
        inventoryPage.addBackpackToCart();
    }

    @Then("the cart should show {int} item")
    public void cartShouldShowItem(int count) {
        Assert.assertEquals(
                "Cart count mismatch!",
                String.valueOf(count),
                inventoryPage.getCartCount()
        );
    }

    // ---------------- CHECKOUT ----------------
    @When("I proceed to checkout with first name {string}, last name {string}, and postal code {string}")
    public void iProceedToCheckout(String firstName, String lastName, String postalCode) {
        cartPage.goToCart();
        cartPage.clickCheckout();

        // Handle popup setelah login
        new PopupHandler(driver).handlePopupIfPresent();

        checkoutPage.fillCheckoutForm(firstName, lastName, postalCode);

        // Handle popup setelah login
        new PopupHandler(driver).handlePopupIfPresent();

        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

    }

    @Then("I should see the order confirmation")
    public void iShouldSeeTheOrderConfirmation() {
        String successMessage = checkoutPage.getSuccessMessage();
        Assert.assertEquals("Thank you for your order!", successMessage);

        // Handle popup setelah login
        new PopupHandler(driver).handlePopupIfPresent();
    }
}
