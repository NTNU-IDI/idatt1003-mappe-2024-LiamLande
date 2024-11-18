package edu.ntnu.iir.bidata.controller.registers;

import java.util.ArrayList;
import java.util.Date;

import edu.ntnu.iir.bidata.model.entities.Ingredient;
import edu.ntnu.iir.bidata.view.PrintModel;


public class FoodStorage {
    private final ArrayList<Ingredient> ingredients;

    public FoodStorage() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient a) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(a.getName())) {
                ingredient.setAmount(ingredient.getAmount() + a.getAmount());
                ingredient.setPrice(ingredient.getPrice() + a.getPrice());
                ingredient.setExpDate(a.getExpDate());
                return;
            }
        }

        ingredients.add(a);
    }

    public void removeIngredient(String name) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().equals(name)) {
                ingredients.remove(i);
                return;
            }
        }
        PrintModel.print("Ingredient not found");
    }

    public Ingredient getIngredient(String Name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(Name)) {
                return ingredient;
            }
        }
        return null;
    }

    public Boolean findIngredient(String name) {

        PrintModel.print("Checking for ingredient: " + name);

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                PrintModel.print(ingredient);
                return true;
            }
        }

        PrintModel.print("Ingredient not found");
        return false;
    }

    public void removeIngredientAmount(String name, double amount, boolean updatePrice) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                ingredient.setAmount(ingredient.getAmount() - amount);
                if (updatePrice) {
                    ingredient.setPrice(ingredient.getPrice() - amount * ingredient.getPrice() / ingredient.getAmount());
                }
                return;
            }
        }
        PrintModel.print("Ingredient not found");
    }

    public void printIngredients() {
        for (Ingredient ingredient : ingredients) {
            PrintModel.print(ingredient);
        }
    }

    public void printExpiredIngredients() {
        double total = 0;
        for (Ingredient ingredient : ingredients) {
            if (new Date().after(ingredient.getExpDate())) {
                PrintModel.print(ingredient);
                total += ingredient.getPrice();
            }
        }
        PrintModel.print("Total value of expired ingredients: " + total);
    }

    public void printTotalValue() {
        double total = 0;
        for (Ingredient ingredient : ingredients) {
            total += ingredient.getPrice();
        }
        PrintModel.print("Total value of ingredients: " + total);
    }

}
