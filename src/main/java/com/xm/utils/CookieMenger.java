package com.xm.utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Cookie;

import java.util.Date;

import static com.xm.base.DriverBase.getDriver;

@Log4j2
public abstract class CookieMenger {

    public static void setCookie(String key, String value) {
        try {
            getDriver().manage().addCookie(new Cookie(key, value));
        } catch (Exception e) {
            log.error("Unable to set cookies, driver object is not viable...", e.getCause());
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
