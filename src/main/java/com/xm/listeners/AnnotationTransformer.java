package com.xm.listeners;


import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        retryTransformer(iTestAnnotation);
    }

    private void retryTransformer(ITestAnnotation iTestAnnotation) {
        iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}