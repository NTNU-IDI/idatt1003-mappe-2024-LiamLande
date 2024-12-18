package edu.ntnu.iir.bidata.controller;

import edu.ntnu.iir.bidata.controller.registers.Cookbook;
import edu.ntnu.iir.bidata.controller.registers.FoodStorage;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.model.Recipe;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.time.LocalDate;

/**
 * <h1>Initializer</h1>
 * The <code>Initializer</code> class is responsible for initializing the application, including loading test data if required.
 *
 * @Since 1.0
 */
public class Initializer {
    private final TUIController controller;

    /**
     * Constructs an Initializer with the specified TUIController.
     *
     * @param controller the TUIController to be used for initialization
     */
    public Initializer(TUIController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the application by printing a welcome message, setting up the main storage and cookbook,
     * and optionally loading test data.
     */
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

    /**
     * Loads test data into the specified food storage and cookbook.
     *
     * @param food     the food storage to load test data into
     * @param cookbook the cookbook to load test data into
     */
    public void testDataLoader(FoodStorage food, Cookbook cookbook) {
        food.addIngredient(new Ingredient("Tomato", 10, 5, LocalDate.now().plusDays(6), "pcs"));
        food.addIngredient(new Ingredient("Potato", 20, 2, LocalDate.now().plusDays(6), "pcs"));
        food.addIngredient(new Ingredient("Onion", 30, 9, LocalDate.now().plusDays(6), "pcs"));
        food.addIngredient(new Ingredient("Garlic", 40, 10, LocalDate.now().plusDays(6), "pcs"));
        food.addIngredient(new Ingredient("Carrot", 50, 18, LocalDate.now().minusDays(7), "pcs"));
        food.addIngredient(new Ingredient("Cucumber", 60, 5, LocalDate.now().plusDays(6), "pcs"));
        food.addIngredient(new Ingredient("Pepper", 70, 100, LocalDate.now().plusDays(6), "g"));
        food.addIngredient(new Ingredient("Salt", 80, 100, LocalDate.now().plusDays(6), "g"));


        cookbook.addRecipe(new Recipe("Tomato soup", "simple tomato soup", "Cook tomato in water, add salt", 5));
        cookbook.getRecipe("Tomato soup").addIngredient("Tomato", 4, 25, "pcs");
        cookbook.getRecipe("Tomato soup").addIngredient("Salt", 2, 0.1, "g");
        cookbook.getRecipe("Tomato soup").addIngredient("Garlic", 1, 10, "pcs");
        cookbook.addRecipe(new Recipe("Potato soup", "simple potato soup", "Cook potato in water, add salt", 5));
        cookbook.getRecipe("Potato soup").addIngredient("Potato", 4, 15, "pcs");
        cookbook.getRecipe("Potato soup").addIngredient("Salt", 2, 5, "g");
        cookbook.getRecipe("Potato soup").addIngredient("Garlic", 1, 10, "pcs");
        cookbook.addRecipe(new Recipe("Onion soup", "not simple onion soup!", "Cook onion in water, add salt", 5));
        cookbook.getRecipe("Onion soup").addIngredient("Onion", 4, 25, "pcs");
        cookbook.getRecipe("Onion soup").addIngredient("Salt", 2, 5, "g");
        cookbook.getRecipe("Onion soup").addIngredient("Garlic", 1, 10, "pcs");
    }
}