package com.sdd.api.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class UserSteps {

    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String APP_ID = "63a804408eb0cb069b57e43a";

    private static String createdUserId;
    private String endpoint;
    private Response response;

    // ----------- GIVEN STEPS -----------
    @Given("I set GET endpoint {string}")
    public void i_set_get_endpoint(String endpoint) {
        this.endpoint = BASE_URL + endpoint;
    }

    @Given("I set POST endpoint {string}")
    public void i_set_post_endpoint(String endpoint) {
        this.endpoint = BASE_URL + endpoint;
    }

    @Given("I set PUT endpoint {string}")
    public void i_set_put_endpoint(String endpoint) {
        this.endpoint = BASE_URL + endpoint;
    }

    @Given("I set DELETE endpoint {string}")
    public void i_set_delete_endpoint(String endpoint) {
        this.endpoint = BASE_URL + endpoint;
    }

    // ----------- WHEN STEPS -----------
    @When("I send GET request")
    public void i_send_get_request() {
        response = given()
                .header("app-id", APP_ID)
                .when()
                .get(endpoint);
        logResponse();
    }

    @When("I send POST request with valid user data")
    public void i_send_post_request_with_valid_user_data() {
        JSONObject body = new JSONObject();
        body.put("firstName", "John");
        body.put("lastName", "Doe");
        body.put("email", "john" + System.currentTimeMillis() + "@example.com"); // email wajib

        response = given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .when()
                .post(endpoint);

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            createdUserId = response.jsonPath().getString("id");
        }

        logResponse();
    }

    @When("I send PUT request to update user lastName")
    public void i_send_put_request_to_update_user_email() {
        JSONObject body = new JSONObject();
        body.put("email", "updated" + System.currentTimeMillis() + "@example.com");

        String targetEndpoint = (createdUserId != null) ? BASE_URL + "/user/" + createdUserId : endpoint;

        response = given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .when()
                .put(targetEndpoint);

        logResponse();
    }

    @When("I send DELETE request")
    public void i_send_delete_request() {
        String targetEndpoint = (createdUserId != null) ? BASE_URL + "/user/" + createdUserId : endpoint;

        response = given()
                .header("app-id", APP_ID)
                .when()
                .delete(targetEndpoint);
        logResponse();
    }

    // ----------- THEN STEPS -----------
    @Then("response status should be {int}")
    public void response_status_should_be(Integer expectedStatus) {
        int actual = response.statusCode();
        logResponse();
        Assert.assertEquals(
                "Expected status " + expectedStatus + " but got " + actual +
                        " with body: " + response.getBody().asString(),
                expectedStatus.intValue(),
                actual
        );
    }

    @Then("response status should be one of {int} or {int}")
    public void response_status_should_be_one_of(Integer status1, Integer status2) {
        int actual = response.statusCode();
        logResponse();
        Assert.assertTrue(
                "Expected status " + status1 + " or " + status2 + " but got " + actual +
                        " with body: " + response.getBody().asString(),
                actual == status1 || actual == status2
        );
    }

    // ----------- Helper Method -----------
    private void logResponse() {
        System.out.println("---- API RESPONSE ----");
        if (response != null) {
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody().asPrettyString());
        } else {
            System.out.println("Response is NULL!");
        }
        System.out.println("----------------------");
    }
}
