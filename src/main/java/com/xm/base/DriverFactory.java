package com.xm.base;


import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

import static com.xm.config.Configuration.*;

@Log4j2
public class DriverFactory {

    private DesiredCapabilities capabilities;

    private DriverFactory() {
    }

    public static DriverFactory getInstance() {
        return new DriverFactory();
    }


    public WebDriver getDriverObject() {
        return instantiateWebDriver();
    }

    private WebDriver instantiateWebDriver() {
        WebDriver driver;
        capabilities = new DesiredCapabilities();
        if (IS_SELENIUM_REMOTE_DRIVER) {
            driver = initRemoteDriver();
        } else {
            driver = initLocalDriver();
        }
        return driver;
    }


    private WebDriver initLocalDriver() {
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
        if (IS_SELENIUM_BROWSER_HEADLESS)
            options.addArguments("--headless=new");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-web-security");
        return new ChromeDriver(options);
    }

    @SneakyThrows
    private RemoteWebDriver initRemoteDriver() {
        capabilities.setBrowserName(SELENIUM_BROWSER);
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false,
                "sessionTimeout", "9m"));

        ChromeOptions chromeOptions = new ChromeOptions();
        HashMap<String, Object> chromePreferences = new HashMap<>();

        chromeOptions.setExperimentalOption("w3c", true);
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setExperimentalOption("prefs", chromePreferences);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new RemoteWebDriver(capabilities);
    }


}