package workflow;

import utils.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class ExcelDataService {

    private final List<String> lfvEliminate = new ArrayList<>();
    private final List<String> lfvAdd = new ArrayList<>();
    private final List<String> lchfEliminate = new ArrayList<>();
    private final List<String> lchfAdd = new ArrayList<>();
    private final List<String> allergies = new ArrayList<>();
    private final List<String> foodCategories = new ArrayList<>();
    private final List<String> cuisines = new ArrayList<>();
    private final List<String> recipeCategories = new ArrayList<>();

    public ExcelDataService() {
        loadAllData();
    }

    public void loadAllData() {
        if (!lfvEliminate.isEmpty()) {
            return;
        }

        ExcelReader ingredientsReader =
                new ExcelReader("testdata/IngredientsAndComorbidities-ScrapperHackathon.xlsx");
        ExcelReader filtersReader =
                new ExcelReader("testdata/Recipe-filters-ScrapperHackathon.xlsx");

        for (int i = 3; i <= 76; i++) {
            addIfNotBlank(lfvEliminate,
                    ingredientsReader.getCellData("Final list for LFV Elimination ", 0, i).toLowerCase());
        }

        for (int i = 3; i <= 90; i++) {
            addIfNotBlank(lfvAdd,
                    ingredientsReader.getCellData("Final list for LFV Elimination ", 1, i).toLowerCase());
        }

        for (int i = 3; i <= 92; i++) {
            addIfNotBlank(lchfEliminate,
                    ingredientsReader.getCellData("Final list for LCHFElimination ", 0, i).toLowerCase());
        }

        for (int i = 3; i <= 34; i++) {
            addIfNotBlank(lchfAdd,
                    ingredientsReader.getCellData("Final list for LCHFElimination ", 1, i).toLowerCase());
        }

        for (int i = 2; i <= 14; i++) {
            addIfNotBlank(allergies,
                    ingredientsReader.getCellData("Filter -1 Allergies - Bonus Poi", 0, i).toLowerCase());
        }

        for (int i = 1; i <= 6; i++) {
            addIfNotBlank(foodCategories,
                    filtersReader.getCellData("Food Category", 0, i));
        }

        for (int i = 2; i <= 32; i++) {
            addIfNotBlank(cuisines,
                    filtersReader.getCellData("Food Category", 1, i));
        }

        for (int i = 2; i <= 32; i++) {
            addIfNotBlank(recipeCategories,
                    filtersReader.getCellData("Food Category", 2, i));
        }
    }

    private void addIfNotBlank(List<String> list, String value) {
        if (value != null && !value.isBlank()) {
            list.add(value.trim());
        }
    }

    public List<String> getLFVEliminateItems() {
        return lfvEliminate;
    }

    public List<String> getLFVAddItems() {
        return lfvAdd;
    }

    public List<String> getLCHFEliminateItems() {
        return lchfEliminate;
    }

    public List<String> getLCHFAddItems() {
        return lchfAdd;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public List<String> getFoodCategories() {
        return foodCategories;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public List<String> getRecipeCategories() {
        return recipeCategories;
    }

    public static void main(String[] args) {
        ExcelDataService service = new ExcelDataService();
        service.loadAllData();
        System.out.println("Food Categories: " + service.getFoodCategories());
        System.out.println("Allergies: " + service.getAllergies());
        System.out.println("LCHF ADD ITEMS : " + service.getLCHFAddItems());
        System.out.println("LCHF elimanimate ITEMS: " + service.getLCHFEliminateItems());
        System.out.println("LFVELIMINATE  ITEMS: " + service.getLFVEliminateItems());
        System.out.println("LFVADDITEMS  ITEMS: " + service.getLFVAddItems());
    }
}