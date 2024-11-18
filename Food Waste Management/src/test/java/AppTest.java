import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ntnu.iir.bidata.controller.Initializer;
import junit.framework.TestCase;
import edu.ntnu.iir.bidata.controller.registers.FoodStorage;
import edu.ntnu.iir.bidata.controller.TUIController;
import edu.ntnu.iir.bidata.controller.registers.Cookbook;

public class AppTest extends TestCase {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    public AppTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.setOut(originalOut);
    }

//    public void testAppInitialization() {
//        // Add InputValidator Scanner scanner = new Scanner(System.in);
//        TUIController controller = new TUIController();
//        Initializer init = new Initializer(controller);
//        init.init();
//        assertNotNull(mainStorage);
//        assertNotNull(mainCookbook);
//    }

    public void testAppStart() {
        String simulatedInput = "3\n"; // Simulate user input to exit the menu
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inContent);

        TUIController controller = new TUIController();
        Initializer init = new Initializer(controller);
        init.init();
        controller.start();

        String expectedMenu = "1. Food storage management\r\n2. Recipes, and other extra features\r\n3. Exit";

        // Print the captured output for debugging

        assertTrue(outContent.toString().contains(expectedMenu));
    }
}