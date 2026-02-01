package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyLoginMissingEmailApiTests extends BaseApiTest {

    @Test(description = "API-8: POST To Verify Login without email parameter")
    public void verifyLogin_withoutEmail_shouldHaveResponseCode400() {
        Response res = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("password", "123456")
                .when()
                .post("/api/verifyLogin")
                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = res.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 400, "Expected responseCode=400 when email is missing");
    }
}