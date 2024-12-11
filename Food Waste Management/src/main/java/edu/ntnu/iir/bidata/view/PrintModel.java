package edu.ntnu.iir.bidata.view;

import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.model.Recipe;
import edu.ntnu.iir.bidata.controller.registers.Cookbook;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <h1>PrintModel</h1>
 * The PrintModel class provides static methods to print various types of data to the console.
 * <p>
 * This class is responsible for printing welcome messages, recipes, ingredients, cookbooks, and menus.
 * </p>
 * <p>
 * The <code>PrintModel</code> class is also the only class that interacts with the console output.
 *
 * @Since 1.0
 */
public final class PrintModel {

    /**
     * Prints the welcome message.
     */
    public static void printWelcome() {
        print("\n" +
                "  ______                 _   _____  _                                  \n" +
                " |  ____|               | | / ____|| |                                 \n" +
                " | |__  ___    ___    __| || (___  | |_  ___   _ __  __ _   __ _   ___ \n" +
                " |  __|/ _ \\  / _ \\  / _` | \\___ \\ | __|/ _ \\ | '__|/ _` | / _` | / _ \\\n" +
                " | |  | (_) || (_) || (_| | ____) || |_| (_) || |  | (_| || (_| ||  __/\n" +
                " |_|   \\___/  \\___/  \\__,_||_____/  \\__|\\___/ |_|   \\__,_| \\__, | \\___|\n" +
                "                                                            __/ |      \n" +
                "                                                           |___/       \n");
        print("Welcome to the food storage system");
        print("... Initialising variables ...");
        print("Do you want to load test data?");
    }

    /**
     * Prints a given string to the console.
     *
     * @param s the string to print
     */
    public static void print(String s) {
        System.out.println(s);
    }


    /**
     * Prints a given recipe to the console.
     *
     * @param recipe the recipe to print
     */
    public static void print(Recipe recipe) {
        print("--------------------");
        print("Recipe:");
        print(recipe.getName());
        print(recipe.getDescription());
        print("Ingredients:");
        recipe.getIngredients().forEach(PrintModel::print);
        print("Instructions:");
        print(recipe.getInstructions());
        print("Portions: " + recipe.getPortions());
        print("--------------------");
    }

    /**
     * Prints the ingredients header to the console.
     */
    public static void beforeIngredients() {
        print("--------------------");
        print(String.format("%-20s %-10s %-10s %-15s %-10s", "Name", "Amount", "Unit", "Exp Date", "Price"));
    }

    /**
     * Prints the ingredients footer to the console.
     */
    public static void afterIngredients() {
        print("--------------------");
    }

    /**
     * Prints a given ingredient to the console.
     *
     * @param ingredient the ingredient to print
     */
    public static void print(Ingredient ingredient) {
        print("--------------------");
        print(String.format("%-20s %-10.2f %-10s %-15s %-10.2f",
                ingredient.getName(),
                ingredient.getAmount(),
                ingredient.getUnit(),
                (Objects.equals(ingredient.getExpDate(), LocalDate.ofYearDay(9999, 1)) ? "N/A" : (ingredient.getExpDate().toString())),
                ingredient.getPrice()));

        if (!Objects.equals(ingredient.getExpDate(), LocalDate.ofYearDay(9999, 1)) && ingredient.getExpDate().isBefore(LocalDate.now())) {
            print("EXPIRED");
        } else if (!Objects.equals(ingredient.getExpDate(), LocalDate.ofYearDay(9999, 1)) && ingredient.getExpDate().isBefore(LocalDate.now().plusDays(1))) {
            print("EXPIRING TOMORROW");
        }
        print("--------------------");
    }

    /**
     * Prints a given cookbook to the console.
     *
     * @param cookbook the cookbook to print
     */
    public static void print(Cookbook cookbook) {
        print("Recipes:");
        cookbook.getRecipes().forEach(PrintModel::print);
    }

    /**
     * Prints the food storage menu to the console.
     */
    public static void storageMenu() {
        print("1.Add Ingredient to your food storage");
        print("2.Find Ingredient in your food storage");
        print("3.Remove amount or whole ingredient");
        print("4.Print all ingredients (sorted by name) + price");
        print("5.Print all expired ingredients + price");
        print("6.Back to main menu");
    }

    /**
     * Prints the cook book menu to the console.
     */
    public static void bookMenu() {
        print("Welcome to the Cookbook menu");
        print("1. Add recipe");
        print("2. Remove recipe");
        print("3. Find recipe");
        print("4. Print cookbook and all recipes");
        print("5. Recommend me some recipes i can make now!");
        print("6. Check if you have all ingredients for a recipe");
        print("7. Back to main menu");
    }

    /**
     * Prints the start menu to the console.
     */
    public static void startMenu() {
        print("1. Food storage");
        print("2. Cookbook");
        print("3. Exit");
    }
}