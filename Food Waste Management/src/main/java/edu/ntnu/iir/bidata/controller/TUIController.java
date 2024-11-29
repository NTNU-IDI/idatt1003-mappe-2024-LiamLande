package edu.ntnu.iir.bidata.controller;

import java.time.LocalDate;

import edu.ntnu.iir.bidata.controller.validator.ArgumentValidator;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.view.PrintModel;
import edu.ntnu.iir.bidata.controller.registers.Cookbook;
import edu.ntnu.iir.bidata.controller.registers.FoodStorage;
import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.model.Recipe;

/**
 * The TUIController class handles the text-based user interface for the application.
 */
public class TUIController {
    private FoodStorage mainStorage;
    private Cookbook mainCookbook;

    /**
     * Sets the registers for the main storage and main cookbook.
     *
     * @param mainStorage  the main food storage
     * @param mainCookbook the main cookbook
     */
    public void setRegisters(FoodStorage mainStorage, Cookbook mainCookbook) {
        this.mainStorage = mainStorage;
        this.mainCookbook = mainCookbook;
    }

    /**
     * Displays the food storage menu and handles user input for various actions.
     */
    public void foodStorageMenu() {
        PrintModel.print("Welcome to the FoodStorage Menu");
        PrintModel.storageMenu();
        int choice = InputValidator.readInt("(1,2,3,4,5,6)", 7);
        switch (choice) {
            case 1 -> mainStorage.addIngredient(this.makeIngredient());
            case 2 -> mainStorage.findIngredient(InputValidator.readString("Name of Ingredient"));
            case 3 -> {
                String Name = InputValidator.readString("Name of Ingredient:");
                mainStorage.removeIngredientAmount(
                        Name,
                        InputValidator.readDouble("Amount to remove:", 0)
                );
            }
            case 4 -> printAllIngredientsSorted();
            case 5 -> mainStorage.printExpiredIngredients();
            case 6 -> {
                this.start();
                return;
            }
            default -> {
            }

        }
        this.foodStorageMenu();
    }

    private void printAllIngredientsSorted() {
        PrintModel.beforeIngredients();
        mainStorage.getIngredientsSorted().forEach(PrintModel::print);
        PrintModel.afterIngredients();
        double total = mainStorage.getIngredientsSorted().stream().mapToDouble(Ingredient::getPrice).sum();
        PrintModel.print("Total price of all ingredients: " + total);
    }

    /**
     * Starts the main menu and handles user input for navigating to different sections.
     */
    public void start() throws IllegalArgumentException {
        PrintModel.startMenu();
        int choice = InputValidator.readInt("(1,2,3)", 4);
        switch (choice) {
            case 1:
                foodStorageMenu();
                break;
            case 2:
                if (!mainCookbook.isInitialized()) {
                    InitializeCookbook();
                }
                cookBookMenu();
                break;
            default:
                break;
        }
    }

    private void InitializeCookbook() {
        PrintModel.print("Cookbook not initialized, please create a cookbook");
        mainCookbook.init(
                InputValidator.readString("Enter name of cookbook"),
                InputValidator.readString("Enter description of cookbook"),
                InputValidator.readList("Enter name of authors (seperated by commas)"));

        PrintModel.print("Cookbook initialized, adding a recipe");
        mainCookbook.addRecipe(this.makeRecipe());
    }

    /**
     * Displays the cookbook menu and handles user input for various actions.
     */
    public void cookBookMenu() {
        PrintModel.bookMenu();
        int choice = InputValidator.readInt("(1,2,3,4,5,6,7)", 7);
        switch (choice) {
            case 1 -> mainCookbook.addRecipe(this.makeRecipe());
            case 2 -> mainCookbook.removeRecipe(InputValidator.readString("Enter name of recipe to remove"));
            case 3 -> {
                Recipe recipe = mainCookbook.getRecipe(InputValidator.readString("Enter name of recipe to find"));
                if (recipe != null) {
                    PrintModel.print(recipe);
                } else {
                    PrintModel.print("Recipe not found");
                }
            }
            case 4 -> PrintModel.print(mainCookbook);
            case 5 -> mainCookbook.printAvailableRecipes(mainStorage);
            case 6 -> mainCookbook.checkRecipe(mainStorage, InputValidator.readString("Enter name of recipe to check"));
            case 7 -> {
                this.start();
                return;
            }
            default -> {
            }
        }
        this.cookBookMenu();
    }

    /**
     * Creates a new Ingredient by prompting the user for input.
     *
     * @return the created Ingredient
     */
    public Ingredient makeIngredient() {
        String name = InputValidator.readString("Enter name of ingredient:");
        int price = InputValidator.readInt("Enter price of ingredient:", 0);
        String unit = InputValidator.readUnit("Enter unit of ingredient:");
        int amount = InputValidator.readInt("Enter amount of ingredient:", 0);
        LocalDate expDate = InputValidator.readDate("Enter expiration date of ingredient");
        try {
            ArgumentValidator.IngredientValidator(name, price, amount, unit, expDate);
        } catch (IllegalArgumentException e) {
            PrintModel.print(e.getMessage());
            return makeIngredient();
        }
        return new Ingredient(name, price, amount, expDate, unit);
    }

    /**
     * Creates a new Recipe by prompting the user for input.
     *
     * @return the created Recipe
     */
    public Recipe makeRecipe() throws IllegalArgumentException {
        String name = InputValidator.readString("Enter name of recipe");
        String description = InputValidator.readString("Enter description of recipe");
        String instructions = InputValidator.readString("Enter instructions of recipe");
        try {
            ArgumentValidator.RecipeValidator(name, description, instructions);
        } catch (IllegalArgumentException e) {
            PrintModel.print(e.getMessage());
            return makeRecipe();
        }
        Recipe NewRecipe = new Recipe(name, description, instructions);

        int choice = InputValidator.readInt("How many ingredients do you want to add to the recipe?", 0);
        for (int i = 0; i < choice; i++) {
            String name_ing = InputValidator.readString("Enter name of ingredient");
            String unit = InputValidator.readUnit("Enter unit of ingredient");
            double amount = InputValidator.readDouble("Enter amount of ingredient", 0);
            double price = InputValidator.readDouble("Enter price of ingredient", 0);
            try {
                ArgumentValidator.RecipeIngredientValidator(name_ing, price, amount, unit);
            } catch (IllegalArgumentException e) {
                PrintModel.print(e.getMessage());
                i--;
                continue;
            }
            NewRecipe.addIngredient(name_ing, amount, price, unit);
        }
        return NewRecipe;
    }

}