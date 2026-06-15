package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DBQueries {

    public void createAllTables(Connection conn) {
        String[] tableNames = {
                "recipes_scrapped_by_foodcategory",
                "lfv_recipes_with_eliminateitems",
                "lfv_recipes_with_addon_items",
                "lfv_recipes_allergy_with_milk",
                "lfv_recipes_allergy_with_nut",
                "lchf_recipes_with_eliminateitems",
                "lchf_recipes_with_addon_items",
                "lchf_recipes_allergy_with_milk",
                "lchf_recipes_allergy_with_nut"
        };

        try (Statement st = conn.createStatement()) {
            for (String table : tableNames) {
                System.out.println("Step 3 : Creating table -> " + table);

                st.execute("CREATE TABLE IF NOT EXISTS " + table + " (" +
                        "id SERIAL PRIMARY KEY, " +
                        "recipe_id VARCHAR(100), " +
                        "recipe_name TEXT, " +
                        "recipe_category TEXT, " +
                        "food_category TEXT, " +
                        "ingredients TEXT, " +
                        "preparation_time TEXT, " +
                        "cooking_time TEXT, " +
                        "tag TEXT, " +
                        "no_of_servings TEXT, " +
                        "cuisine_category TEXT, " +
                        "recipe_description TEXT, " +
                        "preparation_method TEXT, " +
                        "nutrient_values TEXT, " +
                        "recipe_url TEXT, " +
                        "ingredients_contains TEXT" +
                        ")");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create tables", e);
        }
    }

    public static void insertRow(Connection conn, String tableName, Map<String, Object[]> recipe) {
        String query = "INSERT INTO " + tableName +
                " (recipe_id, recipe_name, recipe_category, food_category, ingredients, preparation_time, " +
                "cooking_time, tag, no_of_servings, cuisine_category, recipe_description, preparation_method, " +
                "nutrient_values, recipe_url, ingredients_contains) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            for (Object[] recipeObject : recipe.values()) {
                preparedStatement.setString(1, (String) recipeObject[0]);
                preparedStatement.setString(2, (String) recipeObject[1]);
                preparedStatement.setString(3, (String) recipeObject[2]);
                preparedStatement.setString(4, (String) recipeObject[3]);
                preparedStatement.setString(5, (String) recipeObject[4]);
                preparedStatement.setString(6, (String) recipeObject[5]);
                preparedStatement.setString(7, (String) recipeObject[6]);
                preparedStatement.setString(8, (String) recipeObject[7]);
                preparedStatement.setString(9, (String) recipeObject[8]);
                preparedStatement.setString(10, (String) recipeObject[9]);
                preparedStatement.setString(11, (String) recipeObject[10]);
                preparedStatement.setString(12, (String) recipeObject[11]);
                preparedStatement.setString(13, (String) recipeObject[12]);
                preparedStatement.setString(14, (String) recipeObject[13]);
                preparedStatement.setString(15, (String) recipeObject[14]);

                preparedStatement.executeUpdate();
            }

            System.out.println("Recipes details inserted into: " + tableName);

        } catch (SQLException e) {
            System.out.println("Error inserting row: " + e.getMessage());
        }
    }
}