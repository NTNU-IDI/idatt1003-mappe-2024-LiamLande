package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.TUI.TUI;
import edu.ntnu.iir.bidata.entities.Recipe;

import java.util.ArrayList;

public class Cookbook {
    private String Name;
    private String Description;
    private ArrayList<Recipe> Recipes;
    private TUI a;

    public Cookbook(TUI a) {
        this.a = a;
        Recipes = new ArrayList<Recipe>();
    }

    public Cookbook(String name, String description) {
        Name = name;
        Description = description;
        Recipes = new ArrayList<Recipe>();
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        Recipes = recipes;
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

    public void removeRecipe(Recipe recipe) {
        Recipes.remove(recipe);
    }

    public void printAvailableRecipes(FoodStorage food) {
        for (Recipe recipe : Recipes) {
            for (String ingredient : recipe.getIngredients().keySet()) {
                if (food.findIngredient(ingredient)) {
                    System.out.println(recipe.toString());
                    break;
                }
            }
        }
    }
    
}
