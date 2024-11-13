import edu.ntnu.iir.bidata.entities.Ingredient;
import edu.ntnu.iir.bidata.TUI.TUIController;
import junit.framework.TestCase;
import edu.ntnu.iir.bidata.registers.FoodStorage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

public class FoodStorageTest extends TestCase {

    private TUIController controller;
    private FoodStorage foodStorage;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    public FoodStorageTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        controller = new TUIController();
        foodStorage = new FoodStorage(controller);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.setOut(originalOut);
    }


    public void testAddIngredientWithLegalValues() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, new Date());
        foodStorage.addIngredient(ingredient);
        assertTrue(foodStorage.findIngredient("Tomato"));
    }

    public void testAddIngredientWithIllegalValues() {
        try {
            Ingredient ingredient = new Ingredient("", -10, -5, new Date());
            foodStorage.addIngredient(ingredient);
            fail("Expected IllegalArgumentException for illegal values");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    public void testRemoveIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, new Date());
        foodStorage.addIngredient(ingredient);
        foodStorage.removeIngredient(ingredient.getName());
        assertFalse(foodStorage.findIngredient("Tomato"));
    }

    public void testFindIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, new Date());
        foodStorage.addIngredient(ingredient);
        assertTrue(foodStorage.findIngredient("Tomato"));
        assertFalse(foodStorage.findIngredient("Potato"));
    }

    public void testRemoveIngredientAmount() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, new Date());
        foodStorage.addIngredient(ingredient);
        controller.setInput("Tomato\n2\nfalse\n");
        foodStorage.removeIngredientAmount();
        assertEquals(3, ingredient.getAmount());
    }

    public void testPrintIngredients() {
        Ingredient ingredient1 = new Ingredient("Tomato", 10, 5, new Date());
        Ingredient ingredient2 = new Ingredient("Potato", 20, 10, new Date());
        foodStorage.addIngredient(ingredient1);
        foodStorage.addIngredient(ingredient2);
        foodStorage.printIngredients();
        String expectedOutput = "Ingredients:\r\n" + ingredient1.toString() + "\r\n" + ingredient2.toString() + "\r\n" + "Total value of ingredients: " + (ingredient1.getPpu() * ingredient1.getAmount() + ingredient2.getPpu() * ingredient2.getAmount()) + "\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    public void testPrintExpiredIngredients() {
        Date pastDate = new Date(System.currentTimeMillis() - 100000);
        Ingredient expiredIngredient = new Ingredient("Expired", 10, 5, pastDate);
        foodStorage.addIngredient(expiredIngredient);
        foodStorage.printExpiredIngredients();
        String expectedOutput = expiredIngredient.toString() + "\r\n" + "Total value of expired ingredients: " + expiredIngredient.getPrice() + "\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    public void testPrintTotalValue() {
        Ingredient ingredient1 = new Ingredient("Tomato", 10, 5, new Date());
        Ingredient ingredient2 = new Ingredient("Potato", 20, 10, new Date());
        foodStorage.addIngredient(ingredient1);
        foodStorage.addIngredient(ingredient2);
        foodStorage.printTotalValue();
        String expectedOutput = "Total value of ingredients: " + (ingredient1.getPrice() + ingredient2.getPrice()) + "\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}