package workflow;


import java.util.ArrayList;
import java.util.List;

public class RecipeFilterService {

    /**
     * 1. Checks whether the recipe passes elimination rules.
     *    - Loops through each recipe ingredient.
     *    - Converts ingredient and eliminate item to lowercase.
     *    - If any eliminate item is found in an ingredient, returns false.
     *    - If no match is found, returns true.
     */
    public boolean passesElimination(List<String> recipeIngredients, List<String> eliminateList) {
        for (String ingredient : recipeIngredients) {
            String normalizedIngredient = ingredient.toLowerCase().trim();

            for (String eliminateItem : eliminateList) {
                String normalizedEliminate = eliminateItem.toLowerCase().trim();

                if (!normalizedEliminate.isEmpty() &&
                        normalizedIngredient.contains(normalizedEliminate)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 2. Adds missing ingredients from the add list.  -- have to check if we really needs to implement
     *    - Creates a copy of the original ingredient list.
     *    - Checks each add item against the recipe ingredients.
     *    - If the item is missing, adds it to the updated list.
     *    - Returns the updated ingredient list.
     */

    /**
     * 3. Checks whether any allergen is present.
     *    - Loops through each recipe ingredient.
     *    - Compares it with each allergen term.
     *    - If any allergen is found, returns true.
     *    - If nothing matches, returns false.
     */
    public boolean containsAnyAllergen(List<String> recipeIngredients, List<String> allergenList) {
        for (String ingredient : recipeIngredients) {
            String normalizedIngredient = ingredient.toLowerCase().trim();

            for (String allergen : allergenList) {
                String normalizedAllergen = allergen.toLowerCase().trim();

                if (!normalizedAllergen.isEmpty() &&
                        normalizedIngredient.contains(normalizedAllergen)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 4. Checks for milk allergen.
     *    - Creates a list with "milk".
     *    - Calls containsAnyAllergen().
     */
    public boolean containsMilkAllergen(List<String> recipeIngredients) {
        List<String> milkItems = List.of("milk");
        return containsAnyAllergen(recipeIngredients, milkItems);
    }
    /**
     * 5. Checks for nut allergens.
     *    - Creates a list of nut-related items.
     *    - Calls containsAnyAllergen().
     */
    public boolean containsNutAllergen(List<String> recipeIngredients) {
        List<String> nutItems = List.of(
                "peanuts", "walnut", "almond", "hazelnut",
                "pecan", "cashew", "pistachio"
        );
        return containsAnyAllergen(recipeIngredients, nutItems);
    }
}