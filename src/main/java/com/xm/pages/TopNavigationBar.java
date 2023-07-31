package com.xm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.xm.utils.ActionUtils.click;
import static com.xm.utils.ActionUtils.clickByText;

public class TopNavigationBar extends ComponentBase<TopNavigationBar> {
    @FindBy(css="[class*='main_nav_research']")
    private WebElement researchMainButton;

    @FindBy(css = "[class*='menu-research']")
    private List<WebElement> researchSubButtons;

    @FindBy(css = "[div[id='researchMenu'] li]")
    private List<WebElement> researchSubButtonsMobileView;

    @FindBy(css = "[class*='menu-tutorials']")
    private List<WebElement> tutorialsSubButtons;

    @FindBy(css = "[class*='toggleLeftNav']")
    private WebElement leftMenu;

    @Override
    public TopNavigationBar open() {
        return null;
    }

    @Override
    public TopNavigationBar init() {
        return initPage();
    }

    public TopNavigationBar clickResearchMainButton() {
        click(researchMainButton);
        return this;
    }

    public EconomicCalendarPage clickEconomicCalendarHyperLink() {
        clickByText(researchSubButtons,"Economic Calendar");
        return new EconomicCalendarPage().init();
    }

    public EconomicCalendarPage clickEconomicCalendarHyperLinkMobile() {
        clickByText(researchSubButtonsMobileView,"Economic Calendar");
        return new EconomicCalendarPage().init();
    }

    public EconomicCalendarPage clickEducationalVideosHyperLinkMobile() {
        clickByText(researchSubButtonsMobileView,"Educational Videos");
        return new EconomicCalendarPage().init();
    }

    public EducationalVideosPage clickEducationalVideosHyperLink() {
        clickByText(tutorialsSubButtons,"Educational Videos");
        return  new EducationalVideosPage().init();
    }

    public TopNavigationBar clickLeftMenu(){
        click(leftMenu);
        return this;
    }
}
