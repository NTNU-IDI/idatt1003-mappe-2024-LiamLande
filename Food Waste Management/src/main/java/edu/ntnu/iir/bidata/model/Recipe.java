package edu.ntnu.iir.bidata.model;

import edu.ntnu.iir.bidata.controller.validator.ArgumentValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a recipe with a name, description, instructions, and ingredients.
 */
public class Recipe {
    private final String Name;
    private final String Description;
    private final String Instructions;
    private final List<Ingredient> Ingredients;
    private final int Portions;

    /**
     * Constructs a new Recipe with the specified name, description, and instructions.
     *
     * @param name         the name of the recipe
     * @param description  the description of the recipe
     * @param instructions the instructions for the recipe
     */
    public Recipe(String name, String description, String instructions, int portions) throws IllegalArgumentException {
        ArgumentValidator.RecipeValidator(name, description, instructions, portions);
        this.Name = name;
        this.Description = description;
        this.Instructions = instructions;
        this.Portions = portions;
        this.Ingredients = new ArrayList<>();
    }

    /**
     * Returns the description of the recipe.
     *
     * @return the description of the recipe
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Returns the instructions for the recipe.
     *
     * @return the instructions for the recipe
     */
    public String getInstructions() {
        return Instructions;
    }

    /**
     * Returns the name of the recipe.
     *
     * @return the name of the recipe
     */
    public String getName() {
        return Name;
    }

    public int getPortions() {
        return Portions;
    }

    /**
     * Adds an ingredient to the recipe.
     *
     * @param name   the name of the ingredient
     * @param amount the amount of the ingredient
     */
    public void addIngredient(String name, double amount, double price, String unit) {
        Ingredients.add(new Ingredient(name, amount, price, unit));
    }

    /**
     * Returns the ingredients of the recipe.
     *
     * @return a list of ingredients
     */
    public List<Ingredient> getIngredients() {
        return Ingredients;
    }

}