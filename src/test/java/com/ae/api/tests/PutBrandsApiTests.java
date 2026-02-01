package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutBrandsApiTests extends BaseApiTest {

    @Test(description = "API-4: PUT To All Brands List (should not be allowed)")
    public void putBrandsList_shouldHaveResponseCode405() {
        Response res = given()
                .when()
                .put("/api/brandsList")
                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = res.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 405, "Expected responseCode=405 for PUT brandsList");
    }
}