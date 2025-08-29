@api
Feature: DummyAPI User endpoints
  Testing positive and negative scenarios for user management

  @positive @get
  Scenario: Get list of users (positive)
    Given I set GET endpoint "/user"
    When I send GET request
    Then response status should be 200

  @positive @get
  Scenario: Get user by valid id (positive)
    Given I set GET endpoint "/user/60d0fe4f5311236168a109d4"
    When I send GET request
    Then response status should be 200

  @positive @post
  Scenario: Create user (positive)
    Given I set POST endpoint "/user/create"
    When I send POST request with valid user data
    Then response status should be one of 200 or 201

  @positive @put
  Scenario: Update user last name (positive)
    Given I set PUT endpoint "/user/60d0fe4f5311236168a109d4"
    When I send PUT request to update user lastName
    Then response status should be 200

  @negative @delete
  Scenario: Delete non-existing user (negative)
    Given I set DELETE endpoint "/user/invalidId123"
    When I send DELETE request
    Then response status should be one of 200 or 400
