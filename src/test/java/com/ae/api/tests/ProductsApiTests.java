package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsApiTests extends BaseApiTest {

    @Test
    public void postProductsList_shouldHaveResponseCode405() {
        Response res =
                given()
                        .when()
                        .post("/api/productsList")
                        .then()
                        .statusCode(200)
                        .extract().response();

        int responseCode = res.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 405);
    }
}
