package edu.ntnu.iir.bidata.registers;

import java.util.ArrayList;
import java.util.Date;

import edu.ntnu.iir.bidata.entities.Ingredient;
import edu.ntnu.iir.bidata.TUI.TUI;


public class FoodStorage {
    private ArrayList<Ingredient> ingredients;
    private final TUI a;

    public FoodStorage(TUI a) {
        ingredients = new ArrayList<Ingredient>();
        this.a = a;
    }

    public void addIngredient() {
        String name = a.readString("Enter name of ingredient");
        int price = a.readInt("Enter price of ingredient", 0);
        int amount = a.readInt("Enter amount of ingredient (Liter if liquid, grams if solid, pieces if other)", 0);
        Date expDate = a.readDate("Enter expiration date of ingredient");
        if (this.findIngredient(name)) {
            System.out.println("Ingredient already exists, adding amount");
            System.out.println("Do you want to add more to the same ingredient? \n Old expiry date will be removed and price will be assumed as same price per unit");
            if (a.readBoolean()) {
                for (Ingredient ingredient : ingredients) {
                    if (ingredient.getName().equals(name)) {
                        ingredient.addAmount(amount);
                        return;
                    }
                }
            }

        }
        ingredients.add(new Ingredient(name, price, amount, expDate));
        System.out.println("Ingredient added");
    }

    public void addIngredient(Ingredient a) {
        ingredients.add(a);
    }

    public void removeIngredient(String name) {
        if (name == null) {
            name = a.readString("Enter name of ingredient to remove");
        }
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().equals(name)) {
                ingredients.remove(i);
                return;
            }
        }
        System.out.println("Ingredient not found");
    }

    public Boolean findIngredient(String name) {
        if (name == null) {
            name = a.readString("Enter name of ingredient to find");

        } else {
            System.out.println("Checking for ingredient: " + name);
        }
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                System.out.println(ingredient.toString());
                return true;
            }
        }

        System.out.println("Ingredient not found");
        return false;
    }

    public void removeIngredientAmount() {
        String name = a.readString("Enter name of ingredient to remove amount from");
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                int amount = a.readInt("Enter amount to remove", ingredient.getAmount());
                ingredient.setAmount(ingredient.getAmount() - amount);
                System.out.println("Do you want to update price accordingly? \n New price will be assumed as same price per unit");
                if (a.readBoolean()) {
                    ingredient.setPrice(ingredient.getPrice() - amount * ingredient.getPrice() / ingredient.getAmount());
                }
                return;
            }
        }
        System.out.println("Ingredient not found");
    }

    public void printIngredients() {
        System.out.println("Ingredients:");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.toString());
        }
        this.printTotalValue();
    }

    public void printExpiredIngredients() {
        int total = 0;
        for (Ingredient ingredient : ingredients) {
            if (new Date().after(ingredient.getExpDate())) {
                System.out.println(ingredient.toString());
                total += ingredient.getPrice();
            }
        }
        System.out.println("Total value of expired ingredients: " + total);
    }

    public void printTotalValue() {
        int total = 0;
        for (Ingredient ingredient : ingredients) {
            total += ingredient.getPrice();
        }
        System.out.println("Total value of ingredients: " + total);
    }

}
