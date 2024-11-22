package edu.ntnu.iir.bidata.controller.validator;

import edu.ntnu.iir.bidata.controller.TUIController;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     * Reads a map of ingredient-amount pairs from the user.
     *
     * @param prompt the prompt to display to the user
     * @return a map of ingredient names to their amounts
     */
    public static Map<String, Integer> readMap(String prompt) {
        PrintModel.print(prompt);
        PrintModel.print("Enter Ingredient:Amount pairs separated by commas:");
        Map<String, Integer> map = new HashMap<>();
        String input = S.nextLine();
        String[] pairs = input.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            map.put(keyValue[0], Integer.parseInt(keyValue[1]));
        }
        return map;
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
        if (bound == 0) {
            return a;
        }
        if (a > bound) {
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
        return S.nextLine();
    }

    /**
     * Reads a date from the user with a prompt.
     *
     * @param prompt the prompt to display to the user
     * @return the date input by the user
     */
    public static Date readDate(String prompt) {
        PrintModel.print(prompt + " (yyyy-MM-dd)");
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String input = S.nextLine();

        System.out.print(input + " Parses as ");
        Date t;
        try {
            t = ft.parse(input);
            PrintModel.print(t);
        } catch (ParseException e) {
            PrintModel.print("Unparseable using " + ft);
            t = readDate("Try again, remember the format -");
        }
        return t;
    }

    /**
     * Reads a list of strings from the user with a prompt.
     *
     * @param prompt the prompt to display to the user
     * @return a list of strings input by the user
     */
    public static ArrayList<String> readList(String prompt) {
        PrintModel.print(prompt);
        String input = S.nextLine();
        return new ArrayList<>(Arrays.asList(input.split(",")));
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
        PrintModel.print("Available units: g, l or pcs");
        String input = S.nextLine();
        if (!input.equals("g") && !input.equals("kg") && !input.equals("l") && !input.equals("pcs")) {
            PrintModel.print("Invalid input, try again");
            return readUnit(prompt);
        }
        return input;
    }
}