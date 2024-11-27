import edu.ntnu.iir.bidata.controller.TUIController;
import edu.ntnu.iir.bidata.controller.registers.Cookbook;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.model.Recipe;
import edu.ntnu.iir.bidata.view.PrintModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testInit() {
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
        assertTrue(outContent.toString().contains("Name cannot be empty or null"));
    }


}
