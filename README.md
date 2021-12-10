# SELENIUM UI TESTING
This repository contains the source code for a test scenario ran on [Tunisia net](tunisianet.com.tn) website using java and selenium webdriver

## Requirements
In order to have this project running on your machine you need to have:
* **JDK** : JDK 8 or higher is required
* **Maven** building tool

## Scenario
* Create an account on the website
* Authenticate to the website using the newly created account
* Search for "MacBook M1 13.3" product
* Add the product to the shopping cart and visiting it

## Source code
Two classes where used to elaborate the test scenario: 
* **Account** : a class that generates a random valued account entity which is used to create the account
* **Main** : a class containing the main function that runs the test scenario