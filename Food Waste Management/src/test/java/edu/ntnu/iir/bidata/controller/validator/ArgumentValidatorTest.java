package edu.ntnu.iir.bidata.controller.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentValidatorTest {
    @Test
    void testConstructor() {
        assertThrows(UnsupportedOperationException.class, ArgumentValidator::new);
    }

    @Test
    void testDouble() {
        assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.AmountValidator(-1.0));
    }

    @Test
    void testString() {
        assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.StringValidator(""));
    }

    @Test
    void testPrice() {
        assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.PriceValidator(-1.0));
    }

    @Test
    void testIngredientValidator() {
        assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.IngredientValidator("", -1.0, -1.0, "", null));
    }

    @Test
    void testExpDateValidator() {
        assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.ExpDateValidator(null));
    }

    @Test
    void testRecipeIngredientValidator() {
        assertThrows(IllegalArgumentException.class, () -> ArgumentValidator.RecipeIngredientValidator("", -1.0, -1.0, ""));
    }

    
}
