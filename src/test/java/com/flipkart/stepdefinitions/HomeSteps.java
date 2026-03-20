package com.flipkart.stepdefinitions;

import com.flipkart.base.DriverFactory;

import com.flipkart.pages.HomePage;
import com.flipkart.pages.ProductListingPage;
import com.flipkart.pages.SearchPage;
import io.cucumber.java.en.When;
import com.flipkart.pages.ProductDetailsPage;
import com.flipkart.pages.CartPage;
import com.flipkart.pages.CategoryPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class HomeSteps {

    WebDriver driver = DriverFactory.getDriver();
    HomePage homePage = new HomePage(driver);
    String searchedKeyword;
    ProductListingPage productListingPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;
    CategoryPage categoryPage = new CategoryPage(driver);

    @Given("user launches Flipkart website")
    public void user_launches_flipkart_website() {
        homePage.closeLoginPopupIfPresent();
        cartPage = new CartPage(driver);
    }

    @Then("Flipkart home page should be displayed")
    public void flipkart_home_page_should_be_displayed() {

        String title = homePage.getPageTitle();
        System.out.println("Page Title is: " + title);

        Assert.assertTrue(title.toLowerCase().contains("online"));
    }

    @Then("search box should be visible")
    public void search_box_should_be_visible() {
        Assert.assertTrue(homePage.isSearchBoxDisplayed());
    }
    
    @Then("category menu should be displayed")
    public void category_menu_should_be_displayed() {

        int count = homePage.getCategoryCount();
        System.out.println("Category count: " + count);

        Assert.assertTrue(count > 10);
    }

    SearchPage searchPage;

    @When("user searches for {string}")
    public void user_searches_for(String product) {

        searchedKeyword = product;

        searchPage = new SearchPage(driver);   

        searchPage.searchProduct(product);
        productListingPage = new ProductListingPage(driver);
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {

        Assert.assertTrue(searchPage.isSearchHeadingDisplayed(searchedKeyword));
    }
    
    @Then("no results message should be displayed")
    public void no_results_message_should_be_displayed() {

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("search?q="));
    }
    
    @Then("search page should be displayed")
    public void search_page_should_be_displayed() {

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL for special search: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("search?q="));
    }
    
    @Then("multiple products should be displayed")
    public void multiple_products_should_be_displayed() {
        productListingPage = new ProductListingPage(driver);
        int count = productListingPage.getProductCount();
        Assert.assertTrue(count > 1, "Less products displayed!");
    }
    
    @When("user applies price filter from {string} to {string}")
    public void user_applies_price_filter(String min, String max) {
        productListingPage = new ProductListingPage(driver);
        productListingPage.applyPriceFilter(min, max);
    }
    
    @Then("filtered products should be displayed")
    public void filtered_products_should_be_displayed() {
        int count = productListingPage.getProductCount();
        Assert.assertTrue(count > 0, "No filtered products displayed!");
    }
    
    @And("user applies brand filter {string}")
    public void user_applies_brand_filter(String brand) {
        productListingPage.applyBrandFilter(brand);
    }
    
    @And("user sorts products by {string}")
    public void user_sorts_products_by(String option) {
        productListingPage.sortByOption(option);
    }
    
    @When("user clicks on first product")
    public void user_clicks_on_first_product() {
        productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.clickFirstProduct();
    }

    @Then("product details page should be displayed")
    public void product_details_page_should_be_displayed() {
        Assert.assertTrue(productDetailsPage.isProductDetailsDisplayed());
    }
    
    @Then("product details page should display title, price and image")
    public void verify_product_details_components() {
    	Assert.assertTrue(productDetailsPage.areBasicElementsPresent());
    }
    
    @And("user adds product to cart")
    public void user_adds_product_to_cart() {
        productDetailsPage.addToCart();
    }

    @Then("product should be added to cart")
    public void product_should_be_added_to_cart() {
        Assert.assertTrue(cartPage.isProductAddedToCart());
    }
    
    @When("User clicks on Any category")
    public void user_clicks_on_any_category() {
        homePage.clickAnyCategory();
    }

    @Then("Category page should be displayed")
    public void category_page_should_be_displayed() {
        Assert.assertTrue(categoryPage.isCategoryPageDisplayed());
    }

    @When("User clicks on Flipkart logo")
    public void user_clicks_on_flipkart_logo() {
        homePage.clickFlipkartLogo();
    }

    @Then("Home page should be displayed")
    public void home_page_should_be_displayed() {
        Assert.assertTrue(homePage.isHomePageDisplayed());
    }

}
