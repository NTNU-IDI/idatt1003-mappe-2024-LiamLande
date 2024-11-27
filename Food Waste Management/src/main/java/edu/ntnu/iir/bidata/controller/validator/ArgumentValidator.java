package edu.ntnu.iir.bidata.controller.validator;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The ArgumentValidator class provides static methods for validating various arguments related to ingredients, recipes, and cookbooks.
 */
public class ArgumentValidator {

    private ArgumentValidator() {
        throw new UnsupportedOperationException("Utility class");
    }


    /**
     * Validates the arguments for a cookbook.
     *
     * @param name        the name of the cookbook
     * @param description the description of the cookbook
     * @param authors     the list of authors of the cookbook
     * @throws IllegalArgumentException if any argument is invalid
     */
    public static void CookbookValidator(String name, String description, ArrayList<String> authors) {
        NameValidator(name);
        NameValidator(description);
        AuthorsValidator(authors);
    }

    /**
     * Validates the list of authors.
     *
     * @param authors the list of authors
     * @throws IllegalArgumentException if the authors list is null or empty
     */
    public static void AuthorsValidator(ArrayList<String> authors) {
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException("Authors cannot be empty");
        }
    }

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
    public static void IngredientValidator(String name, double price, double amount, String unit, LocalDate expDate)
            throws IllegalArgumentException {
        ArgumentValidator.RecipeIngredientValidator(name, price, amount, unit);
        ExpDateValidator(expDate);
    }

    /**
     * Validates the expiration date of an ingredient.
     *
     * @param expDate the expiration date
     * @throws IllegalArgumentException if the expiration date is null or more than 14 days in the past
     */
    public static void ExpDateValidator(LocalDate expDate) {
        if (expDate == null || expDate.isBefore(LocalDate.now().minusDays(14))) {
            throw new IllegalArgumentException("Expiration date cannot be null or more than 14 days in the past");
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
        NameValidator(name);
        PriceValidator(price);
        AmountValidator(amount);
        UnitValidator(unit);
    }

    /**
     * Validates the unit of measurement for an ingredient.
     *
     * @param unit the unit of measurement
     * @throws IllegalArgumentException if the unit is null, empty, or not one of the accepted values (g, ml, pcs)
     */
    public static void UnitValidator(String unit) {
        if (unit == null || unit.isEmpty() || !unit.equalsIgnoreCase("g") && !unit.equalsIgnoreCase("ml") && !unit.equalsIgnoreCase("pcs")) {
            throw new IllegalArgumentException("Unit is not accepted (must be g, ml or pcs)");
        }
    }

    /**
     * Validates the amount of an ingredient.
     *
     * @param amount the amount of the ingredient
     * @throws IllegalArgumentException if the amount is less than or equal to 0
     */
    public static void AmountValidator(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative or null");
        }
    }

    /**
     * Validates the price of an ingredient.
     *
     * @param price the price of the ingredient
     * @throws IllegalArgumentException if the price is less than 0
     */
    public static void PriceValidator(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative or null");
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
        NameValidator(name);
        RecipeDescriptionValidator(description);
        RecipeInstructionValidator(instructions);
    }

    /**
     * Validates the name of an ingredient, recipe, or cookbook.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is null or empty
     */
    public static void NameValidator(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
    }

    /**
     * Validates the description of a recipe.
     *
     * @param description the description to validate
     * @throws IllegalArgumentException if the description is null or empty
     */
    private static void RecipeDescriptionValidator(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty or null");
        }
    }

    /**
     * Validates the instructions of a recipe.
     *
     * @param instructions the instructions to validate
     * @throws IllegalArgumentException if the instructions are null or empty
     */
    private static void RecipeInstructionValidator(String instructions) {
        if (instructions == null || instructions.isEmpty()) {
            throw new IllegalArgumentException("Instructions cannot be empty");
        }
    }
}