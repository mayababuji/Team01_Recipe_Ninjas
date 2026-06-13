package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchRecipes {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private final By searchBox = By.xpath("//input[@type='text' or @name='q' or contains(@placeholder,'Search') or contains(@placeholder,'recipe')]");
    private final By searchButton = By.xpath("//button[@type='submit'] | //input[@type='submit'] | //button[contains(.,'Search')]");


    public SearchRecipes(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    /**
     * 1. Searches recipes by food category.
     *    - Finds the search input box.
     *    - Clears any existing value.
     *    - Enters the food category text.
     *    - Clicks the search button if present, otherwise presses Enter.
     *    - Waits until recipe links appear in the search results.
     */

    /**
     * 2. Gets the total recipe count from the results text.
     *    - Showing 10 recipes
     */
    /**
     * 3. Collects all recipe URLs from the current result page.

     */



    /**
     * 4. Collects all recipe names from the current result page.

     */
    /**
     * 5. Moves to the next result page if the Next button exists.

     */




}