package com.xm.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.xm.base.DriverBase.getDriver;

@Log4j2
public class ReportUtils {

    private ReportUtils() {
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    @SneakyThrows
    public static byte[] saveScreenshot(String methodName, Throwable throwable) {
        log.info("Taking screenshot on failure");
        try {
            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            BufferedImage image = ImageIO.read(screenshot);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            return outputStream.toByteArray();
        } catch (RuntimeException ignore) {
            return new byte[0];
        }
    }

    @SneakyThrows
    public static void saveScreenshotByPath(String path, BufferedImage expected, BufferedImage actual) {
        Path content = Paths.get(path);
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        ImageIO.write(expected, "png", byteArrayOutputStream1);
        InputStream expectedStream = new ByteArrayInputStream(byteArrayOutputStream1.toByteArray());

        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ImageIO.write(actual, "png", byteArrayOutputStream2);
        InputStream actualStream = new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());

        InputStream is = Files.newInputStream(content);
        Allure.addAttachment("Screenshot diff", is);
        Allure.addAttachment("Expected image", expectedStream);
        Allure.addAttachment("Actual image", actualStream);
    }

    public static void addMessageAttachment(String name, String message) {
        Allure.addAttachment(name, message);
    }

    public static void addLinkAttachment(String url) {
        Allure.link(url);
    }

    @Step("{message}")
    public static void addStep(String message) {
        log.info(message);
        //do nothing
    }
}