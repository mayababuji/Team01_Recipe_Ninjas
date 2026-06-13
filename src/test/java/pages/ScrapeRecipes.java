package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScrapeRecipes {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ScrapeRecipes(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * 1. Opens the recipe page and builds a recipe data map.

     */
    /**
     * 2. Scrapes all recipe details from the recipe page.

     */
    /**

    /**
     * 3. Extracts the recipe ID from the recipe URL.

     */


}