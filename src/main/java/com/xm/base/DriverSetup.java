package com.xm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public interface DriverSetup<T extends WebDriver> {

    DriverSetup  createSetupObject(URL appiumServerLocation, DesiredCapabilities capabilities);

    T createDriver();

}
