import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import edu.ntnu.iir.bidata.model.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class IngredientTest {
    private Ingredient ingredient;
    private String name;
    private double price;
    private LocalDate expiryDate;
    private double amount;
    private String unit;

    @BeforeEach
    void testInit() {
        name = "Milk";
        price = 9.95;
        expiryDate = LocalDate.now();
        amount = 2.0;
        unit = "L";

        ingredient = new Ingredient(name, price, amount, expiryDate, unit);
    }

    @Test
    void testConstructors() {
        assertEquals(name, ingredient.getName(), "Names should be equal");
        assertEquals(price, ingredient.getPrice(), "Price should be equal");
        assertEquals(expiryDate, ingredient.getExpiryDate(), "Dates should be equal");
        assertEquals(amount, ingredient.getAmount(), "Amount should be equal");
        assertEquals(unit, ingredient.getUnit(), "Unit should be equal");
    }

    @Test
    void testNameThrows() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient("", price, amount, expiryDate, unit),
                "IllegalArgumentException should be thrown if name is empty"
        );
        assertEquals(
                "Name cannot be null or empty.", exception1.getMessage(), "Messages should match"
        );
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(null, price, amount, expiryDate, unit),
                "IllegalArgumentException should be thrown if name is null"
        );
        assertEquals(
                "Name cannot be null or empty.", exception2.getMessage(), "Messages should match"
        );
    }

    @Test
    void testPriceThrows() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, -1, amount, expiryDate, unit),
                "IllegalArgumentException should be thrown if price is negative or zero"
        );
        assertEquals("Price cannot be 0 or negative.", e.getMessage(), "Messages should match");
    }

    @Test
    void testAmountThrows() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, 0, expiryDate, unit),
                "IllegalArgumentException should be thrown if date is null"
        );
        assertEquals("Date cannot be null.", e.getMessage(), "Messages should match");
    }

    @Test
    void testDateThrows() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, amount, null, unit),
                "IllegalArgumentException should be thrown if amount is negative or zero"
        );
        assertEquals("Amount cannot be 0 or negative.", e.getMessage(), "Messages should match");
    }

    @Test
    void testUnitThrows() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, amount, expiryDate, ""),
                "IllegalArgumentException should be thrown if unit is empty"
        );
        assertEquals("Unit cannot be null or empty.", e1.getMessage(), "Messages should match");
        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, amount, expiryDate, null),
                "IllegalArgumentException should be thrown if unit is null"
        );
        assertEquals("Unit cannot be null or empty.", e2.getMessage(), "Messages should match");
    }

    @Test
    void testSetAmount() {
        double newAmount = 5.0;
        ingredient.setAmount(newAmount);
        assertEquals(ingredient.getAmount(), newAmount, "Amount should match");
    }

    @Test
    void testSetPrice() {
        double newPrice = 5.0;
        ingredient.setPrice(newPrice);
        assertEquals(ingredient.getPrice(), newPrice, "Price should match");
    }

    @Test
    void testSetAmountThrows() {
        double newAmount = 0;
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> ingredient.setAmount(newAmount),
                "IllegalArgumentException should be thrown if amount is negative or zero"
        );
        assertEquals("Amount should not be negative or zero", e.getMessage(), "Messages should match");
    }

    @Test
    void testSetPriceThrows() {
        double newPrice = -1;
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> ingredient.setPrice(newPrice),
                "IllegalArgumentException should be thrown if price is negative or zero"
        );
        assertEquals("Price should not be negative or zero", e.getMessage(), "Messages should match");
    }
}