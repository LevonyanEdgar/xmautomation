package com.xm.utils;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.xm.base.DriverBase.getDriver;

public class WaitHelper {

    private static final int TIMEOUT = 60;
    private static final int SHORT_TIMEOUT = 10;
    private static final int SCRIPT_TIMEOUT = 20;
    private static final int LONG_TIMEOUT = 90;
    private static WebDriverWait wait;

    private WaitHelper() {
    }

    public static WaitHelper getWait() {
        return getCustomWait(TIMEOUT);
    }

    public static WaitHelper getLongWait() {
        return getCustomWait(LONG_TIMEOUT);
    }

    public static WaitHelper getScriptWait() {
        return getCustomWait(SCRIPT_TIMEOUT, 200);
    }

    public static WaitHelper getShortWait() {
        return getCustomWait(SHORT_TIMEOUT);
    }

    public static WaitHelper getCustomWait(int duration) {
        WaitHelper waitHelper = new WaitHelper();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(duration), Duration.ofMillis(50));
        return waitHelper;
    }

    public static WaitHelper getCustomWait(int duration, int pingTime) {
        WaitHelper waitHelper = new WaitHelper();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(duration), Duration.ofMillis(pingTime));
        return waitHelper;
    }


    public WebElement waitElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public List<WebElement> waitListElementWithIndexToBeVisible(List<WebElement> elements) {
        return wait.until(XMExpectedConditions.numberOfElementsToBeMoreThan(elements, 0));
    }

    public WebElement waitElementToBeVisible(WebElement element) {
        return wait.until(XMExpectedConditions.visibilityOf(element));
    }

    public WebElement waitElementToBeEnabled(WebElement element) {
        return wait.until(XMExpectedConditions.elementEnabled(element));
    }

    public WebElement waitElementToBeEnabled(WebElement element, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return webDriverWait.until(XMExpectedConditions.elementEnabled(element));
    }


    public WaitHelper waitForUrlChangedTo(String url) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.urlContains(url));
        return this;
    }

    public void waitForPageReady() {
        wait.until((ExpectedConditions.jsReturnsValue("return document.readyState=='complete';")));
    }


    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return jQuery.active").toString().equals("0");
        };
        try {
            Thread.sleep(1000);
            wait.until(expectation);
        } catch (Exception e) {
            //ignore
        }
    }



    public void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
            //ignore
        }
    }


    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }


    public void waitListElementWithIndexToBe(List<WebElement> list, int index) {
        wait.until(XMExpectedConditions.listElementWithIndexToBeExist(list, index));
    }

    public Object executeScript(String script, WebElement... elements) {
        return wait.until(XMExpectedConditions.executeScript(script, elements));
    }


}