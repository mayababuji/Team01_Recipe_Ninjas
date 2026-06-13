package workflow;



public class RecipeFilterService {

    /**
     * 1. Checks whether the recipe passes elimination rules.
     *    - Loops through each recipe ingredient.
     *    - Converts ingredient and eliminate item to lowercase.
     *    - If any eliminate item is found in an ingredient, returns false.
     *    - If no match is found, returns true.
     */
    /**
     * 2. Adds missing ingredients from the add list.
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
    /**
     * 4. Checks for milk allergen.
     *    - Creates a list with "milk".
     *    - Calls containsAnyAllergen().
     */
    /**
     * 5. Checks for nut allergens.
     *    - Creates a list of nut-related items.
     *    - Calls containsAnyAllergen().
     */
}