package com.flipkart.stepdefinitions;

import com.flipkart.base.DriverFactory;
import com.flipkart.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.WebDriver;

public class Hooks {

    WebDriver driver;

    @Before
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.get("https://www.flipkart.com");
    }

    @After
    public void tearDown() {
        DriverFactory.getDriver().quit();
    }
    
    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            ScreenshotUtil.captureScreenshot(driver, scenario.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }

}
