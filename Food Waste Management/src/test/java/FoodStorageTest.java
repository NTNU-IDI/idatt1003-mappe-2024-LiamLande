import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import edu.ntnu.iir.bidata.view.PrintModel;
import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.controller.TUIController;
import junit.framework.TestCase;
import edu.ntnu.iir.bidata.controller.registers.FoodStorage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
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

    protected void setInput(String input) {
        scanner = new Scanner(input);
        new InputValidator(scanner, controller);
    }

    public void testAddIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10.0, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        assertTrue(foodStorage.findIngredient("Tomato"));
    }

    public void testRemoveIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        foodStorage.removeIngredient(ingredient.getName());
        assertFalse(foodStorage.findIngredient("Tomato"));
    }

    public void testFindIngredient() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        assertTrue(foodStorage.findIngredient("Tomato"));
        assertFalse(foodStorage.findIngredient("Potato"));
    }

    public void testRemoveIngredientAmount() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        setInput("n");
        foodStorage.removeIngredientAmount(ingredient.getName(), 2);
        assertEquals(3.0, ingredient.getAmount());
    }

    public void testGetIngredientsSorted() {
        Ingredient ingredient1 = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        Ingredient ingredient2 = new Ingredient("Potato", 20, 10, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient1);
        foodStorage.addIngredient(ingredient2);
        foodStorage.getIngredientsSorted().forEach(PrintModel::print);
        String expectedOutput = "Total value of ingredients: " + (ingredient1.getPpu() * ingredient1.getAmount() + ingredient2.getPpu() * ingredient2.getAmount()) + "\r\n";
        assertTrue(expectedOutput.contains(outContent.toString()));
    }

    public void testPrintExpiredIngredients() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        Ingredient expiredIngredient = new Ingredient("Expired", 10, 5, pastDate, "pcs");
        foodStorage = new FoodStorage();
        foodStorage.addIngredient(expiredIngredient);
        foodStorage.printExpiredIngredients();
        String expectedOutput = ("--------------------") +
                (String.format("%-20s %-10.2f %-10s %-15s %-10.2f",
                        expiredIngredient.getName(),
                        expiredIngredient.getAmount(),
                        expiredIngredient.getUnit(),
                        (expiredIngredient.getExpDate() != null ? expiredIngredient.getExpDate().toString() : "N/A"),
                        expiredIngredient.getPrice())) +
                "EXPIRED" +
                "--------------------";

        assertTrue(expectedOutput.contains(outContent.toString()));
    }

    public void testPrintTotalValue() {
        Ingredient ingredient1 = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        Ingredient ingredient2 = new Ingredient("Potato", 20, 10, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient1);
        foodStorage.addIngredient(ingredient2);
        foodStorage.printTotalValue();
        String expectedOutput = "Total value of ingredients: " + (ingredient1.getPrice() + ingredient2.getPrice()) + "\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    public void testRemoveNonExistentIngredient() {
        foodStorage.removeIngredient("NonExistent");
        assertFalse(foodStorage.findIngredient("NonExistent"));
    }

    public void testRemoveIngredientAmountMoreThanExists() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now(), "pcs");
        foodStorage.addIngredient(ingredient);
        setInput("n");
        foodStorage.removeIngredientAmount(ingredient.getName(), 10);
        assertFalse(foodStorage.findIngredient("Tomato"));
    }

    public void testGetIngredientsSortedWithEmptyStorage() {
        List<Ingredient> sortedIngredients = foodStorage.getIngredientsSorted();
        assertTrue(sortedIngredients.isEmpty());
    }

    public void testPrintExpiredIngredientsWithNoExpired() {
        Ingredient ingredient = new Ingredient("Tomato", 10, 5, LocalDate.now().plusDays(1), "pcs");
        foodStorage.addIngredient(ingredient);
        foodStorage.printExpiredIngredients();
        String expectedOutput = "Total value of expired ingredients: 0.0\r\n";
        assertTrue(expectedOutput.contains(outContent.toString()));
    }

    public void testPrintTotalValueWithEmptyStorage() {
        foodStorage.printTotalValue();
        String expectedOutput = "Total value of ingredients: 0.0\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}