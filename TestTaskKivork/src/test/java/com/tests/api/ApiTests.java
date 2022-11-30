package com.tests.api;

import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTests extends BaseApi {

    // TASK 2
    @Test
    public void GetApiCall() {
        response = given()
                .spec(specification)
                .when()
                .get("{ip}")
                .then()
                .extract().response();

        JsonPath jsonPathEvaluator = response.jsonPath();
        var latitude = jsonPathEvaluator.get("latitude");
        var longitude = jsonPathEvaluator.get("longitude");

        response
                .then()
                .assertThat().statusCode(200)
                .body("ip", Matchers.is(IP));
                //.body("latitude")
                //.body("longitude");
    }
}
