package com.xm.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Objects;

import static com.xm.base.DriverBase.getDriver;
import static com.xm.utils.WaitHelper.getWait;
import static org.testng.internal.Utils.log;

public class ActionUtils {

    private ActionUtils() {
    }

    public static String getText(WebElement element) {
        getWait().waitElementToBeVisible(element);
        String text = element.getText();
        log("Getting text of " + text + " element");
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
        log("Clicking on " + element.getText() + " element");
        getWait().waitElementToBeClickable(element);
        element.click();
    }

    public static void clickByText(List<WebElement> elements, String text) {
        log("Clicking on " + text + " element");
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

    public static void resizeScreen(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }
}
