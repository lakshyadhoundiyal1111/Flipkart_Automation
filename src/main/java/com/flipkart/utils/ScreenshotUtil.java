package com.flipkart.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String scenarioName) {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination = new File("screenshots/" + scenarioName + ".png");

        try {
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved: " + destination.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
