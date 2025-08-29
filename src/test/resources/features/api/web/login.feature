@web
Feature: SauceDemo Login and Shopping

  @positive
  Scenario: Successful login with valid credentials
    Given I open the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should see the homepage

  @negative
  Scenario: Login with invalid credentials
    Given I open the SauceDemo login page
    When I login with username "invalid_user" and password "wrong_pass"
    Then I should see a login error message

  @negative
  Scenario: Login with locked out user
    Given I open the SauceDemo login page
    When I login with username "locked_out_user" and password "secret_sauce"
    Then I should see a login error message

  @positive
  Scenario: Add item to cart
    Given I am logged in with "standard_user" and "secret_sauce"
    When I add the first product to the cart
    Then the cart should show 1 item

  @e2e
  Scenario: End to end checkout process
    Given I am logged in with "standard_user" and "secret_sauce"
    When I add the first product to the cart
    And I proceed to checkout with first name "John", last name "Doe", and postal code "12345"
    Then I should see the order confirmation
