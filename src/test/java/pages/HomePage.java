package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

//    private final By searchBox = By.cssSelector("input[type='search']");
//    private final By searchButton = By.cssSelector("button.search-btn");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openHomePage() {
        System.out.println("Step 4 : Opening home page"+ConfigReader.getProperty("app.url"));
        driver.get(ConfigReader.getProperty("app.url"));
    }

//    public void searchRecipe(String foodCategory) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).clear();
//        driver.findElement(searchBox).sendKeys(foodCategory);
//        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
//    }

}