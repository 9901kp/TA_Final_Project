package com.ae.api.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    @BeforeClass(alwaysRun = true)
    public void setupApi() {
        RestAssured.baseURI = System.getProperty("apiBaseUrl", "https://automationexercise.com");
        RestAssured.filters(new AllureRestAssured()); // attaches request/response to Allure
    }
}
