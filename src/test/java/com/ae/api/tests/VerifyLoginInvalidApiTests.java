package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyLoginInvalidApiTests extends BaseApiTest {

    @Test(description = "API-10: POST To Verify Login with invalid details")
    public void verifyLogin_invalid_shouldHaveErrorResponseCode() {
        Response res = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", "wrong_email@example.com")
                .formParam("password", "wrong_password")
                .when()
                .post("/api/verifyLogin")
                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = res.jsonPath().getInt("responseCode");

        // На сайте иногда 404 для invalid, но если вдруг поменяется - главное что НЕ 200
        Assert.assertTrue(responseCode != 200,
                "Expected responseCode NOT to be 200 for invalid login. Actual: " + responseCode);
    }
}