package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchProductWithParamApiTests extends BaseApiTest {

    @Test(description = "API-5: POST To Search Product with parameter")
    public void searchProduct_withParam_shouldReturn200AndProducts() {
        Response res = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("search_product", "top")
                .when()
                .post("/api/searchProduct")
                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = res.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 200, "Expected responseCode=200");

        String body = res.asString().toLowerCase();
        Assert.assertTrue(body.contains("products") || body.contains("top"),
                "Expected response to contain products. Body: " + res.asString());
    }
}