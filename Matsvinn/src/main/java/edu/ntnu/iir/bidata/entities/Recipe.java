package edu.ntnu.iir.bidata.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe {
    String Name;
    String Description;
    String Instructions;
    HashMap<String, Integer> Ingredients;

    public Recipe(String name, String description, String instructions) {
        Name = name;
        Description = description;
        Instructions = instructions;
        Ingredients = new HashMap<String, Integer>();
    }

    public Recipe() {

    }

    public String getDescription() {
        return Description;
    }

    public String getInstructions() {
        return Instructions;
    }

    public String getName() {
        return Name;
    }

    public void addIngredient(String name, int amount) {
        Ingredients.put(name, amount);
    }


}
