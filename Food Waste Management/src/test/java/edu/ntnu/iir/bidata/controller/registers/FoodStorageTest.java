package edu.ntnu.iir.bidata.controller.registers;

import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.view.PrintModel;
import edu.ntnu.iir.bidata.model.Ingredient;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodStorageTest {

    private FoodStorage foodStorage;
    private ByteArrayOutputStream outContent;

    protected void setInput(String input) {
        Scanner scanner = new Scanner(input);
        InputValidator.setScanner(scanner);
    }

    @BeforeEach
    void setUp() {
        foodStorage = new FoodStorage();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void testAddIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10.0, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        assertTrue(foodStorage.findIngredient("Tomato"));
    }

    @Test
    public void testAddDuplicateIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10.0, 5, LocalDate.now(), "pcs");
        Ingredient ingredient2 = new Ingredient("Tomato", 10.0, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        setInput("y");
        foodStorage.addIngredient(ingredient2);
        assertEquals(10.0, foodStorage.getIngredient("Tomato").getAmount());
    }

    @Test
    public void testRemoveIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);

        foodStorage.removeIngredient(ingredient.getName());
        assertFalse(foodStorage.findIngredient("Tomato"));


    }

    @Test
    public void testFindIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        assertTrue(foodStorage.findIngredient("Tomato"));
        assertFalse(foodStorage.findIngredient("Potato"));
    }

    @Test
    public void testRemoveIngredientAmount() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        setInput("n");
        foodStorage.removeIngredientAmount(ingredient.getName(), 2);
        assertEquals(3.0, ingredient.getAmount());
    }

    @Test
    public void testRemoveIngredientAmountWithPriceUpdate() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        setInput("y");
        foodStorage.removeIngredientAmount(ingredient.getName(), 2);
        assertEquals(3.0, ingredient.getAmount());
        assertEquals(6.0, ingredient.getPrice());
    }

    @Test
    public void testGetIngredientsSorted() {
        Ingredient ingredient1 = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        Ingredient ingredient2 = new Ingredient("Potato", 20, 10, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient1);
        foodStorage.addIngredient(ingredient2);
        foodStorage.getIngredientsSorted().forEach(PrintModel::print);
        System.out.println(outContent.toString());
        assertTrue(outContent.toString().indexOf("Tomato") > outContent.toString().indexOf("Potato"));
    }

    @Test
    public void testPrintExpiredIngredients() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        Ingredient expiredIngredient = new Ingredient("Expired", 10, 5, pastDate, "pcs");
        foodStorage = new FoodStorage();
        foodStorage.addIngredient(expiredIngredient);
        foodStorage.printExpiredIngredients();
        String expectedOutput = (String.format("%-20s %-10.2f %-10s %-15s %-10.2f",
                expiredIngredient.getName(),
                expiredIngredient.getAmount(),
                expiredIngredient.getUnit(),
                (expiredIngredient.getExpDate() != null ? expiredIngredient.getExpDate().toString() : "N/A"),
                expiredIngredient.getPrice()));

        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testPrintTotalValue() {
        Ingredient ingredient1 = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        Ingredient ingredient2 = new Ingredient("Potato", 20, 10, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient1);
        foodStorage.addIngredient(ingredient2);
        foodStorage.printTotalValue();
        String expectedOutput = "Total value of ingredients: " + (ingredient1.getPrice() + ingredient2.getPrice()) + "\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testRemoveNonExistentIngredient() {
        foodStorage.removeIngredient("NonExistent");
        assertFalse(foodStorage.findIngredient("NonExistent"));
    }

    @Test
    public void testRemoveIngredientAmountMoreThanExists() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        setInput("n");
        foodStorage.removeIngredientAmount(ingredient.getName(), 10);
        assertFalse(foodStorage.findIngredient("Tomato"));
    }

    @Test
    public void testGetIngredientsSortedWithEmptyStorage() {
        List<Ingredient> sortedIngredients = foodStorage.getIngredientsSorted();
        assertTrue(sortedIngredients.isEmpty());
    }

    @Test
    public void testPrintExpiredIngredientsWithNoExpired() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now().plusDays(1), "pcs");
        foodStorage.addIngredient(ingredient);
        foodStorage.printExpiredIngredients();
        String expectedOutput = "Total value of expired ingredients: 0.0";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testGetIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        Ingredient retrieved = foodStorage.getIngredient("Tomato");
        assertNotNull(retrieved);
        assertEquals("Tomato", retrieved.getName());
        assertEquals(10.0, retrieved.getPrice());
        assertEquals(5.0, retrieved.getAmount());
        assertEquals("pcs", retrieved.getUnit());
        assertEquals(LocalDate.now(), retrieved.getExpDate());
    }

    @Test
    public void testPrintTotalValueWithEmptyStorage() {
        foodStorage.printTotalValue();
        String expectedOutput = "Total value of ingredients: 0.0\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}