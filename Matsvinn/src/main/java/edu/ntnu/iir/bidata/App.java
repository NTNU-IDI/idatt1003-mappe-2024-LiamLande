package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.TUI.TUI;

public class App {
    public static void main(String[] args) {
        TUI TUI = new TUI();
        FoodStorage mainStorage = TUI.init();
        TUI.start(mainStorage);
    }
}
