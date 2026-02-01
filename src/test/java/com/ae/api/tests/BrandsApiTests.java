package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BrandsApiTests extends BaseApiTest {

    @Test(description = "API-3: GET All Brands List")
    public void getAllBrandsList_shouldReturn200() {
        Response response = stepGetBrands();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.asString().toLowerCase().contains("brands"),
                "Response should contain 'brands'");
    }

    @Step("GET /api/brandsList")
    private Response stepGetBrands() {
        return given()
                .when()
                .get("/api/brandsList")
                .then()
                .extract()
                .response();
    }
}
