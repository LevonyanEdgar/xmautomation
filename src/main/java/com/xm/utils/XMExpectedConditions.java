package com.xm.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.xm.base.DriverBase.getDriver;
@Log4j2
public class XMExpectedConditions {

    private XMExpectedConditions() {
    }

    public static ExpectedCondition<WebElement> steadinessOfElementLocated(final By locator) {
        return new ExpectedCondition<>() {
            private WebElement element = null;
            private Point location = null;

            @Override
            public WebElement apply(WebDriver driver) {
                if (element == null) {
                    try {
                        element = driver.findElement(locator);
                    } catch (NoSuchElementException e) {
                        return null;
                    }
                }

                try {
                    if (element.isDisplayed()) {
                        Point locationPoint = element.getLocation();
                        if (locationPoint.equals(location)) {
                            return element;
                        }
                        location = locationPoint;
                    }
                } catch (StaleElementReferenceException e) {
                    element = null;
                }
                return null;
            }

            @Override
            public String toString() {
                return "steadiness of element located by " + locator;
            }
        };
    }

    public static ExpectedCondition<WebElement> steadinessOfElementLocated(final WebElement element) {
        return new ExpectedCondition<>() {
            private Point location = null;

            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    if (element.isEnabled()) {
                        Point locationPoint = element.getLocation();
                        if (locationPoint.equals(location)) {
                            return element;
                        }
                        location = locationPoint;
                    }
                } catch (StaleElementReferenceException | ElementClickInterceptedException ignore) {
                }
                return null;
            }

