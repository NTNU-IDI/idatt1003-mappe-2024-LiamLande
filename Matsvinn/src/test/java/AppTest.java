import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import junit.framework.TestCase;
import edu.ntnu.iir.bidata.FoodStorage;
import edu.ntnu.iir.bidata.TUI.TUI;


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

    public void testAppInitialization() {
        TUI tui = new TUI();
        FoodStorage mainStorage = tui.init();
        assertNotNull(mainStorage);
    }

    public void testAppStart() {
        String simulatedInput = "3\n"; // Simulate user input to exit the menu
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inContent);

        TUI tui = new TUI(scanner);
        FoodStorage mainStorage = tui.init();
        tui.start(mainStorage);

        String expectedMenu = "1. Food storage management\r\n2. Recipes, and other extra features\r\n3. Exit";

        // Print the captured output for debugging

        assertTrue(outContent.toString().contains(expectedMenu));
    }
}