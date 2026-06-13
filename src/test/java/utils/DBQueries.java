package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
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
                System.out.println("Step 3 :Creating All Tables");
                st.execute("CREATE TABLE IF NOT EXISTS " + table + " (" +
                        "id SERIAL PRIMARY KEY," +
                        "recipe_id VARCHAR(100)," +
                        "recipe_name TEXT," +
                        "recipe_category TEXT," +
                        "food_category TEXT," +
                        "ingredients TEXT," +
                        "preparation_time TEXT," +
                        "cooking_time TEXT," +
                        "tag TEXT," +
                        "no_of_servings TEXT," +
                        "cuisine_category TEXT," +
                        "recipe_description TEXT," +
                        "preparation_method TEXT," +
                        "nutrient_values TEXT," +
                        "recipe_url TEXT," +
                        "extra_info TEXT" +
                        ")");

            }
//            System.out.println("Created All Tables");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create tables", e);
        }
    }

 /*
 insert logic
  */
}