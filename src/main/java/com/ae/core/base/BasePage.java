package com.ae.core.base;

import com.ae.core.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;


import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException(
                    "WebDriver is null. Did you forget to call DriverManager.setDriver(driver) in BaseTest?"
            );
        }
    }

    protected WebDriverWait wait10() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebDriverWait waitSeconds(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    /** Wait element visible and return it */
    protected WebElement visible(By by) {
        return wait10().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /** Wait element clickable and return it */
    protected WebElement clickable(By by) {
        return wait10().until(ExpectedConditions.elementToBeClickable(by));
    }

    /** Safe click with retries + scroll */
    protected void click(By by) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement el = clickable(by);
                scrollIntoView(el);
                el.click();
                return;
            } catch (ElementClickInterceptedException e) {
                smallScrollDown();
            } catch (StaleElementReferenceException e) {
                // retry
            } catch (WebDriverException e) {
                if (attempts == 2) throw e;
            }
            attempts++;
        }
    }

    /** Type text (clear + sendKeys) */
    protected void type(By by, String text) {
        WebElement el = visible(by);
        scrollIntoView(el);
        el.clear();
        el.sendKeys(text);
    }

    /** Get text */
    protected String text(By by) {
        return visible(by).getText();
    }

    /** Scroll element into view */
    protected void scrollIntoView(WebElement el) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el
            );
        } catch (Exception ignored) {}
    }

    /** Small scroll down to avoid fixed headers/overlays */
    protected void smallScrollDown() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");
        } catch (Exception ignored) {}
    }

    /** Wait until URL contains substring */
    protected void waitUrlContains(String part) {
        wait10().until(ExpectedConditions.urlContains(part));
    }

    /** Wait until ANY of the locators becomes visible (displayed) */
    protected void waitAnyVisible(By... locators) {
        ExpectedCondition<Boolean> anyVisible = d -> {
            for (By by : locators) {
                try {
                    for (WebElement el : d.findElements(by)) {
                        if (el != null && el.isDisplayed()) return true;
                    }
                } catch (Exception ignored) {}
            }
            return false;
        };
        wait10().until(anyVisible);
    }

    /** Pause (do NOT name it wait() because Object.wait() exists) */
    protected void pauseMs(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    /** Hover (if needed) */
    protected void hover(By by) {
        WebElement el = visible(by);
        new Actions(driver).moveToElement(el).perform();
    }

    /** Wait until document.readyState == "complete" */
    protected void waitPageReady() {
        wait10().until(d -> {
            try {
                return "complete".equals(((JavascriptExecutor) d)
                        .executeScript("return document.readyState"));
            } catch (Exception e) {
                return true; // если JS недоступен — не валим тест
            }
        });
    }

    /** Returns true if element exists and is displayed (no wait) */
    protected boolean isVisible(By by) {
        try {
            WebElement el = driver.findElement(by);
            return el.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
    protected void acceptAlertIfPresent() {
        try {
            waitSeconds(3).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (TimeoutException ignored) {
            // no alert
        }
    }

    protected boolean isVisible(By by, long seconds) {
        try {
            return waitSeconds(seconds).until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Wait until ANY locator becomes visible */
    protected boolean waitAnyVisible(long seconds, By... locators) {
        try {
            return waitSeconds(seconds).until(d -> {
                for (By by : locators) {
                    try {
                        for (WebElement el : d.findElements(by)) {
                            if (el != null && el.isDisplayed()) return true;
                        }
                    } catch (Exception ignored) {}
                }
                return false;
            });
        } catch (Exception e) {
            return false;
        }
    }

    protected void ensureNotOnVignette(String expectedUrl) {
        String url = driver.getCurrentUrl();
        if (url != null && url.contains("google_vignette")) {
            driver.get(expectedUrl);
            waitPageReady();
        }
    }


}
