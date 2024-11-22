package edu.ntnu.iir.bidata.controller.registers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.model.entities.Ingredient;
import edu.ntnu.iir.bidata.view.PrintModel;

/**
 * The FoodStorage class manages a collection of ingredients, allowing for adding, removing, and querying ingredients.
 */
public class FoodStorage {
    private final ArrayList<Ingredient> ingredients;

    /**
     * Constructs a new FoodStorage with an empty list of ingredients.
     */
    public FoodStorage() {
        ingredients = new ArrayList<>();
    }

    /**
     * Adds an ingredient to the storage. If the ingredient already exists, updates its amount, price, and expiration date.
     *
     * @param a the ingredient to add
     */
    public void addIngredient(Ingredient a) {
        ingredients.stream()
                .filter(ingredient -> ingredient.getName().equals(a.getName()))
                .findFirst()
                .ifPresentOrElse(ingredient -> {
                    ingredient.addAmount(ingredient.getAmount());
                    if (InputValidator.readBoolean("Do you wish to add the price to the old price or update the old price by new amount (ppu)?")) {

                        ingredient.setPrice(ingredient.getPrice() + a.getPrice());
                    }
                    ingredient.setExpDate(a.getExpDate());
                }, () -> ingredients.add(a));

    }

    /**
     * Removes an ingredient from the storage by its name.
     *
     * @param name the name of the ingredient to remove
     */
    public void removeIngredient(String name) {
        ingredients.stream()
                .filter(ingredient -> ingredient.getName().equals(name))
                .findFirst()
                .ifPresentOrElse(ingredients::remove, () -> PrintModel.print("Ingredient not found"));
    }

    /**
     * Retrieves an ingredient from the storage by its name.
     *
     * @param Name the name of the ingredient to retrieve
     * @return the ingredient if found, otherwise null
     */
    public Ingredient getIngredient(String Name) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getName().equals(Name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if an ingredient exists in the storage by its name.
     *
     * @param name the name of the ingredient to check
     * @return true if the ingredient exists, otherwise false
     */
    public Boolean findIngredient(String name) {
        PrintModel.print("Checking for ingredient: " + name);
        return null != ingredients.stream()
                .filter(ingredient -> ingredient.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Removes a specified amount of an ingredient from the storage and optionally updates its price.
     *
     * @param name        the name of the ingredient
     * @param amount      the amount to remove
     * @param updatePrice whether to update the price of the ingredient
     */
    public void removeIngredientAmount(String name, double amount, boolean updatePrice) {
        ingredients.stream()
                .filter(ingredient -> ingredient.getName().equals(name))
                .findFirst()
                .ifPresentOrElse(ingredient -> {
                    ingredient.setAmount(ingredient.getAmount() - amount);
                    if (updatePrice) {
                        ingredient.setPrice(ingredient.getPrice() - amount * ingredient.getPrice() / ingredient.getAmount());
                    }
                }, () -> PrintModel.print("Ingredient not found"));
    }

    /**
     * Prints all ingredients in the storage.
     */
    public List<Ingredient> printIngredientsSorted() {
        return ingredients.stream()
                .sorted(Comparator.comparing(Ingredient::getName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Prints all expired ingredients in the storage and their total value.
     */
    public void printExpiredIngredients() {
        double total = ingredients.stream()
                .filter(ingredient -> new Date().after(ingredient.getExpDate()))
                .peek(PrintModel::print)
                .mapToDouble(Ingredient::getPrice)
                .sum();
        PrintModel.print("Total value of expired ingredients: " + total);
    }

    /**
     * Prints the total value of all ingredients in the storage.
     */
    public void printTotalValue() {
        double total = ingredients.stream()
                .mapToDouble(Ingredient::getPrice)
                .sum();
        PrintModel.print("Total value of ingredients: " + total);
    }
}