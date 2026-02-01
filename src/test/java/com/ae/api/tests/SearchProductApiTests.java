package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchProductApiTests extends BaseApiTest {

    @Test
    public void searchProduct_withoutParam_shouldHaveResponseCode400() {
        Response res =
                given()
                        .when()
                        .post("/api/searchProduct")
                        .then()
                        .statusCode(200)
                        .extract().response();

        int responseCode = res.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 400);
    }
}
