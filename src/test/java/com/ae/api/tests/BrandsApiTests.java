package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BrandsApiTests extends BaseApiTest {

    @Test
    public void getAllBrandsList_shouldReturn200() {
        Response res = given()
                .spec(spec)
                .when()
                .get("/brandsList");

        Assert.assertEquals(res.statusCode(), 200);
    }
}
