package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RecipeTest {
    private Recipe recipe;
    private String name;
    private String description;
    private String instructions;
    private int portions;

    @BeforeEach
    void setUp() {
        name = "Pasta";
        description = "Boil pasta";
        instructions = "1. Boil pasta\n2. Serve";
        portions = 5;
        recipe = new Recipe(name, description, instructions, portions);
    }

    @Test
    void testGetName() {
        assertEquals(name, recipe.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals(description, recipe.getDescription());
    }

    @Test
    void testGetInstructions() {
        assertEquals(instructions, recipe.getInstructions());
    }

    @Test
    void testAddIngredient() {
        recipe.addIngredient("Tomato", 10.0, 5, "pcs");
        assertEquals("Tomato", recipe.getIngredients().stream().findFirst().get().getName());
    }

    @Test
    void testNameThrows() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe("", description, instructions, portions),
                "IllegalArgumentException should be thrown if name is empty"
        );
        assertEquals(
                "String cannot be empty or null", exception1.getMessage(), "Messages should match"
        );
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(null, description, instructions, portions),
                "IllegalArgumentException should be thrown if name is null"
        );
        assertEquals(
                "String cannot be empty or null", exception2.getMessage(), "Messages should match"
        );
    }

    @Test
    void testDescriptionThrows() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, "", instructions, portions),
                "IllegalArgumentException should be thrown if description is empty"
        );
        assertEquals(
                "String cannot be empty or null", exception1.getMessage(), "Messages should match"
        );
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, null, instructions, portions),
                "IllegalArgumentException should be thrown if description is null"
        );
        assertEquals(
                "String cannot be empty or null", exception2.getMessage(), "Messages should match"
        );
    }

    @Test
    void testInstructionsThrows() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, description, "", portions),
                "IllegalArgumentException should be thrown if instructions is empty"
        );
        assertEquals(
                "String cannot be empty or null", exception1.getMessage(), "Messages should match"
        );
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, description, null, portions),
                "IllegalArgumentException should be thrown if instructions is null"
        );
        assertEquals(
                "String cannot be empty or null", exception2.getMessage(), "Messages should match"
        );
    }

    @Test
    void testPortionThrows() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, description, instructions, 0),
                "IllegalArgumentException should be thrown if amount is null"
        );
        assertEquals(
                "Amount cannot be negative or null", exception1.getMessage(), "Messages should match"
        );
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, description, instructions, -1),
                "IllegalArgumentException should be thrown if instructions is below 0"
        );
        assertEquals(
                "Amount cannot be negative or null", exception2.getMessage(), "Messages should match"
        );
    }

}
