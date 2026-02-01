package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllProductsApiTests extends BaseApiTest {

    @Test(description = "API-1: Get All Products List")
    public void getAllProductsList_shouldReturn200() {
        Response res = given()
                .spec(spec)
                .when()
                .get("/productsList")
                .then()
                .extract().response();

        Assert.assertEquals(res.statusCode(), 200);

        String body = res.asString().toLowerCase();
        Assert.assertTrue(body.contains("products") || body.contains("responsecode"),
                "Expected response to contain products or responseCode. Body: " + res.asString());
    }
}