package edu.ntnu.iir.bidata;

import edu.ntnu.iir.bidata.entities.Recipe;

import java.util.ArrayList;

public class Cookbook {
    String Name;
    String Description;

    ArrayList<Recipe> Recipes;

    public Cookbook(String name, String description) {
        Name = name;
        Description = description;
        Recipes = new ArrayList<Recipe>();
    }
    
}
