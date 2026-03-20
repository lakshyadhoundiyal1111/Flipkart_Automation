package com.flipkart.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;
    
    By addToCartBtn = By.xpath("//button[contains(.,'ADD TO CART') or contains(.,'GO TO CART')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    

    public boolean isProductAddedToCart() {

        driver.navigate().to("https://www.flipkart.com/viewcart");

        wait.until(ExpectedConditions.urlContains("viewcart"));

        return driver.getCurrentUrl().contains("viewcart");
    }

    public void removeProduct() {

        WebElement removeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(),'Remove')]")
                )
        );

        removeBtn.click();
    }

    public boolean isCartEmpty() {

        return driver.getPageSource().toLowerCase().contains("missing cart items");
    }
}
