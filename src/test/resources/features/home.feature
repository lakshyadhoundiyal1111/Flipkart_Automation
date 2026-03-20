Feature: Flipkart Home Page

  Scenario: Verify Flipkart home page loads successfully
    Given user launches Flipkart website
    Then Flipkart home page should be displayed

  Scenario: Verify search box is present on home page
    Given user launches Flipkart website
    Then search box should be visible
    
  Scenario: Verify category menu items are displayed
    Given user launches Flipkart website
    Then category menu should be displayed

  Scenario: Search for a valid product
    Given user launches Flipkart website
    When user searches for "laptop"
    Then search results should be displayed

  Scenario: Search with invalid product
    Given user launches Flipkart website
    When user searches for "xyzabc123invalid"
    Then no results message should be displayed

  Scenario: Search with special characters
    Given user launches Flipkart website
    When user searches for "@@@###$$$"
    Then search page should be displayed
    
  Scenario: Verify product listing displays multiple products
  	Given user launches Flipkart website
  	When user searches for "laptop"
  	Then multiple products should be displayed
  	
  Scenario: Apply price filter and verify filtered results
  	Given user launches Flipkart website
  	When user searches for "laptop"
 	And user applies price filter from "40000" to "60000"
  	Then filtered products should be displayed
  	
 # Scenario: Apply brand filter and verify filtered results
 # 	Given user launches Flipkart website
  #	When user searches for "laptop"
  	#And user applies brand filter "Avita"
  	#Then filtered products should be displayed
  	
  Scenario: Sort products by Price Low to High
  Given user launches Flipkart website
  When user searches for "laptop"
  And user sorts products by "Price -- Low to High"
  Then filtered products should be displayed

Scenario: Sort products by Price High to Low
  Given user launches Flipkart website
  When user searches for "laptop"
  And user sorts products by "Price -- High to Low"
  Then filtered products should be displayed

  Scenario: Verify product details page
  Given user launches Flipkart website
  When user searches for "laptop"
  And user clicks on first product
  Then product details page should be displayed

  Scenario: Verify product details components
  Given user launches Flipkart website
  When user searches for "laptop"
  And user clicks on first product
  Then product details page should display title, price and image

  Scenario: Add product to cart and verify
  Given user launches Flipkart website
  When user searches for "laptop"
  And user clicks on first product
  And user adds product to cart
  Then product should be added to cart
  
Scenario: Verify Navigation functionality
When User clicks on Any category
Then Category page should be displayed
When User clicks on Flipkart logo
Then Home page should be displayed

