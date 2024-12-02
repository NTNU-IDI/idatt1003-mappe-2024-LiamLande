package edu.ntnu.iir.bidata.controller.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentValidatorTest {
    @Test
    void testConstructor() {
        assertThrows(UnsupportedOperationException.class, ArgumentValidator::new);
    }
}
