package com.xm.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.xm.utils.ActionUtils.*;
import static com.xm.utils.WaitHelper.getWait;

public class EducationalVideosPage extends PageBase<EducationalVideosPage> {
    @FindBy(css = "[class*='container-fluid top-bar'] h1")
    private WebElement pageTitle;

    @FindBy(css = "[class*='xm-videos__playlistGroup']")
    private List<WebElement> panelCategories;

    @FindBy(css = "[class*='js-video-item']")
    private List<WebElement> videoItems;

    @FindBy(css = "[class*='player-big-play-button']")
    private WebElement playButton;

    @FindBy(css = "[class='player-video-holder']")
    private WebElement videoPlayerView;

    @FindBy(css = "[class='player-progress-time']")
    private WebElement playerProgressTime;

    @FindBy(css = "[class*='sproutvideo-player']")
    private WebElement videoPlayerFrame;

    @FindBy(css = "[class*='player-play-pause player-button']")
    private WebElement playPauseButton;

    @Override
    public EducationalVideosPage open() {
        return openPage();
    }

    @Override
    public EducationalVideosPage init() {
        return initPage();
    }

    @Override
    public String getUrl() {
        return "/educational-videos";
    }

    @Override
    public boolean isOpened() {
        return super.isOpened() && isElementDisplayed(pageTitle);
    }

    private WebElement getVideoItemByName(String name) {
        getWait().waitListElementWithIndexToBe(videoItems, 0);
        return videoItems.stream().filter(element -> element.getText().contains(name)).findFirst().orElseThrow();
    }

    public EducationalVideosPage clickOnVideoCategoryByName(String name) {
        scrollToElement(panelCategories.get(3));
        clickByText(panelCategories,name);
        return this;
    }

    public EducationalVideosPage clickOnVideoItemByName(String name) {
        WebElement videoItem = getVideoItemByName(name);
        click(videoItem);
        return this;
    }

    public EducationalVideosPage clickVideoPlayButton() {
        getWait().waitElementToBeVisible(playButton);
        click(playButton);
        return this;
    }

    public EducationalVideosPage switchToiFrameVideoPlayer() {
        switchToFrame(videoPlayerFrame);
        return this;
    }

    public EducationalVideosPage hoverOverPlayer() {
        hover(videoPlayerView);
        return this;
    }

    public EducationalVideosPage clickPlayPauseButton() {
        click(playPauseButton);
        return this;
    }

    @SneakyThrows
    public EducationalVideosPage pauseVideoAfterSeconds(int seconds) {
        switchToActiveElement(Keys.PAGE_DOWN);
        Thread.sleep(seconds * 1000);
        hoverOverPlayer();
        clickPlayPauseButton();
        return this;
    }

    public String getPlayerProgressTime() {
        hoverOverPlayer();
        return getText(playerProgressTime);
    }
}
