package com.xm.listeners;


import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        if (isDataProviderTest(method)) return;

        iTestAnnotation.setTimeOut(540000);
        retryTransformer(iTestAnnotation);


    }

    private boolean isDataProviderTest(Method method) {
        return !method.getAnnotation(Test.class).dataProvider().equals("");
    }


    private void retryTransformer(ITestAnnotation iTestAnnotation) {
        iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    /**
     * Disabling test if it contains failing annotation
     */
//    private void skipIfIsFailing(ITestAnnotation iTestAnnotation, Method method) {
//
//        final Failing failingAnnotation = method.getAnnotation(Failing.class) == null ?
//                method.getDeclaringClass().getAnnotation(Failing.class) : method.getAnnotation(Failing.class);
//        if (failingAnnotation != null) {
//            ReportUtils.addLinkAttachment(failingAnnotation.issue());
//            ReportUtils.addStep(failingAnnotation.issue());
//            TmsLink tmsLinkAnnotation = method.getAnnotation(TmsLink.class);
//            TmsLinks tmsLinksAnnotation = method.getAnnotation(TmsLinks.class);
//
//
//            String testRunId = Configuration.TESTRAIL_RUN_ID;
//
//            if (tmsLinkAnnotation != null) {
//                TestRailUtils.addCaseResult(testRunId, tmsLinkAnnotation.value(), 5, failingAnnotation.issue(),
//                        "Test Result was updated with job: " + Configuration.JENKINS_BUILD_URL + "\nCase is failing because of bug: " + failingAnnotation.issue(), method.getName());
//            } else if (tmsLinksAnnotation != null) {
//                TmsLink[] tmsLinks = tmsLinksAnnotation.value();
//                for (TmsLink tmsLink : tmsLinks) {
//                    TestRailUtils.addCaseResult(testRunId, tmsLink.value(), 5, failingAnnotation.issue(),
//                            "Test Result was updated with job: " + Configuration.JENKINS_BUILD_URL + "\nCase is failing because of bug: " + failingAnnotation.issue(), method.getName());
//                }
//            }
//            iTestAnnotation.setEnabled(false);
//        }
//    }


}