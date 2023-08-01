package com.xm;

import com.xm.pages.EconomicCalendarPage;
import com.xm.pages.EducationalVideosPage;
import com.xm.pages.HomePage;
import com.xm.pages.TopNavigationBar;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.xm.utils.TestUtils.*;

public class EducationalVideoTest extends TestBase {

    @Test
    public void verifyEducationalVideoOpenTest() {
        SoftAssert softAssert = new SoftAssert();
        TopNavigationBar topNavigationBar = new TopNavigationBar();
        new HomePage().open();
        EconomicCalendarPage economicCalendarPage = topNavigationBar.init().clickResearchMainButton()
                .clickEconomicCalendarHyperLink()
                .switchToiFrameResizer();
        economicCalendarPage.dragCalendarThumb(EconomicCalendarPage.DateOptions.TODAY);
        softAssert.assertEquals(economicCalendarPage.getStartDate(), getCurrentDateTimeNow(), "Selected wrong day from calendar in case[TODAY]");
        economicCalendarPage.dragCalendarThumb(EconomicCalendarPage.DateOptions.TOMORROW);
        softAssert.assertEquals(economicCalendarPage.getStartDate(), getDateTimeAfterDays(1), "Selected wrong day from calendar in case[TOMORROW]");
        economicCalendarPage.dragCalendarThumb(EconomicCalendarPage.DateOptions.NEXT_WEEK);
        softAssert.assertEquals(economicCalendarPage.getStartDate(), getDateTimeAfterWeeks(1), "Selected wrong day from calendar in case[NEXT_WEEK]");
        softAssert.assertEquals(economicCalendarPage.getEndDate(), getEndDateTimeOfWeekAfterWeeks(2), "Selected wrong day from calendar in case[NEXT_WEEK]");
        economicCalendarPage.switchToParentFramePage();
        EducationalVideosPage educationalVideosPage = topNavigationBar.clickResearchMainButton().clickEducationalVideosHyperLink();
        String progress = educationalVideosPage.clickOnVideoCategoryByName("Intro to the Markets")
                 .clickOnVideoItemByName("Lesson 1.1")
                .switchToiFrameVideoPlayer()
                .clickVideoPlayButton()
                .pauseVideoAfterSeconds(5)
                .getPlayerProgressTime();
        softAssert.assertTrue(Integer.parseInt(progress.substring(3, 5)) > 0, "The video doesn't play");
        softAssert.assertAll();
    }
}
