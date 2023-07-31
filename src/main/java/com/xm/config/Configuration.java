package com.xm.config;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

@Log4j2
public class Configuration {

    public static final String SELENIUM_URL;
    public static final String SELENIUM_BROWSER;
    public static final int SELENIUM_RETRY;
    public static final boolean IS_SELENIUM_BROWSER_HEADLESS;
    private static final Properties configs;


    // Read property file only once
    static {
        configs = new Properties();
        final var loader = Thread.currentThread().getContextClassLoader();
        try (InputStream file = loader.getResourceAsStream("config.properties")) {
            configs.load(file);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        SELENIUM_URL = getProperty("selenium.url").replaceAll("/$", "");
        SELENIUM_BROWSER = getProperty("selenium.browser", "chrome");
        SELENIUM_RETRY = parseInt(getProperty("selenium.retry.count"));
        IS_SELENIUM_BROWSER_HEADLESS = parseBoolean(getProperty("selenium.browser.headless"));

        log.info(" ");
        log.info("Selected Browser: " + SELENIUM_BROWSER);
        log.info(" ");
    }

    private Configuration() {
    }

    /**
     * Trying to get property from the System by key
     * If in System there is no, getting from config.property file
     *
     * @param key of property
     * @return value of the property
     */
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

    /**
     * Trying to get property from the System by key
     * If in System there is no, getting from config.property file
     * otherwise returns default value
     *
     * @param key          of property
     * @param defaultValue is the value which will return if the property is empty
     * @return value of the property
     */
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