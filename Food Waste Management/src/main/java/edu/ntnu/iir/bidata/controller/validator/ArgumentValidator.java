package edu.ntnu.iir.bidata.controller.validator;

import java.util.Date;

/**
 * The ArgumentValidator class provides static methods for validating various arguments related to ingredients, recipes, and cookbooks.
 */
public class ArgumentValidator {

    /**
     * Validates the arguments for an ingredient, including its expiration date.
     *
     * @param name    the name of the ingredient
     * @param price   the price of the ingredient
     * @param amount  the amount of the ingredient
     * @param unit    the unit of measurement for the ingredient
     * @param expDate the expiration date of the ingredient
     * @throws IllegalArgumentException if any argument is invalid
     */
    public static void IngredientValidator(String name, double price, double amount, String unit, Date expDate)
            throws IllegalArgumentException {
        ArgumentValidator.RecipeIngredientValidator(name, price, amount, unit);
        if (expDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
    }

    /**
     * Validates the arguments for a recipe ingredient.
     *
     * @param name   the name of the ingredient
     * @param price  the price of the ingredient
     * @param amount the amount of the ingredient
     * @param unit   the unit of measurement for the ingredient
     * @throws IllegalArgumentException if any argument is invalid
     */
    public static void RecipeIngredientValidator(String name, double price, double amount, String unit)
            throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (unit == null || unit.isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be empty");
        }
    }

    /**
     * Validates the arguments for a recipe.
     *
     * @param name         the name of the recipe
     * @param description  the description of the recipe
     * @param instructions the instructions for the recipe
     * @throws IllegalArgumentException if any argument is invalid
     */
    public static void RecipeValidator(String name, String description, String instructions)
            throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (instructions == null || instructions.isEmpty()) {
            throw new IllegalArgumentException("Instructions cannot be empty");
        }
    }

    /**
     * Validates the arguments for a cookbook.
     *
     * @param name        the name of the cookbook
     * @param description the description of the cookbook
     * @throws IllegalArgumentException if any argument is invalid
     */
    public static void CookbookValidator(String name, String description)
            throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
    }
}