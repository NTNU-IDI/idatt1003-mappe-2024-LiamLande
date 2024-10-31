package edu.ntnu.iir.bidata.TUI;

import java.util.*;
import java.text.*;

import edu.ntnu.iir.bidata.Cookbook;
import edu.ntnu.iir.bidata.FoodStorage;
import edu.ntnu.iir.bidata.entities.Ingredient;
import edu.ntnu.iir.bidata.entities.Pair;
import edu.ntnu.iir.bidata.entities.Recipe;


public class TUI {
    public Scanner S;

    public int readInt(String prompt, int bound) {
        System.out.println(prompt);
        int a = Integer.parseInt(S.nextLine());
        if (bound == 0) {
            return a;
        }
        if (a > bound) {
            System.out.println("Number too high, try again");
            return readInt(prompt, bound);
        }
        if (a < 0) {
            System.out.println("Number too low, try again");
            return readInt(prompt, bound);
        }

        return a;
    }

    public String readString(String prompt) {
        System.out.println(prompt);
        return S.nextLine();
    }

    public Date readDate(String prompt) {
        System.out.println(prompt + " (yyyy-MM-dd)");
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String input = S.nextLine();

        System.out.print(input + " Parses as ");
        Date t;
        try {
            t = ft.parse(input);
            System.out.println(t);
        } catch (ParseException e) {
            System.out.println("Unparseable using " + ft);
            t = readDate("Try again, remember the format -");
        }
        return t;
    }

    public Boolean readBoolean() {
        System.out.println("(y/n)");
        String input = S.nextLine();
        return input.equals("y");
    }

    public TUI() {
        S = new Scanner(System.in);
    }

    public TUI(Scanner scanner) {
        S = scanner;
    }

    public Pair init() {
        System.out.println("\n" +
                "  ______                 _   _____  _                                  \n" +
                " |  ____|               | | / ____|| |                                 \n" +
                " | |__  ___    ___    __| || (___  | |_  ___   _ __  __ _   __ _   ___ \n" +
                " |  __|/ _ \\  / _ \\  / _` | \\___ \\ | __|/ _ \\ | '__|/ _` | / _` | / _ \\\n" +
                " | |  | (_) || (_) || (_| | ____) || |_| (_) || |  | (_| || (_| ||  __/\n" +
                " |_|   \\___/  \\___/  \\__,_||_____/  \\__|\\___/ |_|   \\__,_| \\__, | \\___|\n" +
                "                                                            __/ |      \n" +
                "                                                           |___/       \n");
        System.out.println("Welcome to the food storage system");
        System.out.println("... Initialising variables ...");
        System.out.println("Do you want to load test data?");
        Pair A = new Pair(new FoodStorage(this), new Cookbook(this));
        if (this.readBoolean()) {
            System.out.println("Loading test data");
            this.testDataLoader(A);
        } else {
            System.out.println("No test data loaded");
        }

        return A;

    }

    public void start(FoodStorage MainStorage, Cookbook MainCookbook) {
        System.out.println("1. Food storage management");
        System.out.println("2. Recipes, and other extra features");
        System.out.println("3. Exit");
        int choice = this.readInt("(1,2,3)", 4);
        switch (choice) {
            case 1:
                this.foodStorageMenu(MainStorage, MainCookbook);
                break;
            case 2:
                this.secondMenu(MainStorage, MainCookbook);
                break;
            default:
                break;
        }
    }

    public void foodStorageMenu(FoodStorage mainStorage, Cookbook mainCookbook) {
        boolean on = true;
        System.out.println("Welcome to the FoodStorage Menu");
        while (on) {
            System.out.println("1.Add Ingredient to your food storage");
            System.out.println("2.Remove Ingredient from your food storage");
            System.out.println("3.Find Ingredient in your food storage");
            System.out.println("4.Remove amount from ingredient");
            System.out.println("5.Print all ingredients");
            System.out.println("6.Print all expired ingredients");
            System.out.println("7.Back to main menu");
            int choice = this.readInt("(1,2,3,4,5,6,7)", 8);
            switch (choice) {
                case 1:
                    mainStorage.addIngredient();
                    break;
                case 2:
                    mainStorage.removeIngredient(null);
                    break;
                case 3:
                    mainStorage.findIngredient(null);
                    break;
                case 4:
                    mainStorage.removeIngredientAmount();
                    break;
                case 5:
                    mainStorage.printIngredients();
                    break;
                case 6:
                    mainStorage.printExpiredIngredients();
                    break;
                case 7:
                    on = false;
                    break;
                default:
                    break;
            }
        }
        this.start(mainStorage, null);
    }

    private void secondMenu(FoodStorage MainStorage, Cookbook MainCookbook) {
        System.out.println("Welcome to the second menu");
        System.out.println("1. Add recipe");
        System.out.println("2. Remove recipe");
        System.out.println("3. Find recipe");
        System.out.println("4. Print all recipes");
        System.out.println("5. Back to main menu");
        int choice = this.readInt("(1,2,3,4,5)", 6);
        switch (choice) {
            case 1:
                System.out.println("Add recipe");
                break;
            case 2:
                System.out.println("Remove recipe");
                break;
            case 3:
                System.out.println("Find recipe");
                break;
            case 4:
                System.out.println("Print all recipes");
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
        S = new Scanner(s);
    }
}
