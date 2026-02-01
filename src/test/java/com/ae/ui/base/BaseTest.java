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
        bypassGoogleVignette(driver);
    }

    private void bypassGoogleVignette(WebDriver driver) {
        for (int i = 0; i < 5; i++) {
            String url = driver.getCurrentUrl();
            if (url != null && url.contains("google_vignette")) {
                try {
                    driver.navigate().to("https://automationexercise.com/");
                    Thread.sleep(400);
                } catch (Exception ignored) {}
            } else {
                break;
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.getDriver().quit();
        DriverManager.unload();
    }
}