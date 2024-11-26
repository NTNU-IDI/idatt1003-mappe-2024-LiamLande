import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.iir.bidata.controller.Initializer;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;
import org.junit.jupiter.api.Test;
import edu.ntnu.iir.bidata.controller.TUIController;

public class AppTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;


    @Test
    public void testAppStart() {
        String simulatedInput = "n\n3"; // Simulate user input to exit the menu
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inContent);
        TUIController controller = new TUIController();
        new InputValidator(scanner, controller);
        Initializer init = new Initializer(controller);
        init.init();
        controller.start();

        String expectedMenu = "\n" +
                "  ______                 _   _____  _                                  \n" +
                " |  ____|               | | / ____|| |                                 \n" +
                " | |__  ___    ___    __| || (___  | |_  ___   _ __  __ _   __ _   ___ \n" +
                " |  __|/ _ \\  / _ \\  / _` | \\___ \\ | __|/ _ \\ | '__|/ _` | / _` | / _ \\\n" +
                " | |  | (_) || (_) || (_| | ____) || |_| (_) || |  | (_| || (_| ||  __/\n" +
                " |_|   \\___/  \\___/  \\__,_||_____/  \\__|\\___/ |_|   \\__,_| \\__, | \\___|\n" +
                "                                                            __/ |      \n" +
                "                                                           |___/       \n" +
                "\n" +
                "Welcome to the food storage system\n" +
                "... Initialising variables ...\n" +
                "Do you want to load test data?\n" +
                "(y/n)\n" +
                "No test data loaded\n" +
                "1. Food storage\n" +
                "2. Cookbook\n" +
                "3. Exit\n" +
                "(1,2,3)\n";


        assertTrue(outContent.toString().contains(expectedMenu));
    }
}