package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.controller.Initializer;
import edu.ntnu.iir.bidata.controller.TUIController;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;

import java.util.Scanner;

/**
 * <h1>App</h1>
 * The App class serves as the entry point for the application.
 * <p>
 * Initializes the application and starts the user interface.
 * </p>
 *
 * @Since 0.1
 * @Version 2.0
 * @Author Liam Lande
 */
public class App {
    /**
     * The main method initializes the
     * application and starts the user interface.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        TUIController Controller = new TUIController();
        Initializer Initializer = new Initializer(Controller);
        Scanner scanner = new Scanner(System.in);
        InputValidator.setScanner(scanner);
        Initializer.init();
        Controller.start();
    }
}
