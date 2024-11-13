package edu.ntnu.iir.bidata.TUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TUIView {
    public void printWelcome() {
        this.print("\n" +
                "  ______                 _   _____  _                                  \n" +
                " |  ____|               | | / ____|| |                                 \n" +
                " | |__  ___    ___    __| || (___  | |_  ___   _ __  __ _   __ _   ___ \n" +
                " |  __|/ _ \\  / _ \\  / _` | \\___ \\ | __|/ _ \\ | '__|/ _` | / _` | / _ \\\n" +
                " | |  | (_) || (_) || (_| | ____) || |_| (_) || |  | (_| || (_| ||  __/\n" +
                " |_|   \\___/  \\___/  \\__,_||_____/  \\__|\\___/ |_|   \\__,_| \\__, | \\___|\n" +
                "                                                            __/ |      \n" +
                "                                                           |___/       \n");
        this.print("Welcome to the food storage system");
        this.print("... Initialising variables ...");
        this.print("Do you want to load test data?");
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void print(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.print(dateFormat.format(date));
    }

    public void storageMenu() {
        this.print("1.Add Ingredient to your food storage");
        this.print("2.Find Ingredient in your food storage");
        this.print("3.Remove amount or whole ingredient");
        this.print("4.Print all ingredients");
        this.print("5.Print all expired ingredients");
        this.print("6.Back to main menu");
    }

    public void bookMenu() {
        this.print("Welcome to the Cookbook menu");
        this.print("1. Add recipe");
        this.print("2. Remove recipe");
        this.print("3. Find recipe");
        this.print("4. Print all recipes");
        this.print("5. Recommend me a recipe");
        this.print("6. Back to main menu");
    }

    public void startMenu() {
        this.print("1. Food storage");
        this.print("2. Cookbook");
        this.print("3. Exit");
    }
}
