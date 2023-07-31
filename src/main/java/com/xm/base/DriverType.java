//package com.xm.base;
//
//import org.openqa.selenium.WebDriver.Options;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.safari.SafariOptions;
//
//import java.net.URL;
//
//public enum DriverType implements DriverSetup {
//
//
//    Chrome {
//        @Override
//        public DriverSetup createSetupObject(URL appiumServerLocation, DesiredCapabilities capabilities) {
//            ChromeOptions options = new ChromeOptions();
//            options.merge(capabilities);
//            if (IS_SELENIUM_BROWSER_HEADLESS)
//                options.addArguments("--headless=new");
//            options.addArguments("--no-default-browser-check");
//            options.addArguments("--disable-web-security");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-dev-shm-usage");
//            capabilities.merge()
//
//            return (DriverSetup) options;
//        }
//
//        @Override
//        public ChromeDriver createDriver() {
//            return new ChromeDriver(options);
//            ;
//        }
//    },
//    Firefox {
//        @Override
//        public DriverSetup createSetupObject(URL appiumServerLocation, DesiredCapabilities capabilities) {
//            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            return null;
//        }
//
//        @Override
//        public FirefoxDriver createDriver() {
//            return null;
//        }
//    },
//    Safari {
//        @Override
//        public DriverSetup createSetupObject(URL appiumServerLocation, DesiredCapabilities capabilities) {
//            SafariOptions options = new SafariOptions();
//            return null;
//        }
//
//        @Override
//        public SafariDriver createDriver() {
//            return null;
//        }
//    };
//
//
//    URL serverLocation;
//}
