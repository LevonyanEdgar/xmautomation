package com.xm.base;

import com.xm.config.Configuration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import static com.xm.config.Configuration.RESOLUTION;

@Log4j2
public abstract class DriverBase {

    private static ThreadLocal<WebDriver> driverFactoryThread = new ThreadLocal<>();

    public static void instantiateDriverObject() {
        driverFactoryThread.set(DriverFactory.getInstance().getDriverObject());
        setResolution();
    }


    private static void setResolution() {
        switch (RESOLUTION) {
            case MAXIMUM:
                getDriver().manage().window().setSize(new Dimension(1366, 768));
                break;
            case RESOLUTION_800x600:
                getDriver().manage().window().setSize(new Dimension(800, 600));
                break;
            case RESOLUTION_1024x768:
                getDriver().manage().window().setSize(new Dimension(1024, 768));
                break;
        }
    }

    public static WebDriver getDriver() {
        return driverFactoryThread.get();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}