package com.xm.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.xm.config.Configuration.SELENIUM_RETRY;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (retryCount < SELENIUM_RETRY) {
                retryCount++;
                result.setStatus(ITestResult.SKIP);
                return true;
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}