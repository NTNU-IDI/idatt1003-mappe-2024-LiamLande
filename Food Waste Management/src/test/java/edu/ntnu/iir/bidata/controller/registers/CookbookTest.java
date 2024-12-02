package edu.ntnu.iir.bidata.controller.registers;

import edu.ntnu.iir.bidata.controller.TUIController;
import edu.ntnu.iir.bidata.controller.registers.Cookbook;
import edu.ntnu.iir.bidata.controller.registers.FoodStorage;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.model.Recipe;
import edu.ntnu.iir.bidata.view.PrintModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CookbookTest {
    private TUIController controller;
    private Cookbook cookbook;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;
    private PrintModel view;

    protected void setInput(String input) {
        Scanner scanner = new Scanner(input);
        new InputValidator(scanner, controller);
    }

    @BeforeEach
    void setUp() {
        controller = new TUIController();
        cookbook = new Cookbook();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        cookbook.init("Test Cookbook", "A cookbook for testing", new ArrayList<>(List.of("Liam Lande", "Markus Aurelius")));

    }

    @Test
    void testIsInitialized() {
        assertTrue(cookbook.isInitialized());
    }

    @Test
    void testIsInitializedNegative() {
        Cookbook testCookbook = new Cookbook();
        assertFalse(testCookbook.isInitialized());
        testCookbook.init("Test Cookbook", "A cookbook for testing", new ArrayList<>(List.of("Liam Lande", "Markus Aurelius")));
        assertTrue(testCookbook.isInitialized());
    }


    @Test
    void testAddRecipe() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe);
        assertEquals("Tomato Soup", cookbook.getRecipe("Tomato Soup").getName());
    }

    @Test
    void testRemoveRecipe() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe);
        cookbook.removeRecipe("Tomato Soup");
        assertEquals("Not found", cookbook.getRecipe("Tomato Soup").getName());
    }

    @Test
    void testRemoveRecipeNoRecipe() {
        cookbook.removeRecipe("");
        assertEquals("Not found", cookbook.getRecipe("").getName());
    }

    @Test
    void testRemoveRecipeNull() {
        cookbook.removeRecipe(null);
        assertEquals("Not found", cookbook.getRecipe(null).getName());
    }

    @Test
    void testGetRecipe() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe);
        assertEquals(recipe, cookbook.getRecipe("Tomato Soup"));
    }

    @Test
    void testGetRecipes() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe);
        Recipe recipe2 = new Recipe("Potato Soup", "A delicious potato soup", "1. Boil potatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe2);
        List<Recipe> recArray = new ArrayList<>(List.of(recipe, recipe2));
        assertEquals(recArray, cookbook.getRecipes());
    }

    @Test
    void testGetName() {
        assertEquals("Test Cookbook", cookbook.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("A cookbook for testing", cookbook.getDescription());
    }

    @Test
    void testGetAuthors() {
        assertEquals(2, cookbook.getAuthors().size());
    }

    @Test
    void testInitWithNullName() {
        Cookbook testcookbook = new Cookbook();
        setInput("test\ntest\ntest, test, test");
        testcookbook.init(null, "A cookbook for testing", new ArrayList<>(List.of("Liam Lande", "Markus Aurelius")));
        assertEquals("test", testcookbook.getName());
        assertEquals("test", testcookbook.getDescription());
        assertEquals(3, testcookbook.getAuthors().size());
    }

    @Test
    void testInitWithNullDescription() {
        Cookbook testcookbook = new Cookbook();
        setInput("test\ntest\ntest, test, test");
        testcookbook.init("Test Cookbook", null, new ArrayList<>(List.of("Liam Lande", "Markus Aurelius")));
        assertEquals("test", testcookbook.getName());
        assertEquals("test", testcookbook.getDescription());
        assertEquals(3, testcookbook.getAuthors().size());
    }

    @Test
    void testInitWithNullList() {
        Cookbook testcookbook = new Cookbook();
        setInput("test\ntest\ntest, test, test");
        testcookbook.init("Test Cookbook", "A cookbook for testing", null);
        assertEquals("test", testcookbook.getName());
        assertEquals("test", testcookbook.getDescription());
        assertEquals(3, testcookbook.getAuthors().size());
    }

    @Test
    void testInitWithGoodValues() {
        Cookbook testcookbook = new Cookbook();
        testcookbook.init("Test Cookbook", "A cookbook for testing", new ArrayList<>(List.of("Liam Lande", "Markus Aurelius")));
        assertEquals("Test Cookbook", testcookbook.getName());
        assertEquals("A cookbook for testing", testcookbook.getDescription());
        assertEquals(2, testcookbook.getAuthors().size());
    }

    @Test
    void testInitExceptions() {
        setInput("test\ntest\ntest");
        Cookbook testcookbook = new Cookbook();
        testcookbook.init(null, null, null);
        assertTrue(outContent.toString().contains("String cannot be empty or null"));
    }

    @Test
    void testCheckRecipeWithGoodIngredients() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe);
        recipe.addIngredient("Tomato", 10, 5, "pcs");
        recipe.addIngredient("Salt", 1, 1, "g");
        recipe.addIngredient("Pepper", 1, 1, "g");
        FoodStorage food = new FoodStorage();
        food.addIngredient(new Ingredient("Tomato", 10, 5, "pcs"));
        food.addIngredient(new Ingredient("Salt", 1, 1, "g"));
        food.addIngredient(new Ingredient("Pepper", 1, 1, "g"));

        cookbook.checkRecipe(food, "Tomato Soup");

        assertTrue(outContent.toString().contains("You can make this recipe"));
    }

    @Test
    void testCheckRecipeWithBadIngredients() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe);
        recipe.addIngredient("Tomato", 10, 5, "pcs");
        recipe.addIngredient("Salt", 1, 1, "g");
        recipe.addIngredient("Pepper", 1, 1, "g");
        FoodStorage food = new FoodStorage();
        food.addIngredient(new Ingredient("Tomato", 10, 4, "pcs"));
        food.addIngredient(new Ingredient("Salt", 1, 1, "g"));
        food.addIngredient(new Ingredient("Pepper", 1, 1, "g"));

        cookbook.checkRecipe(food, "Tomato Soup");

        assertTrue(outContent.toString().contains("You can't make this recipe"));
    }

    @Test
    void testCheckRecipeWithNoIngredients() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe);
        recipe.addIngredient("Tomato", 10, 5, "pcs");
        recipe.addIngredient("Salt", 1, 1, "g");
        recipe.addIngredient("Pepper", 1, 1, "g");
        FoodStorage food = new FoodStorage();

        cookbook.checkRecipe(food, "Tomato Soup");

        assertTrue(outContent.toString().contains("You can't make this recipe"));
    }

    @Test
    void testCheckRecipeWithNoRecipe() {
        FoodStorage food = new FoodStorage();
        food.addIngredient(new Ingredient("Tomato", 10, 5, "pcs"));
        food.addIngredient(new Ingredient("Salt", 1, 1, "g"));
        food.addIngredient(new Ingredient("Pepper", 1, 1, "g"));

        cookbook.checkRecipe(food, "Tomato Soup");

        assertTrue(outContent.toString().contains("Recipe not found"));
    }

    @Test
    void testPrintAllRecipes() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe);
        Recipe recipe2 = new Recipe("Potato Soup", "A delicious potato soup", "1. Boil potatoes\n2. Add salt\n3. Serve");
        cookbook.addRecipe(recipe2);
        FoodStorage food = new FoodStorage();
        food.addIngredient(new Ingredient("Tomato", 10, 5, "pcs"));
        food.addIngredient(new Ingredient("Salt", 1, 1, "g"));
        food.addIngredient(new Ingredient("Pepper", 1, 1, "g"));

        cookbook.printAvailableRecipes(food);
        assertTrue(outContent.toString().contains("Tomato Soup"));
        assertTrue(outContent.toString().contains("Potato Soup"));
    }

    //TODO: Finne ut om det er best å ha negative tester og positive tester i forskjellige klasser
    @Test
    void testPrintAvailableRecipesCookbook() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve");
        recipe.addIngredient("Tomato", 10, 5, "pcs");
        recipe.addIngredient("Salt", 1, 1, "g");
        recipe.addIngredient("Pepper", 1, 1, "g");

        cookbook.addRecipe(recipe);

        Recipe recipe2 = new Recipe("Potato Soup", "A delicious potato soup", "1. Boil potatoes\n2. Add salt\n3. Serve");
        recipe2.addIngredient("Potato", 10, 5, "pcs");
        recipe2.addIngredient("Salt", 1, 1, "g");
        recipe2.addIngredient("Pepper", 1, 1, "g");

        cookbook.addRecipe(recipe2);

        FoodStorage food = new FoodStorage();
        food.addIngredient(new Ingredient("Tomato", 10, 5, "pcs"));
        food.addIngredient(new Ingredient("Salt", 1, 1, "g"));
        food.addIngredient(new Ingredient("Pepper", 1, 1, "g"));

        cookbook.printAvailableRecipes(food);

        assertTrue(outContent.toString().contains("Tomato Soup"));
        assertFalse(outContent.toString().contains("Potato Soup"));

    }

}

