package com.xm.utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

import static com.xm.base.DriverBase.getDriver;

@Log4j2
public class XMExpectedConditions {

    private XMExpectedConditions() {
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

    public static ExpectedCondition<Boolean> executeScript(String script, WebElement... params) {
        return webDriver -> (Boolean) ((JavascriptExecutor) getDriver()).executeScript(script, params);
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

}