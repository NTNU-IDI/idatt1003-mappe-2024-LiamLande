package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.TUI.TUI;
import edu.ntnu.iir.bidata.entities.Pair;
import edu.ntnu.iir.bidata.registers.Cookbook;
import edu.ntnu.iir.bidata.registers.FoodStorage;

public class App {
    public static void main(String[] args) {
        TUI TUI = new TUI();
        Pair a = TUI.init();
        TUI.start((FoodStorage) a.getFirst(), (Cookbook) a.getSecond());
    }
}
