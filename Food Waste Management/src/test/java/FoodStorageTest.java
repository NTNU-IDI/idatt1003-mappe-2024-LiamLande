import edu.ntnu.iir.bidata.view.PrintModel;
import edu.ntnu.iir.bidata.model.entities.Ingredient;
import edu.ntnu.iir.bidata.controller.TUIController;
import junit.framework.TestCase;
import edu.ntnu.iir.bidata.controller.registers.FoodStorage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

public class FoodStorageTest extends TestCase {

    private TUIController controller;
    private FoodStorage foodStorage;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;
    private Scanner scanner;
    private PrintModel view;

    public FoodStorageTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        controller = new TUIController();
        foodStorage = new FoodStorage();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.setOut(originalOut);
    }


    public void testAddIngredientWithLegalValues() {
        Ingredient ingredient = new Ingredient("Tomato", 10.0, 5, new Date(), "pcs");
        foodStorage.addIngredient(ingredient);
        assertTrue(foodStorage.findIngredient("Tomato"));
    }

    public void testAddIngredientWithIllegalValues() {
        try {
            Ingredient ingredient = new Ingredient("", -10, -5, new Date(), "a");
            foodStorage.addIngredient(ingredient);
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    public void testRemoveIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, new Date(), "pcs");
        foodStorage.addIngredient(ingredient);
        foodStorage.removeIngredient(ingredient.getName());
        assertFalse(foodStorage.findIngredient("Tomato"));
    }

    public void testFindIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, new Date(), "pcs");
        foodStorage.addIngredient(ingredient);
        assertTrue(foodStorage.findIngredient("Tomato"));
        assertFalse(foodStorage.findIngredient("Potato"));
    }

    public void testRemoveIngredientAmount() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, new Date(), "pcs");
        foodStorage.addIngredient(ingredient);
        foodStorage.removeIngredientAmount(ingredient.getName(), 2);
        assertEquals(3, ingredient.getAmount());
    }

    public void testGetIngredientsSorted() {
        Ingredient ingredient1 = new Ingredient("Tomato", 10, 5, new Date(), "pcs");
        Ingredient ingredient2 = new Ingredient("Potato", 20, 10, new Date(), "pcs");
        foodStorage.addIngredient(ingredient1);
        foodStorage.addIngredient(ingredient2);
        foodStorage.getIngredientsSorted().forEach(PrintModel::print);
        String expectedOutput = "Ingredients:\r\n" + ingredient1 + "\r\n" + ingredient2 + "\r\n" + "Total value of ingredients: " + (ingredient1.getPpu() * ingredient1.getAmount() + ingredient2.getPpu() * ingredient2.getAmount()) + "\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    public void testPrintExpiredIngredients() {
        Date pastDate = new Date(System.currentTimeMillis() - 100000);
        Ingredient expiredIngredient = new Ingredient("Expired", 10, 5, pastDate, "pcs");
        foodStorage.addIngredient(expiredIngredient);
        foodStorage.printExpiredIngredients();
        String expectedOutput = expiredIngredient + "\r\n" + "Total value of expired ingredients: " + expiredIngredient.getPrice() + "\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    public void testPrintTotalValue() {
        Ingredient ingredient1 = new Ingredient("Tomato", 10, 5, new Date(), "pcs");
        Ingredient ingredient2 = new Ingredient("Potato", 20, 10, new Date(), "pcs");
        foodStorage.addIngredient(ingredient1);
        foodStorage.addIngredient(ingredient2);
        foodStorage.printTotalValue();
        String expectedOutput = "Total value of ingredients: " + (ingredient1.getPrice() + ingredient2.getPrice()) + "\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}