package edu.ntnu.iir.bidata.TUI;

import java.util.*;
import java.text.*;

import edu.ntnu.iir.bidata.registers.Cookbook;
import edu.ntnu.iir.bidata.registers.FoodStorage;
import edu.ntnu.iir.bidata.entities.Ingredient;
import edu.ntnu.iir.bidata.entities.Pair;
import edu.ntnu.iir.bidata.entities.Recipe;


public class TUIController {
    private Scanner S;
    private final TUIView view;


    public int readInt(String prompt, int bound) {
        view.print(prompt);
        int a = 0;
        try {
            a = Integer.parseInt(S.nextLine());
        } catch (NumberFormatException e) {
            view.print("Invalid input, try again");
            return readInt(prompt, bound);
        }
        if (bound == 0) {
            return a;
        }
        if (a > bound) {
            view.print("Number too high, try again");
            return readInt(prompt, bound);
        }
        if (a < 0) {
            view.print("Number too low, try again");
            return readInt(prompt, bound);
        }

        return a;
    }

    public String readString(String prompt) {
        view.print(prompt);
        return S.nextLine();
    }

    public Date readDate(String prompt) {
        view.print(prompt + " (yyyy-MM-dd)");
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String input = S.nextLine();

        System.out.print(input + " Parses as ");
        Date t;
        try {
            t = ft.parse(input);
            view.print(t);
        } catch (ParseException e) {
            view.print("Unparseable using " + ft);
            t = readDate("Try again, remember the format -");
        }
        return t;
    }

    public Boolean readBoolean() {
        view.print("(y/n)");

        String input = S.nextLine();
        if (!input.equals("y") && !input.equals("n")) {
            view.print("Invalid input, try again");
            return readBoolean();
        }
        return input.equals("y");
    }

    public TUIController(Scanner S, TUIView view) {
        this.S = S;
        this.view = view;
    }

    public TUIController(Scanner scanner) {
        S = scanner;
        view = new TUIView();
    }

    public Pair init() {
        view.printWelcome();
        Pair MainModels = new Pair(new FoodStorage(this), new Cookbook(this));
        if (this.readBoolean()) {
            view.print("Loading test data");
            this.testDataLoader(MainModels);
        } else {
            view.print("No test data loaded");
        }
        return MainModels;
    }

    public void start(FoodStorage MainStorage, Cookbook MainCookbook) {
        view.startMenu();
        int choice = this.readInt("(1,2,3)", 4);
        switch (choice) {
            case 1:
                this.foodStorageMenu(MainStorage, MainCookbook);
                break;
            case 2:
                this.cookBookMenu(MainStorage, MainCookbook);
                break;
            default:
                break;
        }
    }

    public void foodStorageMenu(FoodStorage mainStorage, Cookbook mainCookbook) {
        view.print("Welcome to the FoodStorage Menu");
        view.storageMenu();
        int choice = this.readInt("(1,2,3,4,5,6)", 7);
        switch (choice) {
            case 1:
                mainStorage.addIngredient();
                break;
            case 2:
                mainStorage.findIngredient(null);
                break;
            case 3:
                mainStorage.removeIngredientAmount();
                break;
            case 4:
                mainStorage.printIngredients();
                break;
            case 5:
                mainStorage.printExpiredIngredients();
                break;
            case 6:
                this.start(mainStorage, mainCookbook);
                break;
            default:
                break;

        }
        this.foodStorageMenu(mainStorage, mainCookbook);

    }

    private void cookBookMenu(FoodStorage MainStorage, Cookbook MainCookbook) {
        view.bookMenu();
        int choice = this.readInt("(1,2,3,4,5)", 6);
        switch (choice) {
            case 1:
                MainCookbook.addRecipe();

                break;
            case 2:
                view.print("Remove recipe");
                //TODO: Implement remove recipe
                break;
            case 3:
                view.print("Find recipe");
                //TODO: Implement find recipe
                break;
            case 4:
                view.print("Print all recipes");
                //TODO: Implement print all recipes
                break;
            case 5:
                this.start(MainStorage, MainCookbook);
                break;
            default:
                break;
        }
    }

    public void testDataLoader(Pair A) {
        FoodStorage food = (FoodStorage) A.getFirst();
        Cookbook cookbook = (Cookbook) A.getSecond();
        food.addIngredient(new Ingredient("Tomato", 10, 5, new Date()));
        food.addIngredient(new Ingredient("Potato", 20, 2, new Date()));
        food.addIngredient(new Ingredient("Onion", 30, 9, new Date()));
        food.addIngredient(new Ingredient("Garlic", 40, 10, new Date()));
        food.addIngredient(new Ingredient("Carrot", 50, 18, new Date()));
        food.addIngredient(new Ingredient("Cucumber", 60, 5, new Date()));
        food.addIngredient(new Ingredient("Pepper", 70, 100, new Date()));
        food.addIngredient(new Ingredient("Salt", 80, 100, new Date()));

        cookbook.addRecipe(new Recipe("Tomato soup", "Tomato, water, salt", "Cook tomato in water, add salt"));
        cookbook.getRecipe("Tomato soup").addIngredient("Tomato", 4);
        cookbook.getRecipe("Tomato soup").addIngredient("Salt", 2);
        cookbook.getRecipe("Tomato soup").addIngredient("Garlic", 1);
        cookbook.addRecipe(new Recipe("Potato soup", "Potato, water, salt", "Cook potato in water, add salt"));
        cookbook.getRecipe("Potato soup").addIngredient("Potato", 4);
        cookbook.getRecipe("Potato soup").addIngredient("Salt", 2);
        cookbook.getRecipe("Potato soup").addIngredient("Garlic", 1);
        cookbook.addRecipe(new Recipe("Onion soup", "Onion, water, salt", "Cook onion in water, add salt"));
        cookbook.getRecipe("Onion soup").addIngredient("Onion", 4);
        cookbook.getRecipe("Onion soup").addIngredient("Salt", 2);
        cookbook.getRecipe("Onion soup").addIngredient("Garlic", 1);

    }

    public void setInput(String s) {
        this.S = new Scanner(s);
    }
}
