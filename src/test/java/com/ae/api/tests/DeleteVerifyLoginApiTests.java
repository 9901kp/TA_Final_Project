package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteVerifyLoginApiTests extends BaseApiTest {

    @Test(description = "API-9: DELETE To Verify Login (should not be allowed)")
    public void deleteVerifyLogin_shouldHaveResponseCode405() {
        Response res = given()
                .when()
                .delete("/api/verifyLogin")
                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = res.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCode, 405, "Expected responseCode=405 for DELETE verifyLogin");
    }
}