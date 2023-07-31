package com.xm.pages;

import com.xm.utils.ActionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase<HomePage> {

    @FindBy(css="[id='hero-content']")
    private WebElement heroContent;

    @Override
    public HomePage open() {
        return openPage();
    }

    @Override
    public HomePage init() {
        return initPage();
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public boolean isOpened() {
        return super.isOpened() && isElementDisplayed(heroContent);
    }

}
