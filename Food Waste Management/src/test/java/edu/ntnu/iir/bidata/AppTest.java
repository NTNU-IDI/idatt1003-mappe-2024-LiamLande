package edu.ntnu.iir.bidata;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.iir.bidata.controller.Initializer;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.ntnu.iir.bidata.controller.TUIController;

public class AppTest {

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
    public void testAppStart() {

        setInput("n\n3");
        Initializer init = new Initializer(controller);
        init.init();
        controller.start();

        String expectedMenu = "1. Food storage\r\n2. Cookbook\r\n3. Exit\r\n";

        assertTrue(outContent.toString().contains(expectedMenu));
    }

    @Test
    void testAppMain() {
        ByteArrayInputStream in = new ByteArrayInputStream("n\n3".getBytes());
        System.setIn(in);
        App.main(new String[0]);
        assertTrue(outContent.toString().contains("1. Food storage\r\n2. Cookbook\r\n3. Exit\r\n"));
    }
}