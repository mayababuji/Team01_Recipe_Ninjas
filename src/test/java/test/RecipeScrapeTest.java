package test;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchRecipes;
import pages.ScrapeRecipes;
import workflow.ExcelDataService;
import workflow.RecipeFilterService;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class RecipeScrapeTest extends BaseTest {

    @Test
    public void scrapeAndInsertFilteredRecipes() {
        HomePage homePage = new HomePage(driver);
        SearchRecipes searchRecipes = new SearchRecipes(driver);
        ScrapeRecipes scrapeRecipes = new ScrapeRecipes(driver);

        ExcelDataService excelDataService = new ExcelDataService();
        RecipeFilterService recipeFilterService = new RecipeFilterService();

        List<String> foodCategories = excelDataService.getFoodCategories();

        List<String> lfvEliminate = excelDataService.getLFVEliminateItems();
        List<String> lfvAdd = excelDataService.getLFVAddItems();

        List<String> lchfEliminate = excelDataService.getLCHFEliminateItems();
        List<String> lchfAdd = excelDataService.getLCHFAddItems();

        List<Map<String, String>> allRecipes = new ArrayList<>();
        List<Map<String, String>> lfvPassedRecipes = new ArrayList<>();
        List<Map<String, String>> lfvAddonRecipes = new ArrayList<>();
        List<Map<String, String>> lfvMilkAllergyRecipes = new ArrayList<>();
        List<Map<String, String>> lfvNutAllergyRecipes = new ArrayList<>();

        List<Map<String, String>> lchfPassedRecipes = new ArrayList<>();
        List<Map<String, String>> lchfAddonRecipes = new ArrayList<>();
        List<Map<String, String>> lchfMilkAllergyRecipes = new ArrayList<>();
        List<Map<String, String>> lchfNutAllergyRecipes = new ArrayList<>();

        homePage.openHomePage();
//        1. For each food category in the food category list:
//        a. Search recipes for that food category
//        b. Get all recipe links from the search results
//
//        c. For each recipe link:
//        i. Scrape recipe details from the recipe page
//        ii. Add the scraped recipe to the master recipe list
//
//        iii. Convert the ingredients text into a list
//
//        iv. Check LFV rules:
//        - If ingredient list does not contain any LFV eliminate items:
//        - Add recipe to LFV passed list
//                - Create a copy of recipe
//        - Add missing LFV add ingredients into ingredient list
//                - Update ingredients field in the copied recipe
//        - Mark extra_info as LFV add-on applied
//                - Add copied recipe to LFV add-on list
//                - If recipe contains milk allergen:
//        - Add to LFV milk allergy list
//                - If recipe contains nut allergen:
//        - Add to LFV nut allergy list
//
//        v. Check LCHF rules:
//        - If ingredient list does not contain any LCHF eliminate items:
//        - Add recipe to LCHF passed list
//                - Create a copy of recipe
//        - Add missing LCHF add ingredients into ingredient list
//                - Update ingredients field in the copied recipe
//        - Mark extra_info as LCHF add-on applied
//                - Add copied recipe to LCHF add-on list
//                - If recipe contains milk allergen:
//        - Add to LCHF milk allergy list
//                - If recipe contains nut allergen:
//        - Add to LCHF nut allergy list
//
//        2. Insert all collected recipe lists into database tables:
//        - Insert all scraped recipes
//                - Insert LFV passed recipes
//                - Insert LFV add-on recipes
//                - Insert LFV milk allergy recipes
//        - Insert LFV nut allergy recipes
//        - Insert LCHF passed recipes
//                - Insert LCHF add-on recipes
//                - Insert LCHF milk allergy recipes
//        - Insert LCHF nut allergy recipes
//
//        END test

    }
}