import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    private static WebDriver driver;
    private static JavascriptExecutor jsExecutor;
    private static WebDriverWait waitingVariable;
    private static Account account;
    private static final String product = "MacBook M1 13.3";
    static {
        driver = null;
        WebDriverManager.chromedriver().browserVersion("77.0.3865.40").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.get("https://www.tunisianet.com.tn/");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
        waitingVariable = new WebDriverWait(driver, 50);
        account = new Account();
    }
    public static void main(String[] args) throws InterruptedException {

        // Step1 : finding the button of account and clicking on it
        WebElement accountButton = waitingVariable.until(ExpectedConditions
                .elementToBeClickable(By.className("user-info")));
        jsExecutor.executeScript("arguments[0].click()", accountButton);
        Thread.sleep(1000);

        // Step 2 : opening the connexion toggle
        WebElement connexionButton = waitingVariable.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".user-down > li > a > span")));
        connexionButton.click();
        Thread.sleep(1000);

        // Step 3 : opening the creating account form
        WebElement createAccountButton = waitingVariable.until(ExpectedConditions
                .elementToBeClickable(By.linkText("Pas de compte ? Créez-en un")));
        createAccountButton.click();
        Thread.sleep(1000);

        // Step 4 : filling the form and creating the account
            // Male option
        WebElement genderOption = waitingVariable.until(ExpectedConditions.elementToBeClickable(By.className("custom-radio")));
        genderOption.click();
        Thread.sleep(1000);
            // Getting all the input fields
        List<WebElement> textFields = driver.findElements(By.className("form-control"));
            // LastName field
        textFields.get(2).sendKeys(account.getLastname());
        Thread.sleep(1000);
            // Firstname field
        textFields.get(3).sendKeys(account.getName());
        Thread.sleep(1000);
            // Email field
        textFields.get(4).sendKeys(account.getEmail());
        Thread.sleep(1000);
            // Password field
        textFields.get(5).sendKeys(account.getPassword());
        Thread.sleep(1000);
            // Date of birth field
        textFields.get(6).sendKeys(account.getDob());
        Thread.sleep(1000);
            // scroll down
        jsExecutor.executeScript("window.scrollBy(0,250)");
        Thread.sleep(1000);
            // Submitting the form
        WebElement submitFormButton = driver.findElement(By.className("form-control-submit"));
        submitFormButton.click();
        Thread.sleep(1000);

        // Step 5 : Logging out from the account
            // reloading the account button
        accountButton = waitingVariable.until(ExpectedConditions
                .elementToBeClickable(By.className("user-info")));
        jsExecutor.executeScript("arguments[0].click()", accountButton);
        Thread.sleep(1000);
            // Clicking on the logout button
        WebElement logoutButton = waitingVariable.until(ExpectedConditions.elementToBeClickable(
                By.className("logout")
        ));
        logoutButton.click();
        Thread.sleep(1000);

        // Step 6 : signing in again to the same account
            // The same as before toggling signing in form
        accountButton = waitingVariable.until(ExpectedConditions
                .elementToBeClickable(By.className("user-info")));
        jsExecutor.executeScript("arguments[0].click()", accountButton);
        Thread.sleep(1000);

        connexionButton = waitingVariable.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".user-down > li > a > span")));
        jsExecutor.executeScript("arguments[0].click()", connexionButton);
        Thread.sleep(1000);
            // filling the form with credentials of the account and submitting the request
        WebElement emailField = waitingVariable.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("email")
        ));
        WebElement passwordField = waitingVariable.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("password")
        ));
        emailField.sendKeys(account.getEmail());
        Thread.sleep(1000);
        passwordField.sendKeys(account.getPassword());
        Thread.sleep(1000);

        WebElement signingButton = driver.findElement(By.id("submit-login"));
        signingButton.click();
        Thread.sleep(1000);

        // Step 7 : Looking for the Product from the search bar
        WebElement searchBar = waitingVariable.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("search_query_top")
        ));
        searchBar.sendKeys(product);
        Thread.sleep(1000);
        WebElement searchButton = driver.findElement(By.name("submit_search"));
        searchButton.click();
        Thread.sleep(1000);

        // Step 8 : Opening the product Details
        WebElement productDetailsLink = waitingVariable.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("PC Portable Apple MacBook Pro M1 (2020) 13.3\" / Argent")
                )
        );
        productDetailsLink.click();
        Thread.sleep(1000);
        // Step 9 : Add To cart
        WebElement addProductToCartButton = waitingVariable.until(
                ExpectedConditions.elementToBeClickable(
                        By.className("add-to-cart")
                )
        );
        addProductToCartButton.click();
        Thread.sleep(1000);

        WebElement commandButton = waitingVariable.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Commander")
                )
        );
        commandButton.click();

    }
}
