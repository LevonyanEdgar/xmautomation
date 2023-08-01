package com.xm.utils;

import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.openqa.selenium.TimeoutException;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.xm.base.DriverBase.getDriver;

public class TestUtils {


    @SneakyThrows
    public static boolean urlEquals(String url1, String url2) {
        // Compare Query String Parameters
        Map<String, String> mapParams1 = getParams(URLEncodedUtils.parse(new URI(url1), "UTF-8"));
        Map<String, String> mapParams2 = getParams(URLEncodedUtils.parse(new URI(url2), "UTF-8"));
        return Objects.equals(getEndpoint(url1), getEndpoint(url2)) && mapParams1.equals(mapParams2);
    }

    public static boolean urlContainsIgnoreQuery(String url1, String url2) {
        return Objects.equals(getEndpoint(url1), getEndpoint(url2));
    }

    private static Map<String, String> getParams(List<NameValuePair> list) {
        Map<String, String> result = new HashMap<>();
        for (NameValuePair param : list) {
            if (!result.containsKey(param.getName()))
                result.put(param.getName(), param.getValue());
        }
        return result;
    }

    private static String getEndpoint(String url) {
        String s = url.split("\\?")[0];
        if (!s.endsWith("/")) {
            return s + "/";
        }
        return s;
    }

    public static String getCurrentDateTimeNow() {
        LocalDateTime datetime1 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        return datetime1.format(format);
    }

    public static String getDateTimeAfterDays(int days) {
        LocalDateTime datetime1 = LocalDateTime.now().plusDays(days);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        return datetime1.format(format);
    }


    public static String getDateTimeAfterWeeks(int weeks) {
        int minusDays = 0;
        switch (LocalDateTime.now().getDayOfWeek()) {
            case MONDAY:
                minusDays = 0;
                break;
            case TUESDAY:
                minusDays = 1;
                break;
            case WEDNESDAY:
                minusDays = 2;
                break;
            case THURSDAY:
                minusDays = 3;
                break;
            case FRIDAY:
                minusDays = 4;
                break;
            case SATURDAY:
                minusDays = 5;
                break;
            case SUNDAY:
                minusDays = 6;
                break;
        }
        LocalDateTime datetime1 = LocalDateTime.now().minusDays(minusDays).plusWeeks(weeks);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        return datetime1.format(format);
    }


    public static String getEndDateTimeOfWeekAfterWeeks(int weeks) {
        int minusDays = 0;
        switch (LocalDateTime.now().getDayOfWeek()) {
            case MONDAY:
                minusDays = 0;
                break;
            case TUESDAY:
                minusDays = 2;
                break;
            case WEDNESDAY:
                minusDays = 3;
                break;
            case THURSDAY:
                minusDays = 4;
                break;
            case FRIDAY:
                minusDays = 5;
                break;
            case SATURDAY:
                minusDays = 6;
                break;
            case SUNDAY:
                minusDays = 7;
                break;
        }
        LocalDateTime datetime1 = LocalDateTime.now().minusDays(minusDays).plusWeeks(weeks);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        return datetime1.format(format);
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void waitForUrl(String newUrl) {
        try {
            WaitHelper.getWait().waitForUrlChangedTo(newUrl);
        } catch (TimeoutException e) {
            throw new AssertionError("url is not opened, expected " + newUrl + "but found " + getCurrentUrl());
        }
    }
}
