package edu.ntnu.iir.bidata.controller.registers;

import edu.ntnu.iir.bidata.controller.validator.ArgumentValidator;
import edu.ntnu.iir.bidata.model.Recipe;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.util.ArrayList;

/**
 * The Cookbook class manages a collection of recipes, including their initialization, addition, removal, and querying.
 */
public class Cookbook {
    private String Name;
    private String Description;
    private ArrayList<Recipe> Recipes;
    private ArrayList<String> Authors;


    public Cookbook() {
    }

    /**
     * Checks if the cookbook is initialized with a name and description.
     *
     * @return true if the cookbook is initialized, otherwise false
     */
    public boolean isInitialized() {
        return Name != null && Description != null;
    }

    /**
     * Initializes the cookbook with the specified name, description, and authors.
     *
     * @param name        the name of the cookbook
     * @param description the description of the cookbook
     * @param authors     the list of authors of the cookbook
     */
    public void init(String name, String description, ArrayList<String> authors) {
        try {
            ArgumentValidator.CookbookValidator(name, description, authors);
        } catch (IllegalArgumentException e) {
            PrintModel.print(e.getMessage());
            return;
        }
        Name = name;
        Description = description;
        Authors = authors;
        Recipes = new ArrayList<>();
    }


    /**
     * Returns the description of the cookbook.
     *
     * @return the description of the cookbook
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Returns the name of the cookbook.
     *
     * @return the name of the cookbook
     */
    public String getName() {
        return Name;
    }

    /**
     * Returns the list of recipes in the cookbook.
     *
     * @return the list of recipes
     */
    public ArrayList<Recipe> getRecipes() {
        return Recipes;
    }

    /**
     * Returns the list of authors of the cookbook.
     *
     * @return the list of authors
     */
    public ArrayList<String> getAuthors() {
        return Authors;
    }

    /**
     * Retrieves a recipe from the cookbook by its name.
     *
     * @param name the name of the recipe to retrieve
     * @return the recipe if found, otherwise null
     */
    public Recipe getRecipe(String name) {
        return Recipes.stream()
                .filter(recipe -> recipe.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a recipe to the cookbook.
     *
     * @param recipe the recipe to add
     */
    public void addRecipe(Recipe recipe) {
        Recipes.add(recipe);
    }

    /**
     * Removes a recipe from the cookbook by its name.
     *
     * @param name the name of the recipe to remove
     */
    public void removeRecipe(String name) {
        Recipes.removeIf(recipe -> recipe.getName().equalsIgnoreCase(name));
    }

    public void checkRecipe(FoodStorage food, String RecipeName) {
        Recipe recipe = getRecipe(RecipeName);
        if (recipe != null) {
            boolean canMake = true;
            for (var ingredient : recipe.getIngredients()) {
                if (!food.findIngredient(ingredient.getName()) && ingredient.getAmount() >= food.getIngredient(ingredient.getName()).getAmount()) {
                    canMake = false;
                    break;
                }
            }
            if (canMake) {
                PrintModel.print("You can make this recipe");
            } else {
                PrintModel.print("You can't make this recipe");
            }
        } else {
            PrintModel.print("Recipe not found");
        }
    }

    /**
     * Prints the available recipes that can be made with the ingredients in the specified food storage.
     *
     * @param food the food storage to check for ingredients
     */
    public void printAvailableRecipes(FoodStorage food) {
        for (Recipe recipe : Recipes) {
            boolean canMake = true;
            for (var ingredient : recipe.getIngredients()) {
                if (!food.findIngredient(ingredient.getName()) && ingredient.getAmount() >= food.getIngredient(ingredient.getName()).getAmount()) {
                    canMake = false;
                    break;
                }
            }
            if (canMake) {
                PrintModel.print(recipe);
            }
        }
    }
}