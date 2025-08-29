package com.sdd.ui.stepdefinitions;

import com.sdd.ui.pages.CheckoutPage;
import com.sdd.ui.pages.InventoryPage;
import com.sdd.ui.pages.LoginPage;
import com.sdd.ui.WebDriverManager;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;

    @Given("I open the SauceDemo login page")
    public void iOpenTheSauceDemoLoginPage() {
        driver = WebDriverManager.getDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage.open();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see the homepage")
    public void iShouldSeeTheHomepage() {
        Assert.assertTrue("User should be redirected to inventory page",
                inventoryPage.isAtInventoryPage());
    }

    @Then("I should see a login error message")
    public void iShouldSeeALoginErrorMessage() {
        Assert.assertTrue("Expected error message not found",
                loginPage.getErrorMessage().contains("Epic sadface"));
    }

    @Given("I am logged in with {string} and {string}")
    public void iAmLoggedInWithAnd(String username, String password) {
        driver = WebDriverManager.getDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage.open();
        loginPage.login(username, password);
        Assert.assertTrue("Login failed, not redirected to inventory page",
                inventoryPage.isAtInventoryPage());
    }

    @When("I add the first product to the cart")
    public void iAddTheFirstProductToTheCart() {
        inventoryPage.addFirstProductToCart();
    }

    @Then("the cart should show {int} item")
    public void theCartShouldShowItem(int expected) {
        Assert.assertEquals("Cart count mismatch",
                String.valueOf(expected), inventoryPage.cartCount());
    }

    @When("I proceed to checkout with first name {string}, last name {string}, and postal code {string}")
    public void iProceedToCheckoutWithFirstNameLastNameAndPostalCode(String first, String last, String postal) {
        inventoryPage.goToCart();
        checkoutPage.checkout(first, last, postal);
    }

    @Then("I should see the order confirmation")
    public void iShouldSeeTheOrderConfirmation() {
        Assert.assertTrue("Order confirmation page not found!",
                checkoutPage.isOrderComplete());
    }
}
