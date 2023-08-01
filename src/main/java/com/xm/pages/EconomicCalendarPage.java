package com.xm.pages;

import com.xm.base.Resolution;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.xm.config.Configuration.RESOLUTION;
import static com.xm.utils.ActionUtils.*;

public class EconomicCalendarPage extends PageBase<EconomicCalendarPage> {

    @FindBy(css = "[class*='page-content'] h2")
    private WebElement pageTitle;

    @FindBy(css = "[class*='mat-calendar-body-cell']")
    private List<WebElement> calendarDays;

    @FindBy(css = "[class*='mat-slider-thumb'] :nth-child(2)")
    private WebElement calendarSliderThumb;

    @FindBy(css = "[class*='mat-slider-thumb-container']")
    private WebElement calendarSlider;

    @FindBy(css = "[class*='tc-finalval-tmz'] [class*='ng-star-inserted']")
    private WebElement calendarSliderText;

    @FindBy(css = " [class*='mat-calendar-body-cell mat-calendar-body-active']")
    private WebElement selectedStartDate;

    @FindBy(css = "[class*=' mat-calendar-body-range-end mat-calendar-body-in-range']")
    private WebElement selectedEndDate;

    @FindBy(css = "[class*='mat-calendar-body-label ng-star-inserted']")
    private WebElement calendarView;

    @FindBy(css = "[aria-label='Show time filter']")
    private WebElement calendarFilter;

    @Override
    public EconomicCalendarPage open() {
        return openPage();
    }

    @Override
    public EconomicCalendarPage init() {
        return initPage();
    }

    @Override
    public String getUrl() {
        return "/research/economicCalendar";
    }

    @Override
    public boolean isOpened() {
        return super.isOpened() && isElementDisplayed(pageTitle);
    }

    public EconomicCalendarPage switchToiFrameResizer() {
        switchToFrame("iFrameResizer0");
        return this;
    }

    public EconomicCalendarPage switchToParentFramePage() {
        switchToParentFrame();
        return this;
    }

    public void clickCalendarFilter() {
        if (RESOLUTION.equals(Resolution.RESOLUTION_800x600)) {
            if (!isElementDisplayed(calendarSliderText))
                click(calendarFilter);
            switchToActiveElement(Keys.ARROW_DOWN);
        }
    }

    public void dragCalendarThumb(DateOptions dateOption) {
        switchToActiveElement(Keys.ARROW_DOWN);
        switchToActiveElement(Keys.ARROW_DOWN);
        clickCalendarFilter();
        while (!getText(calendarSliderText).equals(dateOption.getDateOption())) {
            moveSliderThumb(calendarSlider, calendarSliderThumb);
        }
    }

    public String getStartDate() {
        return getAttribute(selectedStartDate, "aria-label");
    }

    public String getEndDate() {
        return getAttribute(selectedEndDate, "aria-label");
    }

    public enum DateOptions {
        RECENT_AND_NEXT("Recent & Next"),
        TODAY("Today"),
        TOMORROW("Tomorrow"),
        THIS_WEEK("This Week"),
        NEXT_WEEK("Next Week");

        private final String dateOption;

        public String getDateOption() {
            return dateOption;
        }

        DateOptions(String dateOption) {
            this.dateOption = dateOption;
        }
    }
}
