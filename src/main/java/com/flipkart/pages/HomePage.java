package com.flipkart.pages;

import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Login popup close button
    By closeLoginPopup = By.xpath("//button[contains(text(),'✕')]");

    public void closeLoginPopupIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup)).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(closeLoginPopup));
            System.out.println("Login popup closed.");
        } catch (Exception e) {
            System.out.println("Login popup not displayed.");
        }
    }


    public String getPageTitle() {
        return driver.getTitle();
    }
    
    By searchBox = By.name("q");
    
    public boolean isSearchBoxDisplayed() {
        return driver.findElement(searchBox).isDisplayed();
    }
    
    By categoryItems = By.xpath("//a[contains(@href,'/search') or contains(@href,'/')]"); 
    
    public int getCategoryCount() {
        int size = driver.findElements(categoryItems).size();
        System.out.println("Navigation links found: " + size);
        return size;
    }
    
    public void clickAnyCategory() {

        By categoryLinks = By.cssSelector("a[href*='flipkart.com']");

        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(categoryLinks));

                List<WebElement> categories = driver.findElements(categoryLinks);

                if (categories.size() > 5) {
                    wait.until(ExpectedConditions.elementToBeClickable(categories.get(5))).click();
                    System.out.println("Clicked a category link");
                    return;
                } else {
                    System.out.println("Not enough category links found");
                    return;
                }

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Retrying click due to stale element (attempt " + attempt + ")");
            }
        }

        throw new RuntimeException("Failed to click category after retries");
    }

    
    public void clickFlipkartLogo() {
        By logo = By.xpath("//img[@title='Flipkart']");
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    
    public boolean isHomePageDisplayed() {

        return driver.getCurrentUrl().equals("https://www.flipkart.com/");
    }

}
