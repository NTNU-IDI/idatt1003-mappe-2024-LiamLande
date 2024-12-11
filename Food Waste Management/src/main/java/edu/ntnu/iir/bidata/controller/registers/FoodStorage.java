package edu.ntnu.iir.bidata.controller.registers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.view.PrintModel;

/**
 * <h1>FoodStorage</h1>
 * The FoodStorage class manages a collection of <code>Ingredient</code> objects, allowing for adding,
 * removing, and querying ingredients.
 *
 * @Since 0.1
 */
public class FoodStorage {
    private final List<Ingredient> ingredients;

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
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(a.getName()))
                .findFirst()
                .ifPresentOrElse(ingredient -> {
                    ingredient.addAmount(a.getAmount());
                    ingredient.setPrice(ingredient.getPrice() + a.getPrice());
                    PrintModel.print("WARNING: Setting expiration date to the latest date, EAT AT YOUR OWN RISK!");
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
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresentOrElse(ingredients::remove, () -> PrintModel.print("Ingredient not found"));
    }

    /**
     * Retrieves an ingredient from the storage by its name.
     *
     * @param Name the name of the ingredient to retrieve
     * @return the <code>Ingredient</code> if found, otherwise null
     */
    public Ingredient getIngredient(String Name) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(Name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if an ingredient exists in the storage by its name.
     *
     * @param name the name of the ingredient to check
     * @return <code>true</code> if the <code>ingredient</code> exists, otherwise <code>false</code>
     */
    public boolean findIngredient(String name) {
        PrintModel.print("Checking for ingredient: " + name);
        Ingredient found = ingredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (found == null) {
            PrintModel.print("Ingredient not found");
        } else {
            PrintModel.beforeIngredients();
            PrintModel.print(found);
        }
        return found != null;
    }

    /**
     * Removes a specified amount of an ingredient from the storage and optionally updates its price.
     *
     * @param name   the name of the ingredient
     * @param amount the amount to remove
     */
    public void removeIngredientAmount(String name, double amount) {
        ingredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresentOrElse(ingredient -> {
                    if (ingredient.getAmount() <= amount) {
                        PrintModel.print("Removing more or equal to the amount, therefore removing " + ingredient.getName());
                        ingredients.remove(ingredient);
                    } else {
                        if (InputValidator.readBoolean("Do you wish to update the price?")) {
                            ingredient.setPrice(ingredient.getPrice() - ingredient.getPrice() * amount / ingredient.getAmount());
                            PrintModel.print("Price updated to: " + ingredient.getPrice() + " NOK");
                        }
                        ingredient.setAmount(ingredient.getAmount() - amount);
                        PrintModel.print("Removed " + amount + ingredient.getUnit() + " of " + ingredient.getName());

                    }
                }, () -> PrintModel.print("Ingredient not found"));
    }

    /**
     * Retrieves all ingredients in the storage sorted by name.
     *
     * @return a <code>List</code> of all <code>Ingredient</code> objects sorted by name
     */
    public List<Ingredient> getIngredientsSorted() {
        return ingredients.stream()
                .sorted(Comparator.comparing(Ingredient::getName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Prints all expired ingredients in the storage and their total value.
     */
    public void printExpiredIngredients() {
        PrintModel.beforeIngredients();
        double total = ingredients.stream()
                .filter(ingredient -> ingredient.getExpDate().isBefore(LocalDate.now()))
                .peek(PrintModel::print)
                .mapToDouble(Ingredient::getPrice)
                .sum();
        PrintModel.afterIngredients();
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