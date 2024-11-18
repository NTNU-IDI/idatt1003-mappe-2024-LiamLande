package edu.ntnu.iir.bidata.controller;

import edu.ntnu.iir.bidata.controller.registers.Cookbook;
import edu.ntnu.iir.bidata.controller.registers.FoodStorage;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.model.entities.Ingredient;
import edu.ntnu.iir.bidata.model.entities.Recipe;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Initializer {
    private final TUIController controller;

    public Initializer(TUIController controller) {
        this.controller = controller;
    }

    public void init() {
        PrintModel.printWelcome();
        FoodStorage mainStorage = new FoodStorage();
        Cookbook mainCookbook = new Cookbook();
        controller.setRegisters(mainStorage, mainCookbook);
        if (InputValidator.readBoolean()) {
            PrintModel.print("Loading test data");
            this.testDataLoader(mainStorage, mainCookbook);
        } else {
            PrintModel.print("No test data loaded");
        }
    }

    public void testDataLoader(FoodStorage food, Cookbook cookbook) {
        food.addIngredient(new Ingredient("Tomato", 10, 5, new Date(), "pcs"));
        food.addIngredient(new Ingredient("Potato", 20, 2, new Date(), "pcs"));
        food.addIngredient(new Ingredient("Onion", 30, 9, new Date(), "pcs"));
        food.addIngredient(new Ingredient("Garlic", 40, 10, new Date(), "pcs"));
        food.addIngredient(new Ingredient("Carrot", 50, 18, new Date(), "pcs"));
        food.addIngredient(new Ingredient("Cucumber", 60, 5, new Date(), "pcs"));
        food.addIngredient(new Ingredient("Pepper", 70, 0.1, new Date(), "kg"));
        food.addIngredient(new Ingredient("Salt", 80, 0.1, new Date(), "kg"));

        ArrayList<String> authors = new ArrayList<>();
        authors.add("John Doe");
        cookbook.init("Test cookbook", "A cookbook for testing", authors);
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
}
