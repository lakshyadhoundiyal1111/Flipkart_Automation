package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.name("q");
    By productContainers = By.xpath("//a[contains(@href,'/p/')]");
    By searchHeading = By.xpath("//span[contains(text(),'results for')]");

    public void searchProduct(String product) {

        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBox).sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("search?q="));
    }

    public int getProductCount() {
        return driver.findElements(productContainers).size();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
    
    public boolean isSearchHeadingDisplayed(String keyword) {

        String headingText = driver.findElement(searchHeading).getText();
        System.out.println("Search Heading: " + headingText);

        return headingText.toLowerCase().contains(keyword.toLowerCase());
    }

}
