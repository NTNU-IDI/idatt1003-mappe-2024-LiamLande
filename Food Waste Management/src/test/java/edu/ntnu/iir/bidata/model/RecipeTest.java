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

    @BeforeEach
    void setUp() {
        name = "Pasta";
        description = "Boil pasta";
        instructions = "1. Boil pasta\n2. Serve";
        recipe = new Recipe(name, description, instructions);
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
                () -> new Recipe("", description, instructions),
                "IllegalArgumentException should be thrown if name is empty"
        );
        assertEquals(
                "String cannot be empty or null", exception1.getMessage(), "Messages should match"
        );
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(null, description, instructions),
                "IllegalArgumentException should be thrown if name is null"
        );
        assertEquals(
                "String cannot be empty or null", exception2.getMessage(), "Messages should match"
        );
    }

    @Test
    void testDescriptionThrows() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, "", instructions),
                "IllegalArgumentException should be thrown if description is empty"
        );
        assertEquals(
                "String cannot be empty or null", exception1.getMessage(), "Messages should match"
        );
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, null, instructions),
                "IllegalArgumentException should be thrown if description is null"
        );
        assertEquals(
                "String cannot be empty or null", exception2.getMessage(), "Messages should match"
        );
    }

    @Test
    void testInstructionsThrows() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, description, ""),
                "IllegalArgumentException should be thrown if instructions is empty"
        );
        assertEquals(
                "String cannot be empty or null", exception1.getMessage(), "Messages should match"
        );
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, description, null),
                "IllegalArgumentException should be thrown if instructions is null"
        );
        assertEquals(
                "String cannot be empty or null", exception2.getMessage(), "Messages should match"
        );
    }

}
