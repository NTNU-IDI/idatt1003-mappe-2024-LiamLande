package edu.ntnu.iir.bidata.controller.validator;

import edu.ntnu.iir.bidata.controller.TUIController;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.time.LocalDate;
import java.util.*;

/**
 * The InputValidator class provides static methods for validating and reading user input.
 */
public final class InputValidator {
    TUIController controller;
    private static Scanner S;

    /**
     * Constructs an InputValidator with the specified Scanner and TUIController.
     *
     * @param scanner    the Scanner to be used for reading input
     * @param controller the TUIController to be used for validation
     */
    public InputValidator(Scanner scanner, TUIController controller) {
        InputValidator.S = scanner;
        this.controller = controller;
    }

    /**
     * Reads an integer from the user with a prompt and an upper bound.
     *
     * @param prompt the prompt to display to the user
     * @param bound  the upper bound for the integer
     * @return the integer input by the user
     */
    public static int readInt(String prompt, int bound) {
        PrintModel.print(prompt);
        int a;
        try {
            a = Integer.parseInt(S.nextLine());
        } catch (NumberFormatException e) {
            PrintModel.print("Invalid input, try again");
            return readInt(prompt, bound);
        }
        if (bound == 0) {
            return a;
        }
        if (a > bound) {
            PrintModel.print("Number too high, try again");
            return readInt(prompt, bound);
        }
        if (a < 0) {
            PrintModel.print("Number too low, try again");
            return readInt(prompt, bound);
        }

        return a;
    }


    /**
     * Reads a double from the user with a prompt and an upper bound.
     *
     * @param prompt the prompt to display to the user
     * @param bound  the upper bound for the double
     * @return the double input by the user
     */
    public static double readDouble(String prompt, double bound) {
        PrintModel.print(prompt);
        double a;
        try {
            a = Double.parseDouble(S.nextLine());
        } catch (NumberFormatException e) {
            PrintModel.print("Invalid input, try again");
            return readDouble(prompt, bound);
        }
        if (a > bound && bound != 0) {
            PrintModel.print("Number too high, try again");
            return readDouble(prompt, bound);
        }
        if (a < 0) {
            PrintModel.print("Number too low, try again");
            return readDouble(prompt, bound);
        }
        return a;
    }

    /**
     * Reads a string from the user with a prompt.
     *
     * @param prompt the prompt to display to the user
     * @return the string input by the user
     */
    public static String readString(String prompt) {
        PrintModel.print(prompt);
        String string = S.nextLine();
        try {
            ArgumentValidator.StringValidator(string);
        } catch (IllegalArgumentException e) {
            PrintModel.print(e.getMessage());
            return readString(prompt);
        }
        return string;
    }

    /**
     * Reads a date from the user with a prompt.
     *
     * @param prompt the prompt to display to the user
     * @return LocalDate the date input by the user
     */
    public static LocalDate readDate(String prompt) {
        PrintModel.print(prompt);
        int year = InputValidator.readInt("Enter year:", 0);
        int month = InputValidator.readInt("Enter month:", 12);
        int day = InputValidator.readInt("Enter day:", 31);
        LocalDate FinalDate;
        try {
            FinalDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            PrintModel.print("Invalid date, try again");
            return readDate(prompt);
        }
        return FinalDate;
    }

    /**
     * Reads a list of strings from the user with a prompt.
     *
     * @param prompt the prompt to display to the user
     * @return a list of strings input by the user
     */
    public static ArrayList<String> readList(String prompt) {
        PrintModel.print(prompt);
        PrintModel.print("Separate, values, with, a, comma");

        String input = S.nextLine();
        ArrayList<String> List = new ArrayList<>(Arrays.asList(input.split(",")));
        if (List.isEmpty()) {
            PrintModel.print("Invalid input, try again");
            return readList(prompt);
        }
        return List;
    }

    /**
     * Reads a boolean value from the user.
     *
     * @return the boolean value input by the user
     */
    public static Boolean readBoolean() {
        PrintModel.print("(y/n)");

        String input = S.nextLine();
        if (!input.equals("y") && !input.equals("n")) {
            PrintModel.print("Invalid input, try again");
            return readBoolean();
        }
        return input.equals("y");
    }

    /**
     * Reads a boolean value from the user with a prompt.
     *
     * @param prompt the prompt to display to the user
     * @return the boolean value input by the user
     */
    public static Boolean readBoolean(String prompt) {
        PrintModel.print(prompt);
        return readBoolean();
    }

    /**
     * Reads a unit of measurement from the user with a prompt.
     *
     * @param prompt the prompt to display to the user
     * @return the unit of measurement input by the user
     */
    public static String readUnit(String prompt) {
        PrintModel.print(prompt);
        PrintModel.print("Available units: g, ml or pcs");
        String input = S.nextLine();
        if (!input.equals("g") && !input.equals("ml") && !input.equals("pcs")) {
            PrintModel.print("Invalid input, try again");
            return readUnit(prompt);
        }
        return input;
    }
}