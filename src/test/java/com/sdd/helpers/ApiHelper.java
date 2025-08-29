package com.sdd.helpers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiHelper {
    public static final String APP_ID = "63a804408eb0cb069b57e43a";
    public static final String BASE = "https://dummyapi.io/data/v1";

    static {
        RestAssured.baseURI = BASE;
    }

    public static RequestSpecification givenSpec() {
        return RestAssured.given()
                .header("app-id", APP_ID)
                .contentType("application/json");
    }
}
