package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage {

    WebDriver driver;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCategoryPageDisplayed() {

        return !driver.getCurrentUrl().equals("https://www.flipkart.com/");
    }
}