package test;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchRecipes;
import pages.ScrapeRecipes;
import workflow.ExcelDataService;
import workflow.RecipeFilterService;

import java.util.*;

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

		Map<String, Object[]> allRecipes = new TreeMap<>();
		Map<String, Object[]> lfvPassedRecipes = new TreeMap<>();
		Map<String, Object[]> lfvAddonRecipes = new TreeMap<>();
		Map<String, Object[]> lfvMilkAllergyRecipes = new TreeMap<>();
		Map<String, Object[]> lfvNutAllergyRecipes = new TreeMap<>();
		Map<String, Object[]> lchfPassedRecipes = new TreeMap<>();
		Map<String, Object[]> lchfAddonRecipes = new TreeMap<>();
		Map<String, Object[]> lchfMilkAllergyRecipes = new TreeMap<>();
		Map<String, Object[]> lchfNutAllergyRecipes = new TreeMap<>();

		homePage.openHomePage();

		for (String foodCategory : foodCategories) {
			System.out.println("Step 5 : Searcning for  food category " + foodCategory);
			searchRecipes.searchByFoodCategory(foodCategory);

			List<String> recipeLinks = searchRecipes.getRecipeLinks();

			for (String recipeLink : recipeLinks) {
				Map<String, String> recipeData = scrapeRecipes.scrapeRecipe(recipeLink, foodCategory);

				List<String> ingredientList = convertIngredientsToList(recipeData.get("ingredients"));

				boolean lfvPass = recipeFilterService.passesElimination(ingredientList, lfvEliminate);

				if (lfvPass) {
					if (recipeFilterService.containsNutAllergen(ingredientList)) {
						lfvNutAllergyRecipes.put("LFV_NUT_" + recipeData.get("recipe_id"),
								new Object[] { recipeData.get("recipe_id"), recipeData.get("recipe_name"),
										recipeData.get("recipe_category"), recipeData.get("food_category"),
										recipeData.get("ingredients"), recipeData.get("preparation_time"),
										recipeData.get("cooking_time"), recipeData.get("tag"),
										recipeData.get("no_of_servings"), recipeData.get("cuisine_category"),
										recipeData.get("recipe_description"), recipeData.get("preparation_method"),
										recipeData.get("nutrient_values"), recipeData.get("recipe_url") });
					}
				}

				/*
				 * ***************************************LCHF**********************************
				 * ********
				 */

				String lchfPass = recipeFilterService.passesEliminationStr(ingredientList, lchfEliminate);

				if (lchfPass.equals("NOTFOUND")) {
					String lchAddPass = recipeFilterService.passesLCHAddStr(ingredientList, lchfAdd);
					if (lchAddPass.equals("FOUND")) {
						lchfAddonRecipes.put("LCHF_Eliminate" + recipeData.get("recipe_id"),
								new Object[] { recipeData.get("recipe_id"), recipeData.get("recipe_name"),
										recipeData.get("recipe_category"), recipeData.get("food_category"),
										recipeData.get("ingredients"), recipeData.get("preparation_time"),
										recipeData.get("cooking_time"), recipeData.get("tag"),
										recipeData.get("no_of_servings"), recipeData.get("cuisine_category"),
										recipeData.get("recipe_description"), recipeData.get("preparation_method"),
										recipeData.get("nutrient_values"), recipeData.get("recipe_url") });

						if (recipeFilterService.containsNutAllergen(ingredientList)) {
							lchfNutAllergyRecipes.put("LCHF_NUT_" + recipeData.get("recipe_id"),
									new Object[] { recipeData.get("recipe_id"), recipeData.get("recipe_name"),
											recipeData.get("recipe_category"), recipeData.get("food_category"),
											recipeData.get("ingredients"), recipeData.get("preparation_time"),
											recipeData.get("cooking_time"), recipeData.get("tag"),
											recipeData.get("no_of_servings"), recipeData.get("cuisine_category"),
											recipeData.get("recipe_description"), recipeData.get("preparation_method"),
											recipeData.get("nutrient_values"), recipeData.get("recipe_url") });
						}
						if (recipeFilterService.containsMilkAllergen(ingredientList)) {
							lchfMilkAllergyRecipes.put("LCHF_NUT_" + recipeData.get("recipe_id"),
									new Object[] { recipeData.get("recipe_id"), recipeData.get("recipe_name"),
											recipeData.get("recipe_category"), recipeData.get("food_category"),
											recipeData.get("ingredients"), recipeData.get("preparation_time"),
											recipeData.get("cooking_time"), recipeData.get("tag"),
											recipeData.get("no_of_servings"), recipeData.get("cuisine_category"),
											recipeData.get("recipe_description"), recipeData.get("preparation_method"),
											recipeData.get("nutrient_values"), recipeData.get("recipe_url") });
						}

					}
				}
				if (lchfPass.equals("FOUND")) {

					lchfPassedRecipes.put("LCHF_Eliminate" + recipeData.get("recipe_id"),
							new Object[] { recipeData.get("recipe_id"), recipeData.get("recipe_name"),
									recipeData.get("recipe_category"), recipeData.get("food_category"),
									recipeData.get("ingredients"), recipeData.get("preparation_time"),
									recipeData.get("cooking_time"), recipeData.get("tag"),
									recipeData.get("no_of_servings"), recipeData.get("cuisine_category"),
									recipeData.get("recipe_description"), recipeData.get("preparation_method"),
									recipeData.get("nutrient_values"), recipeData.get("recipe_url") });

				}

			}
		}

		/*
		 * ***************************************LCHF END****************************************** */

		dbQueries.insertRow(conn, "lfv_recipes_allergy_with_nut", lfvNutAllergyRecipes);

		dbQueries.insertRow(conn, "lchf_recipes_with_eliminateitems", lchfPassedRecipes); // if ingredient found update
		dbQueries.insertRow(conn, "lchf_recipes_with_addon_items", lchfAddonRecipes);	// lch_Eliminate 
		dbQueries.insertRow(conn, "lchf_recipes_allergy_with_milk", lchfMilkAllergyRecipes);
		dbQueries.insertRow(conn, "lchf_recipes_allergy_with_nut", lchfNutAllergyRecipes);

	}

	private List<String> convertIngredientsToList(String ingredientsText) {
		if (ingredientsText == null || ingredientsText.trim().isEmpty()) {
			return new ArrayList<>();
		}

		return Arrays.stream(ingredientsText.split(",")).map(String::trim).filter(item -> !item.isEmpty()).toList();
	}
}