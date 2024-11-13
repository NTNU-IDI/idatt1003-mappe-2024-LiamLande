package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.TUI.TUIController;
import edu.ntnu.iir.bidata.TUI.TUIView;
import edu.ntnu.iir.bidata.entities.Pair;
import edu.ntnu.iir.bidata.registers.Cookbook;
import edu.ntnu.iir.bidata.registers.FoodStorage;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TUIView view = new TUIView();
        Scanner scanner = new Scanner(System.in);
        TUIController Controller = new TUIController(scanner, view);
        Pair a = Controller.init();
        Controller.start((FoodStorage) a.getFirst(), (Cookbook) a.getSecond());
    }
}
//TODO: JAVADOC COMMENTS
