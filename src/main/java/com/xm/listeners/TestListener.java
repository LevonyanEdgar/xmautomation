package com.xm.listeners;


import com.xm.utils.ReportUtils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.testng.*;

import java.net.URL;
import java.net.URLConnection;

@Log4j2
public class TestListener implements ITestListener, IHookable {


    public static boolean isInternetConnected() {
        try {
            URL url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("-----------------------------------------------------------------------");
        log.info("Starting to run " + iTestResult.getMethod().getQualifiedName() + " test method");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("-----------------------------------------------------------------------");
        log.info("PASSED " + iTestResult.getMethod().getQualifiedName() + " test method");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("Checking if Internet connected: " + isInternetConnected());
        log.info("-----------------------------------------------------------------------");
        log.info("FAILED " + iTestResult.getMethod().getQualifiedName() + " test method");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("Checking if Internet connected: " + isInternetConnected());
        log.info("-----------------------------------------------------------------------");
        log.info("SKIPPED " + iTestResult.getMethod().getQualifiedName() + " test method");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //TODO log failed percentage
        log.info("Test Failed Within Success Percentage");
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Staring to run test suite with " + context.getAllTestMethods().length + " tests");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finished running test suite with " + context.getAllTestMethods().length + " tests");

    }


    @SneakyThrows
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        iHookCallBack.runTestMethod(iTestResult);
        if (iTestResult.getThrowable() != null) {
            ReportUtils.saveScreenshot(iTestResult.getMethod().getQualifiedName(), iTestResult.getThrowable());
        }
    }
}





