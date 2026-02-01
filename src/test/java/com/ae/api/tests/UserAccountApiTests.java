package com.ae.api.tests;

import com.ae.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserAccountApiTests extends BaseApiTest {

    private final String email = "user_" + System.currentTimeMillis() + "@test.com";
    private final String password = "123456";
    private final String name = "Test User";

    @Test(description = "API-11: POST To Create/Register User Account", priority = 1)
    public void createAccount_shouldReturn201() {
        Response res = given()
                .spec(spec)
                .formParam("name", name)
                .formParam("email", email)
                .formParam("password", password)
                // optional fields (можно оставить, часто API принимает)
                .formParam("title", "Mr")
                .formParam("birth_date", "1")
                .formParam("birth_month", "1")
                .formParam("birth_year", "2000")
                .formParam("firstname", "Test")
                .formParam("lastname", "User")
                .formParam("company", "Company")
                .formParam("address1", "Address 1")
                .formParam("address2", "Address 2")
                .formParam("country", "India")
                .formParam("zipcode", "00000")
                .formParam("state", "State")
                .formParam("city", "City")
                .formParam("mobile_number", "1234567890")
                .when()
                .post("/createAccount")
                .then()
                .extract().response();

        Assert.assertEquals(res.statusCode(), 200, "HTTP status must be 200");

        int responseCode = safeGetInt(res, "responseCode");
        // У AutomationExercise обычно 201 для createAccount
        Assert.assertEquals(responseCode, 201, "Expected responseCode=201 for createAccount. Body: " + res.asString());
    }

    @Test(description = "API-14: GET user account detail by email", priority = 2, dependsOnMethods = "createAccount_shouldReturn201")
    public void getUserDetailByEmail_shouldReturn200() {
        Response res = given()
                .spec(spec)
                .queryParam("email", email)
                .when()
                .get("/getUserDetailByEmail")
                .then()
                .extract().response();

        Assert.assertEquals(res.statusCode(), 200, "HTTP status must be 200");

        int responseCode = safeGetInt(res, "responseCode");
        Assert.assertEquals(responseCode, 200,
                "Expected responseCode=200 for getUserDetailByEmail. Body: " + res.asString());
    }

    @Test(description = "API-13: PUT METHOD To Update User Account", priority = 3, dependsOnMethods = "createAccount_shouldReturn201")
    public void updateAccount_shouldReturn200() {
        Response res = given()
                .spec(spec)
                .formParam("name", "Updated Name")
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .put("/updateAccount")
                .then()
                .extract().response();

        Assert.assertEquals(res.statusCode(), 200, "HTTP status must be 200");

        int responseCode = safeGetInt(res, "responseCode");
        Assert.assertEquals(responseCode, 200,
                "Expected responseCode=200 for updateAccount. Body: " + res.asString());
    }

    @Test(description = "API-12: DELETE METHOD To Delete User Account", priority = 4, dependsOnMethods = "createAccount_shouldReturn201")
    public void deleteAccount_shouldReturn200() {
        Response res = given()
                .spec(spec)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete("/deleteAccount")
                .then()
                .extract().response();

        Assert.assertEquals(res.statusCode(), 200, "HTTP status must be 200");

        int responseCode = safeGetInt(res, "responseCode");
        Assert.assertEquals(responseCode, 200,
                "Expected responseCode=200 for deleteAccount. Body: " + res.asString());
    }

    private int safeGetInt(Response res, String key) {
        try {
            return res.jsonPath().getInt(key);
        } catch (Exception e) {
            return -1;
        }
    }
}