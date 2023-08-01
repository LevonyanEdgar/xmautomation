package com.xm.config;

import com.xm.base.Resolution;
import io.netty.handler.codec.spdy.SpdyWindowUpdateFrame;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
@Log4j2
public abstract class Configuration {

    public static final String SELENIUM_URL;
    public static final String SELENIUM_BROWSER;
    public static final int SELENIUM_RETRY;
    public static final boolean IS_SELENIUM_BROWSER_HEADLESS;
    public static final Resolution RESOLUTION;
    public static final boolean IS_SELENIUM_REMOTE_DRIVER;
     public static final String SELENIOID_GRID_URL;
    private static final Properties configs;

    static {
        configs = new Properties();
        final var loader = Thread.currentThread().getContextClassLoader();
        try (InputStream file = loader.getResourceAsStream("config.properties")) {
            configs.load(file);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        SELENIOID_GRID_URL=getProperty("selenioid.grid.url");

        SELENIUM_URL = getProperty("selenium.url").replaceAll("/$", "");
        SELENIUM_BROWSER = getProperty("selenium.browser", "chrome");
        SELENIUM_RETRY = parseInt(getProperty("selenium.retry.count"));
        IS_SELENIUM_BROWSER_HEADLESS = parseBoolean(getProperty("selenium.browser.headless"));
        RESOLUTION = Resolution.valueOf(getProperty("browser.resolution"));
        IS_SELENIUM_REMOTE_DRIVER = parseBoolean(getProperty("is.remote"));

        log.info(" ");
        log.info("Selected Browser: " + SELENIUM_BROWSER);
        log.info(" ");
    }

    public static String getProperty(String key) {
        if (System.getProperty(key) == null || System.getProperty(key).isEmpty()) {
            String property = configs.getProperty(key);
            log.info("Getting property " + key + ": " + property);
            return property;
        } else {
            String property = System.getProperty(key);
            log.info("Getting property " + key + ": " + property);
            return property;
        }
    }

    public static String getProperty(String key, String defaultValue) {
        if (System.getProperty(key) == null || System.getProperty(key).isEmpty()) {
            if (configs.getProperty(key) == null || configs.getProperty(key).isEmpty()) {
                return defaultValue;
            } else {
                return configs.getProperty(key);
            }
        } else {
            return System.getProperty(key);
        }
    }

}