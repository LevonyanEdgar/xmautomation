package com.xm.base;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;

@Log4j2
public class DriverFactory {

    private DesiredCapabilities capabilities;

    private DriverFactory() {
    }

    public static DriverFactory getInstance() {
        return new DriverFactory();
    }


    public WebDriver getDriverObject(Method method) {
        return instantiateWebDriver(method);
    }

    private WebDriver instantiateWebDriver(Method method) {
        WebDriver driver;
        capabilities = new DesiredCapabilities();
//        if (IS_SELENIUM_REMOTE_DRIVER) {
//            driver = initRemoteDriver(method);
//        } else {
        driver = initLocalDriver(method);
//        }
//        driver.manage().window().setSize(new Dimension(1366, 768));
        return driver;
    }

    private WebDriver initLocalDriver(Method method) {
        HashMap<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.password_manager_enabled", false);
//        chromePreferences.put("profile.default_content_setting_values.automatic_downloads", 1);
//        chromePreferences.put("download.default_directory", System.getProperty("user.dir") + "/target/downloads");
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
//        if (IS_SELENIUM_BROWSER_HEADLESS)
//            options.addArguments("--headless=new");
        options.addArguments("--no-default-browser-check");
//        options.addArguments("--disable-web-security");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        options.setExperimentalOption("prefs", chromePreferences);
        return new ChromeDriver(options);
    }
//
//    @SneakyThrows
//    private RemoteWebDriver initRemoteDriver(Method method) {
//        capabilities.setBrowserName(SELENIUM_BROWSER);
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "name", method.getName(),
//                "enableVNC", true,
//                "enableVideo", false,
//                "sessionTimeout", "9m"));
//
//        switch (SELENIUM_BROWSER) {
//            case "chrome":
//                ChromeOptions chromeOptions = new ChromeOptions();
//                HashMap<String, Object> chromePreferences = new HashMap<>();
//
//                chromeOptions.setExperimentalOption("w3c", true);
//                chromeOptions.addArguments("--no-sandbox");
//                chromeOptions.addArguments("--disable-web-security");
//                chromeOptions.addArguments("--disable-dev-shm-usage");
//                chromePreferences.put("profile.content_settings.exceptions.clipboard", getClipBoardSettingsMap(1));
//                chromePreferences.put("download.default_directory", DOWNLOAD_DIRECTORY);
//                chromeOptions.setExperimentalOption("prefs", chromePreferences);
//                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//                break;
//            case "firefox":
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.addArguments("--no-sandbox");
//                firefoxOptions.setExperimentalOption("w3c", true);
//                firefoxOptions.addArguments("--disable-web-security");
//                firefoxOptions.addArguments("--disable-dev-shm-usage");
//                firefoxOptions.addPreference("browser.download.dir", DOWNLOAD_DIRECTORY);
//                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
//                break;
//            case "MicrosoftEdge":
//                EdgeOptions edgeOptions = new EdgeOptions();
//                edgeOptions.addArguments("--no-sandbox");
//                edgeOptions.addArguments("--disable-web-security");
//                edgeOptions.addArguments("--disable-dev-shm-usage");
//                capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
//                break;
//        }
//        RemoteWebDriver remoteWebDriver = WaitHelper.getDriverWait()
//                .driverToBeInitialised(URI.create(SELENIUM_GRID_URL).toURL(), capabilities);
//        remoteWebDriver.setFileDetector(new LocalFileDetector());
//        return remoteWebDriver;
//    }
//

}