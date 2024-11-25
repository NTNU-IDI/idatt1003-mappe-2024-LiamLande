import edu.ntnu.iir.bidata.model.Ingredient;
import junit.framework.TestCase;

import java.time.LocalDate;

public class IngredientTest extends TestCase {

    public IngredientTest(String testName) {
        super(testName);
    }

    public void testAddIngredientWithLegalValues() {
        Ingredient ingredient = new Ingredient("Tomato", 10.0, 5, LocalDate.now(), "pcs");
        assertEquals("Tomato", ingredient.getName());
        assertEquals(10.0, ingredient.getAmount());
        assertEquals(5, ingredient.getPrice());
        assertEquals("pcs", ingredient.getUnit());
    }

    public void testAddIngredientWithIllegalValues() {
        try {
            new Ingredient("", -10, -5, LocalDate.now(), "a");
            fail("Expected IllegalArgumentException for illegal values");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    public void testAddIngredientWithNullName() {
        try {
            new Ingredient(null, 10.0, 5, LocalDate.now(), "pcs");
            fail("Expected IllegalArgumentException for null name");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    public void testAddIngredientWithNullUnit() {
        try {
            new Ingredient("Tomato", 10.0, 5, LocalDate.now(), null);
            fail("Expected IllegalArgumentException for null unit");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}