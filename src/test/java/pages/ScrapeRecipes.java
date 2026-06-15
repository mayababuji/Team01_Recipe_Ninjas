package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class ScrapeRecipes {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ScrapeRecipes(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public Map<String, String> scrapeRecipe(String recipeUrl, String foodCategory) {
        String recipeName = "";
        String recipeCategory = "";
        String cuisineCategory = "";


        return scrapeRecipe(
                recipeUrl,
                recipeName,
                foodCategory,
                recipeCategory,
                cuisineCategory
        );
    }

    /**
     * 1. Opens the recipe page and builds a recipe data map.

     */
    public Map<String, String> scrapeRecipe(String recipeUrl, String recipeName, String foodCategory,
                                            String recipeCategory, String cuisineCategory) {
        driver.navigate().to(recipeUrl);

        Map<String, String> recipe = new LinkedHashMap<>();
        recipe.put("recipe_id", extractRecipeId(recipeUrl));
        recipe.put("recipe_name", recipeName.isBlank() ? getText(By.xpath("//h1"), "") : recipeName);
        recipe.put("recipe_category", recipeCategory);
        recipe.put("food_category", foodCategory);
        recipe.put("ingredients", getText(By.xpath("//div[@id='ingredients']"), "not available"));
        recipe.put("preparation_time", getText(By.xpath("//h6[text()='Preparation Time']"), ""));
        recipe.put("cooking_time", getText(By.xpath("//h6[text()='Cooking Time']"), ""));
        recipe.put("tag", getText(By.cssSelector("div.col-md-12 ul.tags-list"), "not available"));
        recipe.put("no_of_servings", getText(By.xpath("//h6[text()='Makes ']"), "").replaceAll("[^0-9]", ""));
        recipe.put("cuisine_category", cuisineCategory);
        recipe.put("recipe_description", getText(By.xpath("//*[@id='aboutrecipe']/p[1]"), ""));
        recipe.put("preparation_method", getText(By.xpath("//div[@id='methods']"), "not available"));
        recipe.put("nutrient_values", getText(By.cssSelector("a[href='#nutrients']"), "not available"));
        recipe.put("recipe_url", recipeUrl);

        return recipe;
    }
    /**
     * 2. Scrapes all recipe details from the recipe page.

     */
    private String getText(By locator, String defaultValue) {
        try {
            String text = driver.findElement(locator).getText();
            return text == null || text.isBlank() ? defaultValue : text.trim();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**

    /**
     * 3. Extracts the recipe ID from the recipe URL.

     */
    private String extractRecipeId(String recipeUrl) {
        String id = recipeUrl.substring(recipeUrl.lastIndexOf("-") + 1);
        return id.replaceAll("[^0-9]", "");
    }


}