package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyLoginApiTests extends BaseApiTest {

    @Test(description = "API-7: POST To Verify Login with valid details")
    public void verifyLogin_valid_shouldReturn200() {
        Response response = stepVerifyLogin("test@test.com", "123456");
        Assert.assertEquals(response.statusCode(), 200);
        // У них часто текстовый message, проверяем по наличию ключевых слов
        Assert.assertTrue(response.asString().toLowerCase().contains("user"),
                "Expected response to mention user existence.");
    }

    @Step("POST /api/verifyLogin with email={email}")
    private Response stepVerifyLogin(String email, String password) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/api/verifyLogin")
                .then()
                .extract()
                .response();
    }
}
