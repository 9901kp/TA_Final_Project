package com.ae.api.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    protected RequestSpecification spec;

    @BeforeClass(alwaysRun = true)
    public void setupApi() {
        String baseUrl = System.getProperty("apiBaseUrl", "https://automationexercise.com");
        RestAssured.baseURI = baseUrl;

        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath("/api")
                .setContentType(ContentType.URLENC)   // у них много POST через form params
                .addFilter(new AllureRestAssured())   // attaches request/response to Allure
                .build();
    }
}
