package com.xm.pages;

import com.xm.base.Resolution;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.xm.config.Configuration.RESOLUTION;
import static com.xm.utils.ActionUtils.*;

public class TopNavigationBar extends ComponentBase<TopNavigationBar> {
    @FindBy(css = "[class*='main_nav_research']")
    private WebElement researchMainButton;

    @FindBy(css = "[href='#researchMenu']")
    private WebElement menuItem;


    @FindBy(css = "[class='fa fa-bars']")
    private WebElement menuBar;


    @FindBy(css = "[class*='menu-research']")
    private List<WebElement> researchSubButtons;

    @FindBy(css = "[div[id='researchMenu'] li]")
    private List<WebElement> researchSubButtonsMobileView;

    @FindBy(css = "[class*='menu-tutorials']")
    private List<WebElement> tutorialsSubButtons;

    @FindBy(css = "[class*='toggleLeftNav']")
    private WebElement leftMenu;

    @FindBy(css = "[class*='fa fa-calendar']")
    private WebElement economicCalendar;

    @FindBy(css = "[class*='fa fa-pie-chart']")
    private WebElement economicSessionItem;

    @Override
    public TopNavigationBar open() {
        return null;
    }

    @Override
    public TopNavigationBar init() {
        return initPage();
    }

    public TopNavigationBar clickResearchMainButton() {
        click(RESOLUTION.equals(Resolution.RESOLUTION_800x600) ? menuBar : researchMainButton);
        return this;
    }

    public EconomicCalendarPage clickEconomicCalendarHyperLink() {
        if (RESOLUTION.equals(Resolution.RESOLUTION_800x600)) {
            economicCalendarStep();
        } else {
            scrollToElement(researchSubButtons.get(2));
            clickByText(researchSubButtons, "Economic Calendar");
        }
        return new EconomicCalendarPage().init();
    }

    public void economicCalendarStep() {
        scrollToElement(menuItem);
        click(menuItem);
        scrollToElement(economicCalendar);
        click(economicCalendar);
    }

    public EducationalVideosPage clickEducationalVideosHyperLink() {
        clickByText(tutorialsSubButtons, "Educational Videos");
        return new EducationalVideosPage().init();
    }
}
