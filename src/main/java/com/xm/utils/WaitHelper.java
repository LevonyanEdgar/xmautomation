package com.xm.utils;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static com.xm.base.DriverBase.getDriver;
import static com.xm.config.Configuration.SELENIUM_URL;

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

    public WebElement waitElementToBeClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementToBeVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> waitListElementWithIndexToBeVisible(List<WebElement> elements) {
        return wait.until(XMExpectedConditions.numberOfElementsToBeMoreThan(elements, 0));
    }

    public List<WebElement> waitListElementWithIndexToBeVisible(List<WebElement> elements, int index) {
        return wait.until(XMExpectedConditions.numberOfElementsToBeMoreThan(elements, index));
    }

    public WebElement waitListElementWithIndexToBeClickable(List<WebElement> elements, int index) {
        return wait.until(XMExpectedConditions.elementOfListToBeClickable(elements, index));
    }

    public WebElement waitElementTextToBeFill(WebElement elements) {
        return wait.until(XMExpectedConditions.elementTextNotPresent(elements));
    }

    public WebElement waitListElementWithIndexToBeClickable(List<WebElement> elements) {
        return wait.until(XMExpectedConditions.elementOfListToBeClickable(elements, 0));
    }

    public WebElement waitListElementWithTextToBeClickable(List<WebElement> elements, String text) {
        return wait.until(XMExpectedConditions.elementByTextOfListToBeClickable(elements, text));
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

    public WebElement waitElementToBeVisible(WebElement element, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return webDriverWait.until(XMExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(webDriver -> element);
    }

    public WebElement waitForElementToBeVisible(By by) {
        return wait.until(webDriver -> webDriver.findElement(by));
    }

    public Boolean waitElementToBeInvisible(By by) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WaitHelper waitElementToBeInvisible(WebElement element) {
        wait.until(driver -> !isElementDisplayed(element));
        return this;
    }

    public WaitHelper waitElementToExistInDOM(WebElement element) {
        wait.until(e -> element.isDisplayed());
        return this;
    }

    public Boolean waitElementTextToBe(By locator, String expectedText) {
        return wait.until(ExpectedConditions.textToBe(locator, expectedText));
    }

    public Boolean textToBePresentInElement(WebElement element, String expectedText) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    public Boolean textToBePresentInElements(List<WebElement> elements, String expectedText) {
        return wait.until(XMExpectedConditions.textToBePresentInElements(elements, expectedText));
    }

    public void containsAttributeToChangeTo(WebElement element, String attribute, String value) {
        wait.until(XMExpectedConditions.containsAttributeChangeTo(element, attribute, value));
    }

    public WaitHelper elementNotPresent(WebElement element) {
        wait.until(XMExpectedConditions.elementNotPresent(element));
        return this;
    }

    public WaitHelper elementNotPresent(List<WebElement> element, int index) {
        wait.until(XMExpectedConditions.elementNotPresent(element.get(index)));
        return this;
    }

    public WaitHelper elementNotPresent(By by) {
        return elementNotPresent(getDriver().findElement(by));
    }

    public WaitHelper elementToBePresent(By by) {
        wait.until(XMExpectedConditions.elementToBePresent(by));
        return this;
    }

    public WaitHelper elementToBePresent(WebElement element) {
        wait.until(XMExpectedConditions.elementToBePresent(element));
        return this;
    }

    public WaitHelper waitForElementHeightIsGreaterThenZero(WebElement element) {
        wait.until((driver) -> Integer.parseInt(element.getAttribute("style").replaceAll("[^0-9]", "")) > 0);
        return this;
    }

    public WaitHelper waitForElementAttributeValueDisappear(WebElement element, String attr, String value) {
        wait.until(XMExpectedConditions.elementAttributeValueDisappear(element, attr, value));
        return this;
    }

    public WaitHelper waitForElementAttributeAppear(List<WebElement> elements, WebElement element, String attr, String value) {
        waitListElementWithIndexToBeVisible(elements);
        wait.until(XMExpectedConditions.scrollUntilElementAttributeAppear(elements, element, attr, value));
        return this;
    }

    public WaitHelper waitForSizeChanged(int beforeCount, By elementLocation) {
        wait.until(driver -> {
            int elementCount = driver.findElements(elementLocation).size();
            return elementCount > beforeCount || elementCount < beforeCount;
        });
        return this;
    }

    public WaitHelper waitForTextChanged(String beforeText, WebElement elementLocation) {
        wait.until(driver -> !Objects.equals(elementLocation.getText(), beforeText));
        return this;
    }

    public WaitHelper waitForSizeChanged(int beforeCount, List<WebElement> elements) {
        wait.until(driver -> {
            int elementCount = elements.size();
            return elementCount > beforeCount || elementCount < beforeCount;
        });
        return this;
    }

    public WaitHelper waitForElementsSizeToBe(int count, By elementLocation) {
        wait.until(driver -> {
            int elementCount = driver.findElements(elementLocation).size();
            return elementCount == count;
        });
        return this;
    }

    public WaitHelper waitForUrlChangedTo(String url) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.urlContains(url));
        return this;
    }

    public void waitForPageReady() {
        wait.until((ExpectedConditions.jsReturnsValue("return document.readyState=='complete';")));
    }

    public void waitSteadinessOfElementLocated(WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20)).until(XMExpectedConditions.steadinessOfElementLocated(element));
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

    public void waitForImageRender(WebElement element) {
        String script = "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0";
        wait.until(XMExpectedConditions.executeScript(script, element));
    }

    public void sleep() {
        sleep(3000);
    }

    public void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
            //ignore
        }
    }

    public ExpectedCondition<Boolean> waitForElementsSizeBe(final By location, final int size) {
        return driver -> {
            try {
                assert driver != null;
                return (driver.findElements(location).size() == size);
            } catch (Exception e) {
                return false; // catchall fail case
            }
        };
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public void waitListWithIndexToBe(List<String> list, int index) {
        wait.until(XMExpectedConditions.listWithIndexToBeExist(list, index));
    }

    public void waitListElementWithIndexToBe(List<WebElement> list, int index) {
        wait.until(XMExpectedConditions.listElementWithIndexToBeExist(list, index));
    }

    public void waitListElementWithIndexToBe(List<WebElement> list) {
        wait.until(XMExpectedConditions.listElementWithIndexToBeExist(list, 0));
    }

    public void waitForItemToBeInterceptableAndClick(WebElement element) {
        wait.until(XMExpectedConditions.waitForElementInteracting(element));
    }

    public void waitUntilTabsCountBeMoreThen(int count) {
        wait.until(XMExpectedConditions.moreThanOneTabWillOpen(count));
    }

    public void oneOfElementsContainAttribute(List<WebElement> elements, String attribute, String value) {
        wait.until(XMExpectedConditions.oneOfElementsContainAttribute(elements, attribute, value));
    }

    public void attributeToChange(WebElement element, String attribute) {
        String attributeBefore = element.getAttribute(attribute);
        wait.until(XMExpectedConditions.attributeToChange(element, attribute, attributeBefore));
    }

    public void attributeToChange(WebElement element, String attribute, String attributeBefore) {
        wait.until(XMExpectedConditions.attributeToChange(element, attribute, attributeBefore));
    }

    public void attributeToChangeTo(WebElement element, String attribute, String value) {
        wait.until(XMExpectedConditions.attributeChangeTo(element, attribute, value));
    }

    public File waitUntilFileNameExistInFolder(File folder, String name) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(LONG_TIMEOUT)).until(XMExpectedConditions.fileNameContainsExist(folder, name));
    }

    public InputStream waitUntilFileExistInRemote(URL url) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(LONG_TIMEOUT)).until(XMExpectedConditions.fileExistInRemote(url));
    }

    public String waitUntilFileExistInRemote(URL url, String name) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(LONG_TIMEOUT)).until(XMExpectedConditions.fileExistInRemoteByPartialName(url, name));
    }

    public Object executeScript(String script, WebElement... elements) {
        return wait.until(XMExpectedConditions.executeScript(script, elements));
    }

    public void executeScript(String script) {
        ((JavascriptExecutor) getDriver()).executeScript(script);
    }

    public void executeScriptUntilSuccess(String script) {
        wait.until(XMExpectedConditions.scriptToBeSuccess(script));
    }


    public Object executeScript(String script, Object... params) {
        return ((JavascriptExecutor) getDriver()).executeScript(script, params);
    }

    public WebElement waitElementToBeIntractable(WebElement element) {
        return wait.until(XMExpectedConditions.intractableTypeInElement(element));
    }

    public WaitHelper waitForUrlChangedFrom(String previousUrl) {
        wait.until(driver -> {
            String url = driver != null ? driver.getCurrentUrl() : "";
            return !url.equals(SELENIUM_URL + previousUrl);
        });
        return this;
    }

    public boolean waitForCssValueToBe(WebElement element, String cssStyle, String cssValue) {
        return wait.until(XMExpectedConditions.cssValueChangeTo(element, cssStyle, cssValue));
    }

}