            @Override
            public String toString() {
                return "steadiness of element " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> onlyOneTabWillBeOpen() {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                if (getDriver().getWindowHandles().size() == 1) {
                    List<String> windowHandles = new ArrayList<>(getDriver().getWindowHandles());
                    getDriver().switchTo().window(windowHandles.get(0));
                    return true;
                }
                return null;
            }

            @Override
            public String toString() {
                return "Only one tab to be open";
            }
        };
    }

    public static ExpectedCondition<Boolean> listElementWithIndexToBeExist(List<WebElement> list, int index) {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    if (list.size() > index) {
                        return true;
                    }
                } catch (Exception e) {
                    return null;
                }
                return null;
            }

            @Override
            public String toString() {
                return "list element with" + index + " index to be exist. Element " + list;
            }
        };
    }

    public static ExpectedCondition<Boolean> listWithIndexToBeExist(List<String> list, int index) {
        return webDriver -> {
            try {
                if (list.size() > index) {
                    return true;
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        };

    }

    public static ExpectedCondition<WebElement> waitForElementInteracting(WebElement element) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try {
                    element.click();
                } catch (ElementClickInterceptedException | StaleElementReferenceException ignore) {
                    return null;
                }
                return element;
            }

            @Override
            public String toString() {
                return "Element is not intractable : " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> sliderItemClickWhenClickable(WebElement element) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try {
                    element.click();
                } catch (ElementNotInteractableException ignore) {
                    return null;
                }
                return element;
            }

            @Override
            public String toString() {
                return "slider Item Click When Clickable : " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> moreThanOneTabWillOpen(int count) {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                if (getDriver().getWindowHandles().size() > count) {
                    return true;
                }
                return null;
            }

            @Override
            public String toString() {
                return "more Than One Tab Is Open";
            }
        };
    }

    public static ExpectedCondition<WebElement> attributeToChange(WebElement element, String attribute, String attributeBefore) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                if (!element.getAttribute(attribute).equals(attributeBefore)) {
                    return element;
                }
                return null;
            }

            @Override
            public String toString() {
                return "attribute To Change from : " + attributeBefore + " to : " + attribute + " element : " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> containsAttributeChangeTo(WebElement element, String attribute, String value) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                if (element.getAttribute(attribute).contains(value)) {
                    return element;
                }
                return null;
            }

            @Override
            public String toString() {
                return attribute + " attribute To Change to : " + value + " element : " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> oneOfElementsContainAttribute(List<WebElement> elements, String attribute, String value) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                for (WebElement element : elements) {
                    try {
                        if (element.getAttribute(attribute).contains(value)) {
                            return element;
                        }
                    } catch (Exception ignore) {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public String toString() {
                return "one Of Elements Contain Attribute attribute : " + attribute + " value : " + value + " element : " + elements.get(0);
            }
        };
    }

    public static ExpectedCondition<WebElement> attributeChangeTo(WebElement element, String attribute, String value) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                if (element.getAttribute(attribute).equals(value)) {
                    return element;
                }
                return null;
            }

            @Override
            public String toString() {
                return attribute + " attribute To Change to : " + value + " element : " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> executeScript(String script, WebElement... params) {
        return webDriver -> (Boolean) ((JavascriptExecutor) getDriver()).executeScript(script, params);
    }

    public static ExpectedCondition<WebElement> elementToBePresent(By by) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try {
                    getDriver().findElement(by).isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException ignore) {
                    return null;
                }
                return getDriver().findElement(by);
            }

            @Override
            public String toString() {
                return "element To Be Present : " + by;
            }
        };
    }

    public static ExpectedCondition<WebElement> elementToBePresent(WebElement element) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try {
                    element.isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException ignore) {
                    return null;
                }
                return element;
            }

            @Override
            public String toString() {
                return "element To Be Present : " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> scriptToBeSuccess(String script) {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver webDriver) {

                try {
                    ((JavascriptExecutor) getDriver()).executeScript(script);
                    return true;
                } catch (Exception e) {
                    log.error(e.getStackTrace());
                    return null;
                }
            }

            @Override
            public String toString() {
                return " execute Script : " + script;
            }
        };
    }

    public static ExpectedCondition<WebElement> elementNotPresent(WebElement element) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try {
                    element.isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return element;
                }
                return null;
            }

            @Override
            public String toString() {
                return "element To not Present : " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> elementTextNotPresent(WebElement element) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try {
                    if (!element.getText().isEmpty()) {
                        return element;
                    }
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return null;
                }
                return null;
            }

            @Override
            public String toString() {
                return "Element Text not Present : " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> elementOfListToBeClickable(List<WebElement> elements, int index) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try {
                    WebElement visibleElement = ExpectedConditions.visibilityOf(elements.get(index)).apply(webDriver);
                    return visibleElement != null && visibleElement.isEnabled() ? visibleElement : null;
                } catch (Exception ignore) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Element Of List To Be Clickable. index : " + index + ". element : " + elements;
            }
        };
    }

    public static ExpectedCondition<WebElement> elementByTextOfListToBeClickable(List<WebElement> elements, String text) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                try {
                    for (WebElement element : elements) {
                        if (element.getText().equals(text)) {
                            WebElement visibleElement = ExpectedConditions.visibilityOf(element).apply(webDriver);
                            return visibleElement != null && visibleElement.isEnabled() ? visibleElement : null;
                        }
                    }
                } catch (Exception ignore) {
                    return null;
                }
                return null;
            }

            @Override
            public String toString() {
                return "Element By Text Of List To Be Clickable. Elements : " + elements + ". Test : " + text;
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInElements(List<WebElement> elements, String text) {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    elements.stream().filter(el -> el.getText().equals(text))
                            .findAny().orElseThrow();
                    return true;
                } catch (Exception ignore) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "Element By Text Of List To Be Clickable. Elements : " + elements + ". Text : " + text;
            }
        };
    }

    public static ExpectedCondition<Boolean> fileExist(File file) {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return file.exists();
                } catch (Exception ignore) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "file Exist. Path : " + file.getPath();
            }
        };
    }

    public static ExpectedCondition<File> fileNameContainsExist(File folder, String name) {
        return new ExpectedCondition<>() {
            @Override
            public File apply(WebDriver webDriver) {
                for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                    if (fileEntry.getName().contains(name)
                            && !fileEntry.getName().endsWith("download")
                            && fileEntry.canRead()) {
                        return fileEntry;
                    }
                }
                return null;
            }

            @Override
            public String toString() {
                return "file Name Contains Exist. Path : " + folder + ". name : " + name;
            }
        };
    }

    private static boolean isOnTop(WebElement element) {
        return (boolean) ((JavascriptExecutor) getDriver()).executeScript(
                "var elm = arguments[0];" +
                        "var doc = elm.ownerDocument || document;" +
                        "var rect = elm.getBoundingClientRect();" +
                        "return elm === doc.elementFromPoint(rect.left + (rect.width / 2), rect.top + (rect.height / 2));"
                , element);
    }

    public static ExpectedCondition<List<WebElement>> numberOfElementsToBeMoreThan(List<WebElement> elements, int number) {
        return new ExpectedCondition<>() {
            @Override
            public List<WebElement> apply(WebDriver webDriver) {
                try {
                    int currentNumber = elements.size();
                    return currentNumber > number ? elements : null;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "number Of Elements To Be More Than " + number + ". Elements : " + elements;
            }
        };
    }

    public static ExpectedCondition<WebElement> visibilityOf(WebElement element) {
        return new ExpectedCondition<>() {
            public WebElement apply(WebDriver driver) {
                try {
                    return element.isDisplayed() ? element : null;
                } catch (Exception e) {
                    return null;
                }
            }

            public String toString() {
                return "visibility of " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> elementEnabled(WebElement element) {
        return new ExpectedCondition<>() {
            public WebElement apply(WebDriver driver) {
                try {
                    return element.isEnabled() ? element : null;
                } catch (Exception e) {
                    return null;
                }
            }

            public String toString() {
                return "enable status of " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> intractableTypeInElement(WebElement element) {
        return new ExpectedCondition<>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    element.sendKeys("");
                    return element;
                } catch (ElementNotInteractableException e) {
                    return null;
                } catch (InvalidArgumentException e) {
                    return element;
                }
            }

            @Override
            public String toString() {
                return "intractable Of Element " + element;
            }
        };

    }

    public static ExpectedCondition<InputStream> fileExistInRemote(URL url) {
        return new ExpectedCondition<>() {
            @NotNull
            @Override
            public InputStream apply(@NotNull WebDriver driver) {
                try {
                    return url.openStream();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "File is not exist in path : " + url.getPath();
            }
        };
    }

    public static ExpectedCondition<Boolean> elementAttributeValueDisappear(WebElement element, String attr, String value) {
        return new ExpectedCondition<>() {
            @NotNull
            @Override
            public Boolean apply(@NotNull WebDriver webDriver) {
                WaitHelper.getWait().sleep(500);
                try {
                    if (!element.getAttribute(attr).contains(value)) {
                        return true;
                    }
                } catch (NoSuchElementException e) {
                    return true;
                }
                return null;
            }

            @Override
            public String toString() {
                return "File is not exist in path :  ";
            }
        };
    }

    public static ExpectedCondition<Boolean> scrollUntilElementAttributeAppear(List<WebElement> elements, WebElement scroll, String attribute, String value) {
        return new ExpectedCondition<>() {
            @NotNull
            @Override
            public Boolean apply(@NotNull WebDriver webDriver) {
                if (elements.stream()
                        .anyMatch(e -> e.getAttribute(attribute).contains(value))) {
                    return true;
                } else {
                    JavascriptExecutor js = (JavascriptExecutor) getDriver();
                    js.executeScript("arguments[0].scrollTop += 200", scroll);
                    return false;
                }
            }

            @Override
            public String toString() {
                return elements.toString() + " " + attribute + " attribute was not found.";
            }
        };
    }

    public static ExpectedCondition<String> fileExistInRemoteByPartialName(URL url, String name) {
        return new ExpectedCondition<>() {
            @Override
            public String apply(WebDriver driver) {
                try {
                    HttpResponse<String> stringHttpResponse = Unirest.get(url.toString()).asString();
                    return Arrays.stream(stringHttpResponse.getBody()
                                    .split("\""))
                            .filter(a -> a.contains(name))
                            .findAny()
                            .orElseThrow();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "File is not exist in path : " + url.getPath();
            }
        };
    }

    public static ExpectedCondition<Boolean> progressIsOn(WebElement progress, String value, double maxProgress) {
        return webDriver -> {
            try {
                double currentProgress = Double.parseDouble(progress.getAttribute(value));
                return !(currentProgress < maxProgress);
            } catch (WebDriverException ex) {
                return null;
            }
        };
    }




    public static ExpectedCondition<Boolean> appleScriptToBeLoaded() {
        return input -> {
            Object o = ((JavascriptExecutor) getDriver()).executeScript("return window.AppleID");
            WaitHelper.getWait().sleep(1000);
            return o != null;
        };
    }

    public static ExpectedCondition<Boolean> cssValueChangeTo(WebElement element, String cssStyle, String cssValue) {
        return new ExpectedCondition<>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return element.getCssValue(cssStyle).contains(cssValue);
            }

            @Override
            public String toString() {
                return cssStyle + " css Value To Change to : " + cssValue + " element : " + element;
            }
        };
    }
}