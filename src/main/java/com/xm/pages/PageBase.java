package com.xm.pages;

import com.xm.utils.TestUtils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.xm.base.DriverBase.getDriver;
import static com.xm.config.Configuration.SELENIUM_URL;
import static com.xm.utils.ReportUtils.addStep;
import static com.xm.utils.WaitHelper.getWait;

@Log4j2
public abstract class PageBase<T extends LoadableComponent<T>> extends LoadableComponent<T> {

    protected WebDriverWait wait;

    protected PageBase() {
        this.wait = (new WebDriverWait(getDriver(), Duration.ofSeconds(50)));
    }

    public abstract T open();

    public abstract T init();

    /**
     * Need to be overridden for all pages. This function is comparing url
     * in override function we need to call super and
     *
     * @return return any component visibility in page
     */
    @SneakyThrows
    public boolean isOpened() {
        return isOpened(true);
    }

    @SneakyThrows
    public boolean isOpened(boolean compareQueryPart) {
        initElements();
        getWait().waitForPageLoaded();
        getWait().waitForPageReady();
        String finalUrl = getUrl() != null ? SELENIUM_URL + getUrl() : getUrlWithSubdomain();
        if (compareQueryPart)
            return TestUtils.urlEquals(getCurrentUrl(), finalUrl);
        return TestUtils.urlContainsIgnoreQuery(getCurrentUrl(), finalUrl);
    }

    @Override
    @SneakyThrows
    protected void load() {
        if (getUrlWithSubdomain() == null)
            getDriver().get(SELENIUM_URL + getUrl());
        else
            getDriver().get(getUrlWithSubdomain());

    }

    protected T openPage() {
        PageFactory.initElements(getDriver(), this);
        addStep("Navigating to " + SELENIUM_URL + getUrl());
        load();
        return get();
    }

    protected T openPage(Boolean isWaitNeeded) {
        PageFactory.initElements(getDriver(), this);
        addStep("Navigating to " + SELENIUM_URL + getUrl());
        load();
        return isWaitNeeded ? get() : (T) this;
    }

    protected T initPage() {
        PageFactory.initElements(getDriver(), this);
        addStep("Initialising to " + getDriver().getCurrentUrl());
        return get();
    }

    public <T extends PageBase> T initElements() {
        PageFactory.initElements(getDriver(), this);
        return (T) this;
    }

    @Override
    protected void isLoaded() {
        getWait().waitForPageReady();
    }

    public abstract String getUrl();

    public String getUrlWithSubdomain() {
        return null;
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    protected void switchToParentFrame() {
        log.info("Switching on parent frame");
        getDriver().switchTo().parentFrame();
    }

    protected void switchToFrame(String frameElementName) {
        log.info("Switching on frame");
        getDriver().switchTo().frame(frameElementName);
    }

    protected void switchToFrame(WebElement frameElement) {
        log.info("Switching on frame");
        getDriver().switchTo().frame(frameElement);
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            getWait().waitElementToBeEnabled(element, 10);
            return isElementExist(element) && element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementExist(WebElement element) {
        try {
            log.info("Checking element " + element.getText() + "exist");
            getWait().waitElementToBeEnabled(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}