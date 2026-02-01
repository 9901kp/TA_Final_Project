package com.ae.ui.base;

import com.ae.core.driver.DriverFactory;
import com.ae.core.driver.DriverManager;
import com.ae.ui.listeners.AllureTestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(AllureTestListener.class)
public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver("chrome");
        DriverManager.setDriver(driver);

        driver.get("https://automationexercise.com/");
//        System.out.println("URL=" + driver.getCurrentUrl());
//        System.out.println("TITLE=" + driver.getTitle());

    }


    @AfterMethod
    public void tearDown() {
        DriverManager.getDriver().quit();
        DriverManager.unload();
    }
}
