package edu.ntnu.iir.bidata.controller.registers;

import edu.ntnu.iir.bidata.model.Recipe;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Cookbook class manages a collection of recipes, including their initialization, addition, removal, and querying.
 */
public class Cookbook {
    private final List<Recipe> Recipes;

    public Cookbook() {
        Recipes = new ArrayList<>();
    }


    /**
     * Returns the list of recipes in the cookbook.
     *
     * @return the list of recipes
     */
    public List<Recipe> getRecipes() {
        return Recipes;
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
                .orElse(new Recipe("Not found", "...", "...", 1));
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
        if (!Objects.equals(recipe.getName(), "Not found")) {
            boolean canMake = recipe.getIngredients().stream()
                    .allMatch(ingredient -> food.findIngredient(ingredient.getName())
                            && ingredient.getAmount() <= food.getIngredient(ingredient.getName()).getAmount());
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
        Recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .allMatch(ingredient -> food.findIngredient(ingredient.getName())
                                && ingredient.getAmount() <= food.getIngredient(ingredient.getName()).getAmount()))
                .forEach(PrintModel::print);
    }
}
