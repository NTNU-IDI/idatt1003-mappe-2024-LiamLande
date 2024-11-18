package edu.ntnu.iir.bidata.controller.registers;

import edu.ntnu.iir.bidata.model.entities.Recipe;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.util.ArrayList;

public class Cookbook {
    private String Name;
    private String Description;
    private ArrayList<Recipe> Recipes;
    private ArrayList<String> Authors;

    public boolean isInitialized() {
        return Name != null && Description != null;
    }

    public void init(String name, String description, ArrayList<String> authors) {
        Name = name;
        Description = description;
        Authors = authors;
        Recipes = new ArrayList<>();
    }


    public String getDescription() {
        return Description;
    }

    public String getName() {
        return Name;
    }

    public ArrayList<Recipe> getRecipes() {
        return Recipes;
    }

    public ArrayList<String> getAuthors() {
        return Authors;
    }

    public Recipe getRecipe(String name) {
        for (Recipe recipe : Recipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    public void addRecipe(Recipe recipe) {
        Recipes.add(recipe);
    }

    public void removeRecipe(String name) {
        for (int i = 0; i < Recipes.size(); i++) {
            if (Recipes.get(i).getName().equals(name)) {
                Recipes.remove(i);
                return;
            }
        }
    }


    public void printAvailableRecipes(FoodStorage food) {
        for (Recipe recipe : Recipes) {
            for (String ingredient : recipe.getIngredients().keySet()) {
                if (food.findIngredient(ingredient)) {
                    PrintModel.print(recipe);
                    return;
                }
            }
        }
    }

}
