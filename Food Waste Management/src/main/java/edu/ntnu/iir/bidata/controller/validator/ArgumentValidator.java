package edu.ntnu.iir.bidata.controller.validator;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The ArgumentValidator class provides static methods for validating various arguments related to ingredients, recipes, and cookbooks.
 */
public class ArgumentValidator {


    public static void CookbookValidator(String name, String description, ArrayList<String> authors) {
        NameValidator(name);
        NameValidator(description);
        AuthorsValidator(authors);
    }

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

    public static void UnitValidator(String unit) {
        if (unit == null || unit.isEmpty() || !unit.equalsIgnoreCase("g") && !unit.equalsIgnoreCase("ml") && !unit.equalsIgnoreCase("pcs")) {
            throw new IllegalArgumentException("Unit is not accepted (must be g, ml or pcs)s");
        }
    }

    public static void AmountValidator(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }

    public static void PriceValidator(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
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

    public static void NameValidator(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private static void RecipeDescriptionValidator(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
    }

    private static void RecipeInstructionValidator(String instructions) {
        if (instructions == null || instructions.isEmpty()) {
            throw new IllegalArgumentException("Instructions cannot be empty");
        }
    }
}