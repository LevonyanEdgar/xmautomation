package com.xm.base;

import com.xm.config.Configuration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;

@Log4j2
public class DriverBase {

    private static ThreadLocal<WebDriver> driverFactoryThread = new ThreadLocal<>();

    public static void instantiateDriverObject(Method method) {
        driverFactoryThread.set(DriverFactory.getInstance().getDriverObject(method));
    }

    private DriverBase() {
    }

    public static WebDriver getDriver() {
        return driverFactoryThread.get();
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getDriver()).getSessionId().toString();
    }

    public static void initDomain() {
        getDriver().get(Configuration.SELENIUM_URL);
    }


    public static void closeDriverObjects() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
            }
            if (driverFactoryThread != null) {
                driverFactoryThread.remove();
            }
        } catch (Exception ignore) {

            //ignore
        }
    }
}