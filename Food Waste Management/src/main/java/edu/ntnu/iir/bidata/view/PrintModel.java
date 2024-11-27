package edu.ntnu.iir.bidata.view;

import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.model.Recipe;
import edu.ntnu.iir.bidata.controller.registers.Cookbook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The PrintModel class provides static methods to print various types of data to the console.
 */
public final class PrintModel {

    /**
     * Prints the welcome message.
     */
    public static void printWelcome() {
        PrintModel.print("\n" +
                "  ______                 _   _____  _                                  \n" +
                " |  ____|               | | / ____|| |                                 \n" +
                " | |__  ___    ___    __| || (___  | |_  ___   _ __  __ _   __ _   ___ \n" +
                " |  __|/ _ \\  / _ \\  / _` | \\___ \\ | __|/ _ \\ | '__|/ _` | / _` | / _ \\\n" +
                " | |  | (_) || (_) || (_| | ____) || |_| (_) || |  | (_| || (_| ||  __/\n" +
                " |_|   \\___/  \\___/  \\__,_||_____/  \\__|\\___/ |_|   \\__,_| \\__, | \\___|\n" +
                "                                                            __/ |      \n" +
                "                                                           |___/       \n");
        PrintModel.print("Welcome to the food storage system");
        PrintModel.print("... Initialising variables ...");
        PrintModel.print("Do you want to load test data?");
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
     * Prints a given date to the console in the format yyyy/MM/dd.
     *
     * @param date the date to print
     */
    public static void print(LocalDate date) {
        PrintModel.print(date.toString());
    }

    /**
     * Prints a given list of strings to the console.
     *
     * @param list the list of strings to print
     */
    public static void print(List<String> list) {
        list.forEach(PrintModel::print);
    }

    /**
     * Prints a given recipe to the console.
     *
     * @param recipe the recipe to print
     */
    public static void print(Recipe recipe) {
        PrintModel.print("--------------------");
        PrintModel.print("Recipe:");
        PrintModel.print(recipe.getName());
        PrintModel.print(recipe.getDescription());
        PrintModel.print("Ingredients:");
        recipe.getIngredients().forEach(PrintModel::print);
        PrintModel.print("Instructions:");
        PrintModel.print(recipe.getInstructions());
        PrintModel.print("--------------------");
    }

    public static void beforeIngredients() {
        PrintModel.print("--------------------");
        PrintModel.print(String.format("%-20s %-10s %-10s %-15s %-10s", "Name", "Amount", "Unit", "Exp Date", "Price"));
    }

    public static void afterIngredients() {
        PrintModel.print("--------------------");
    }

    /**
     * Prints a given ingredient to the console.
     *
     * @param ingredient the ingredient to print
     */
    public static void print(Ingredient ingredient) {
        PrintModel.print("--------------------");
        PrintModel.print(String.format("%-20s %-10.2f %-10s %-15s %-10.2f",
                ingredient.getName(),
                ingredient.getAmount(),
                ingredient.getUnit(),
                (Objects.equals(ingredient.getExpDate(), LocalDate.ofYearDay(9999, 1)) ? "N/A" : ingredient.getExpDate().toString()),
                ingredient.getPrice()));

        if (!Objects.equals(ingredient.getExpDate(), LocalDate.ofYearDay(9999, 1)) && ingredient.getExpDate().isBefore(LocalDate.now())) {
            PrintModel.print("EXPIRED");
        } else if (!Objects.equals(ingredient.getExpDate(), LocalDate.ofYearDay(9999, 1)) && ingredient.getExpDate().isBefore(LocalDate.now().plusDays(1))) {
            PrintModel.print("EXPIRING TOMORROW");
        }
        PrintModel.print("--------------------");
    }

    /**
     * Prints a given cookbook to the console.
     *
     * @param cookbook the cookbook to print
     */
    public static void print(Cookbook cookbook) {
        PrintModel.print(cookbook.getName());
        PrintModel.print("Description:");
        PrintModel.print(cookbook.getDescription());
        PrintModel.print("Authors:");
        PrintModel.print(cookbook.getAuthors());
        PrintModel.print("Recipes:");
        for (Recipe recipe : cookbook.getRecipes()) {
            PrintModel.print(recipe);
        }
    }

    /**
     * Prints the food storage menu to the console.
     */
    public static void storageMenu() {
        PrintModel.print("1.Add Ingredient to your food storage");
        PrintModel.print("2.Find Ingredient in your food storage");
        PrintModel.print("3.Remove amount or whole ingredient");
        PrintModel.print("4.Print all ingredients (sorted by name)");
        PrintModel.print("5.Print all expired ingredients");
        PrintModel.print("6.Back to main menu");
    }

    /**
     * Prints the cook book menu to the console.
     */
    public static void bookMenu() {
        PrintModel.print("Welcome to the Cookbook menu");
        PrintModel.print("1. Add recipe");
        PrintModel.print("2. Remove recipe");
        PrintModel.print("3. Find recipe");
        PrintModel.print("4. Print cookbook and all recipes");
        PrintModel.print("5. Recommend me a recipe");
        PrintModel.print("6. Check if you have all ingredients for a recipe");
        PrintModel.print("7. Back to main menu");
    }

    /**
     * Prints the start menu to the console.
     */
    public static void startMenu() {
        PrintModel.print("1. Food storage");
        PrintModel.print("2. Cookbook");
        PrintModel.print("3. Exit");
    }
}