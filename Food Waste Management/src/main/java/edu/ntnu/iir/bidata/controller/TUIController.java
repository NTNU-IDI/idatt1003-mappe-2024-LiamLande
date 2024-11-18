package edu.ntnu.iir.bidata.controller;

import java.util.*;

import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.view.PrintModel;
import edu.ntnu.iir.bidata.controller.registers.Cookbook;
import edu.ntnu.iir.bidata.controller.registers.FoodStorage;
import edu.ntnu.iir.bidata.model.entities.Ingredient;
import edu.ntnu.iir.bidata.model.entities.Recipe;


public class TUIController {
    private FoodStorage mainStorage;
    private Cookbook mainCookbook;

    public void setRegisters(FoodStorage mainStorage, Cookbook mainCookbook) {
        this.mainStorage = mainStorage;
        this.mainCookbook = mainCookbook;
    }

    public void foodStorageMenu() {
        PrintModel.print("Welcome to the FoodStorage Menu");
        PrintModel.storageMenu();
        int choice = InputValidator.readInt("(1,2,3,4,5,6)", 7);
        switch (choice) {
            case 1:
                mainStorage.addIngredient(this.makeIngredient());

                this.foodStorageMenu();
                break;
            case 2:
                mainStorage.findIngredient(InputValidator.readString("Name of Ingredient"));

                this.foodStorageMenu();
                break;
            case 3:
                String Name = InputValidator.readString("Name of Ingredient:");
                mainStorage.removeIngredientAmount(
                        Name,
                        InputValidator.readDouble("Amount to remove:", mainStorage.getIngredient(Name).getAmount()),
                        InputValidator.readBoolean("Do you wish to update the price?")
                );

                this.foodStorageMenu();
                break;
            case 4:
                mainStorage.printIngredients();

                this.foodStorageMenu();
                break;
            case 5:
                mainStorage.printExpiredIngredients();

                this.foodStorageMenu();
                break;
            case 6:
                this.start();
                break;
            default:
                break;

        }

    }

    public void start() {
        PrintModel.startMenu();
        int choice = InputValidator.readInt("(1,2,3)", 4);
        switch (choice) {
            case 1:
                this.foodStorageMenu();
                break;
            case 2:
                if (mainCookbook.isInitialized()) {
                    this.cookBookMenu();
                } else {
                    PrintModel.print("Cookbook not initialized, please create a cookbook");
                    mainCookbook.init(
                            InputValidator.readString("Enter name of cookbook"),
                            InputValidator.readString("Enter description of cookbook"),
                            InputValidator.readList("Enter name of authors (seperated by commas)"));
                    PrintModel.print("Cookbook initialized, adding a recipe");
                    mainCookbook.addRecipe(this.makeRecipe());
                }
                break;
            default:
                break;
        }
    }

    public void cookBookMenu() {
        PrintModel.bookMenu();
        int choice = InputValidator.readInt("(1,2,3,4,5,6)", 7);
        switch (choice) {
            case 1:
                mainCookbook.addRecipe(this.makeRecipe());
                break;
            case 2:
                mainCookbook.removeRecipe(InputValidator.readString("Enter name of recipe to remove"));
                break;
            case 3:
                PrintModel.print(mainCookbook.getRecipe(InputValidator.readString("Enter name of recipe to find")));
                break;
            case 4:
                PrintModel.print(mainCookbook);
                break;
            case 5:
                mainCookbook.printAvailableRecipes(mainStorage);
            case 6:
                this.start();
                break;
            default:
                break;
        }
    }


    public Ingredient makeIngredient() {
        String name = InputValidator.readString("Enter name of ingredient");
        int price = InputValidator.readInt("Enter price of ingredient", 0);
        int amount = InputValidator.readInt("Enter amount of ingredient (Liter if liquid, grams if solid, pieces if other)", 0);
        String unit = InputValidator.readUnit("Enter unit of ingredient");
        Date expDate = InputValidator.readDate("Enter expiration date of ingredient");
        return new Ingredient(name, price, amount, expDate, unit);
    }

    public Recipe makeRecipe() {
        String name = InputValidator.readString("Enter name of recipe");
        String ingredients = InputValidator.readString("Enter ingredients of recipe");
        String instructions = InputValidator.readString("Enter instructions of recipe");
        return new Recipe(name, ingredients, instructions);
        //TODO: Finish this
    }

}
