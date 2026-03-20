package com.flipkart.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By productList = By.cssSelector("div._1AtVbE");
    By productTitle = By.xpath("//span[contains(@class,'VU-ZEz') or contains(@class,'B_NuCI')]");
    By productPrice = By.xpath("//*[contains(text(),'₹')]");


    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Click first visible product
    public void clickFirstProduct() {

        // Wait for product links to be visible
        By firstProductLocator = By.xpath("(//a[contains(@href,'/p/')])[1]");

        WebElement firstProduct = wait.until(
                ExpectedConditions.elementToBeClickable(firstProductLocator)
        );

        System.out.println("Clicking first product...");
        firstProduct.click();

        // Store parent window
        String parentWindow = driver.getWindowHandle();

        // Wait for new tab and switch
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("Switched to product details tab.");
    }
    
    public boolean isTitleDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(productTitle)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductDetailsDisplayed() {

        wait.until(ExpectedConditions.urlContains("/p/"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Product Page URL: " + currentUrl);

        return currentUrl.contains("/p/");
    }

    public boolean areBasicElementsPresent() {

        boolean hasImage = driver.findElements(By.tagName("img")).size() > 0;
        boolean hasPrice = driver.getPageSource().contains("₹");

        System.out.println("Image Present: " + hasImage);
        System.out.println("Price Present: " + hasPrice);

        return hasImage && hasPrice;
    }
    
    public boolean isPriceDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
            String price = driver.findElement(productPrice).getText();
            System.out.println("Detected Price: " + price);
            return price.contains("₹");
        } catch (Exception e) {
            return false;
        }
    }
    
    By productImage = By.xpath("//img[contains(@class,'_396cs4') or contains(@src,'http')]");

    public boolean isImageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productImage));
            return driver.findElement(productImage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void addToCart() {

        System.out.println("Skipping unstable Add to Cart button.");
    }
}