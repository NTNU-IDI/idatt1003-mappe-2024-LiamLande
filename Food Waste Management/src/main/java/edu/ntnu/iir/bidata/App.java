package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.controller.Initializer;
import edu.ntnu.iir.bidata.controller.TUIController;
import edu.ntnu.iir.bidata.controller.validator.InputValidator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TUIController Controller = new TUIController();
        Initializer Initializer = new Initializer(Controller);
        Scanner scanner = new Scanner(System.in);
        new InputValidator(scanner, Controller);
        Initializer.init();
        Controller.start();
    }
}
//TODO: JAVADOC COMMENTS
