package com.xm;

import com.xm.listeners.AnnotationTransformer;
import com.xm.listeners.TestListener;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

import static com.xm.base.DriverBase.*;
import static com.xm.utils.CookieMenger.setCookie;

 @Listeners({TestListener.class, AnnotationTransformer.class})
 public class TestBase {
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {

        instantiateDriverObject(method);
        initDomain();
        setRequiredCookies();
    }


    @AfterMethod(alwaysRun = true)
    public synchronized void tearDownMethod() {
        closeDriverObjects();
    }

    protected void setRequiredCookies() {
        //disable popup
        Cookie cookie = new Cookie("xmck_popupShown", "1");
        setCookie(cookie);
    }

}
