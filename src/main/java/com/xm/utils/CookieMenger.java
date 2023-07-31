package com.xm.utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Date;
import java.util.Map;

import static com.xm.base.DriverBase.getDriver;

@Log4j2
public class CookieMenger {

    public static void clearCookies() {
        try {
            getDriver().manage().deleteAllCookies();
        } catch (Exception e) {
            log.error("Unable to clear cookies, driver object is not viable...", e.getCause());
        }
    }

    public static void setCookie(String key, String value) {
        try {
            getDriver().manage().addCookie(new Cookie(key, value));
        } catch (Exception e) {
            log.error("Unable to set cookies, driver object is not viable...", e.getCause());
        }
    }

    public static void setItemLocalStorage(String key, String value) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("localStorage.setItem(arguments[0],arguments[1])", key, value);
        } catch (Exception e) {
            log.error("Unable to set local storage item, driver object is not viable...", e.getCause());
        }
    }

    public static void setCookie(String key, String value, String domain, String path, Date expiry) {
        try {
            getDriver().manage().addCookie(new Cookie(key, value, domain, path, expiry));
        } catch (Exception e) {
            log.error("Unable to set cookies, driver object is not viable...", e.getCause());
        }
    }

    public static void setCookie(Cookie cookie) {
        try {
            getDriver().manage().addCookie(cookie);
        } catch (Exception e) {
            log.error("Unable to set cookies, driver object is not viable...", e.getCause());
        }
    }

    public static void deleteCookieByName(String name) {
        try {
            getDriver().manage().deleteCookieNamed(name);
        } catch (Exception e) {
            log.error("Unable to set cookies, driver object is not viable...", e.getCause());
        }
    }




}
