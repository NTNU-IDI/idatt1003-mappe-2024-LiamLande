package edu.ntnu.iir.bidata.controller;

import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TUIControllerTest {
    private ByteArrayOutputStream outContent;
    private TUIController controller;


    protected void setInput(String input) {
        Scanner scanner = new Scanner(input);
        InputValidator.setScanner(scanner);
    }

    @BeforeEach
    void setUp() {
        controller = new TUIController();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testFoodStorageMenu() {
        setInput("6\n3");
        controller.foodStorageMenu();
        String expectedOut = "Welcome to the FoodStorage Menu";
        assertTrue(outContent.toString().contains(expectedOut));
    }

    @Test
    void testMakeIngredient() {
        setInput("Tomato\n10\npcs\n5\n2024\n12\n10");
        Ingredient testIngredient = controller.makeIngredient();
        assertEquals("Tomato", testIngredient.getName());
    }
}
