package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchRecipes {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private final By searchBox = By.cssSelector("//input[@type='search']");
    private final By searchButton = By.xpath("//i[@class='fa fa-search']");
    private final By recipeLinks = By.xpath("//h5[@class='mb-0 two-line-text']/a");


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
    public void searchByFoodCategory(String foodCategory) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        input.clear();
        input.sendKeys(foodCategory);

        List<WebElement> searchButtons = driver.findElements(searchButton);
        if (!searchButtons.isEmpty() && searchButtons.get(0).isDisplayed()) {
            searchButtons.get(0).click();
        } else {
            input.sendKeys(Keys.ENTER);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(recipeLinks));
    }
    public List<String> getRecipeUrls() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(recipeLinks));
        List<String> urls = new ArrayList<>();
        for (WebElement e : driver.findElements(recipeLinks)) {
            String href = e.getAttribute("href");
            if (href != null && !href.isBlank()) {
                urls.add(href);
            }
        }
        return urls;
    }

    public List<String> getRecipeLinks() {
        return getRecipeUrls();
    }

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