package edu.ntnu.iir.bidata.controller.registers;

import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CookbookTest {
    private Cookbook cookbook;
    private ByteArrayOutputStream outContent;

    protected void setInput(String input) {
        Scanner scanner = new Scanner(input);
        InputValidator.setScanner(scanner);
    }

    @BeforeEach
    void setUp() {
        cookbook = new Cookbook();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

    }


    @Test
    void testAddRecipe() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
        cookbook.addRecipe(recipe);
        assertEquals("Tomato Soup", cookbook.getRecipe("Tomato Soup").getName());
    }

    @Test
    void testRemoveRecipe() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
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
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
        cookbook.addRecipe(recipe);
        assertEquals(recipe, cookbook.getRecipe("Tomato Soup"));
    }

    @Test
    void testGetRecipes() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
        cookbook.addRecipe(recipe);
        Recipe recipe2 = new Recipe("Potato Soup", "A delicious potato soup", "1. Boil potatoes\n2. Add salt\n3. Serve", 5);
        cookbook.addRecipe(recipe2);
        List<Recipe> recArray = new ArrayList<>(List.of(recipe, recipe2));
        assertEquals(recArray, cookbook.getRecipes());
    }


    @Test
    void testCheckRecipeWithGoodIngredients() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
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
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
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
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
        cookbook.addRecipe(recipe);
        recipe.addIngredient("Tomato", 10, 5, "pcs");
        recipe.addIngredient("Salt", 1, 1, "g");
        recipe.addIngredient("Pepper", 1, 1, "g");
        FoodStorage food = new FoodStorage();
        food.addIngredient(new Ingredient("Tomato", 5, 1, "pcs"));

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
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
        cookbook.addRecipe(recipe);
        Recipe recipe2 = new Recipe("Potato Soup", "A delicious potato soup", "1. Boil potatoes\n2. Add salt\n3. Serve", 5);
        cookbook.addRecipe(recipe2);
        FoodStorage food = new FoodStorage();
        food.addIngredient(new Ingredient("Tomato", 10, 5, "pcs"));
        food.addIngredient(new Ingredient("Salt", 1, 1, "g"));
        food.addIngredient(new Ingredient("Pepper", 1, 1, "g"));

        cookbook.printAvailableRecipes(food);
        assertTrue(outContent.toString().contains("Tomato Soup"));
        assertTrue(outContent.toString().contains("Potato Soup"));
    }

    @Test
    void testPrintAvailableRecipesCookbook() {
        Recipe recipe = new Recipe("Tomato Soup", "A delicious tomato soup", "1. Boil tomatoes\n2. Add salt\n3. Serve", 5);
        recipe.addIngredient("Tomato", 10, 5, "pcs");
        recipe.addIngredient("Salt", 1, 1, "g");
        recipe.addIngredient("Pepper", 1, 1, "g");

        cookbook.addRecipe(recipe);

        Recipe recipe2 = new Recipe("Potato Soup", "A delicious potato soup", "1. Boil potatoes\n2. Add salt\n3. Serve", 5);
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

