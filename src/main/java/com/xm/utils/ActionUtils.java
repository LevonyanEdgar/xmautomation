package com.xm.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Objects;

import static com.xm.base.DriverBase.getDriver;
import static com.xm.utils.ReportUtils.addStep;
import static com.xm.utils.WaitHelper.getWait;

public class ActionUtils {

    private ActionUtils() {
    }

    public static String getText(WebElement element) {
        getWait().waitElementToBeVisible(element);
        String text = element.getText();
        addStep(String.format("Getting text of %s element", text));
        return text;
    }

    public static void moveSliderThumb(WebElement slider, WebElement thumb) {
        Actions move = new Actions(getDriver());
        int width = getDimensionalSize(slider).getWidth();
        move.moveToElement(thumb, ((width * 10) / 100), 0).click();
        move.build().perform();
    }

    public static Dimension getDimensionalSize(WebElement element) {
        getWait().waitElementToBeVisible(element);
        return element.getSize();
    }

    public static String getAttribute(WebElement element, String attribute) {
        getWait().waitElementToBeEnabled(element);
        return element.getAttribute(attribute);
    }

    public static void click(WebElement element) {
        getWait().waitElementToBeEnabled(element);
        addStep("Clicking on " + element.getText() + " element");
        getWait().waitElementToBeClickable(element);
        element.click();
    }

    public static void clickByText(List<WebElement> elements, String text) {

        addStep("Clicking on " + text + " element");
        getWait().waitListElementWithIndexToBeVisible(elements);
        elements.stream()
                .filter(e -> Objects.equals(e.getText(), text))
                .findAny()
                .ifPresent(ActionUtils::click);
    }

    public static void hover(WebElement element) {
        getWait().waitElementToBeVisible(element);
        org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(getDriver());
        action.moveToElement(element).perform();
    }

    public static Boolean scrollToElement(WebElement element) {
        String scrollElement = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var top = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, top-(viewPortHeight/2));"
                + "return true;";
        try {
            return (Boolean) getWait().executeScript(scrollElement, element);
        } catch (Exception ignore) {
            return false;
        }
    }
    public static void switchToActiveElement(Keys key){
        getDriver().switchTo().activeElement().sendKeys(key);
    }
}
