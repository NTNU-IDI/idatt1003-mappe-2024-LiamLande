package edu.ntnu.iir.bidata.controller.validator;

import edu.ntnu.iir.bidata.controller.TUIController;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public final class InputValidator {
    TUIController controller;
    private static Scanner S;

    public InputValidator(Scanner scanner, TUIController controller) {
        InputValidator.S = scanner;
        this.controller = controller;
    }

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

    public static String readString(String prompt) {
        PrintModel.print(prompt);
        return S.nextLine();
    }

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

    public static ArrayList<String> readList(String prompt) {
        PrintModel.print(prompt);
        String input = S.nextLine();
        return new ArrayList<>(Arrays.asList(input.split(",")));
    }

    public static Boolean readBoolean() {
        PrintModel.print("(y/n)");

        String input = S.nextLine();
        if (!input.equals("y") && !input.equals("n")) {
            PrintModel.print("Invalid input, try again");
            return readBoolean();
        }
        return input.equals("y");
    }

    public static Boolean readBoolean(String prompt) {
        PrintModel.print(prompt);
        return readBoolean();
    }

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
