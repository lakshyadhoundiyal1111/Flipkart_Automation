package com.flipkart.pages;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductListingPage {

    WebDriver driver;

    // Constructor
    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for product titles
    By products = By.cssSelector("div[data-id]");
    By minPriceDropdown = By.xpath("(//select[contains(@class,'hbnjE2')])[1]");
    By maxPriceDropdown = By.xpath("(//select[contains(@class,'hbnjE2')])[2]");
    By brandSection = By.xpath("//div[text()='Brand']");
    By brandCheckbox(String brand) {
        return By.xpath("//div[text()='Brand']/following::div[text()='" + brand + "']/preceding-sibling::div");
    }
    By sortDropdown = By.xpath("//div[contains(text(),'Sort By')]/following::div[contains(@class,'_10UF8M')]");


  
    // Method to get product count
    public int getProductCount() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-id]")));

        List<WebElement> productList = driver.findElements(products);
        System.out.println("Total Products Found: " + productList.size());

        return productList.size();
    }
    
    public void applyPriceFilter(String min, String max) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(minPriceDropdown));

        Select minSelect = new Select(driver.findElement(minPriceDropdown));
        minSelect.selectByVisibleText("₹" + min);

        Select maxSelect = new Select(driver.findElement(maxPriceDropdown));
        maxSelect.selectByVisibleText("₹" + max);

        System.out.println("Applied Price Filter: ₹" + min + " to ₹" + max);

        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
    }
    
    public void applyBrandFilter(String brand) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to brand section
        js.executeScript("window.scrollBy(0,800);");

        // Locate visible brand directly
        By brandLocator = By.xpath("//div[text()='" + brand + "']");

        WebElement brandElement = wait.until(
                ExpectedConditions.elementToBeClickable(brandLocator));

        js.executeScript("arguments[0].scrollIntoView(true);", brandElement);
        brandElement.click();

        System.out.println("Applied Brand Filter: " + brand);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(products));
    }

    public void sortByOption(String option) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By sortOption = By.xpath("//div[contains(text(),'" + option + "')]");

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(sortOption)
        );

        element.click();

        System.out.println("Sorting applied: " + option);
    }

}
