package edu.ntnu.iir.bidata;
import java.util.ArrayList;
import edu.ntnu.iir.bidata.entities.Ingredient;
import edu.ntnu.iir.bidata.TUI.TUI;


public class FoodStorage {
    private ArrayList<Ingredient> ingredients;
    private TUI a;

    public FoodStorage(TUI a) {
        ingredients = new ArrayList<Ingredient>();
        this.a = a;
    }
    public void addIngredient(){
        String name = a.readString("Enter name of ingredient");
        int price = a.readInt("Enter price of ingredient");
        int amount = a.readInt("Enter amount of ingredient (Liter if liquid, grams if solid, pieces if other)");
        int expDate = a.readDate("Enter expiration date of ingredient");
        if (this.findIngredient(name)) {
            System.out.println("Ingredient already exists, adding amount");
            System.out.println("Do you want to add more to the same ingredient? \n Old expiry date will be removed and price will be assumed as same price per unit");
            if (a.readBoolean()) {
                for (int i = 0; i < ingredients.size(); i++) {
                    if (ingredients.get(i).getName().equals(name)) {
                        ingredients.get(i).addAmount(amount);
                        return;
                    }
                }
            }
            
        }
        ingredients.add(new Ingredient(name, price, amount, expDate));
    }

    public void removeIngredient(){
        String name = a.readString("Enter name of ingredient to remove");
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().equals(name)) {
                ingredients.remove(i);
                return;
            }
        }
        System.out.println("Ingredient not found");
    }
    public Boolean findIngredient(String name){
        if (name == null) {
            name = a.readString("Enter name of ingredient to find");
        }
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().equals(name)) {
                System.out.println((ingredients.get(i)).toString());
                return true;
            }
        }
        System.out.println("Ingredient not found");
        return false;
    }
    public void removeIngredientAmount(){
        String name = a.readString("Enter name of ingredient to remove amount from");
        int amount = a.readInt("Enter amount to remove");
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().equals(name)) {
                ingredients.get(i).setAmount(ingredients.get(i).getAmount() - amount);
                System.out.println("Do you want to update price accordingly? \n New price will be assumed as same price per unit");
                if (a.readBoolean()) {
                    ingredients.get(i).setPrice(ingredients.get(i).getPrice() - amount * ingredients.get(i).getPrice() / ingredients.get(i).getAmount());
                }
                return;
            }
        }
        System.out.println("Ingredient not found");
    }
    public void printIngredients(){
        System.out.println("Ingredients:");
        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println(ingredients.get(i).toString());
        }
    }
    public void printExpiredIngredients(){
        int total = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getExpDate() < 20241013) {
                System.out.println(ingredients.get(i).toString());
                total += ingredients.get(i).getPrice();
            }
        }
        System.out.println("Total value of expired ingredients: " + total);
    }
    public void printTotalValue(){
        int total = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            total += ingredients.get(i).getPrice();
        }
        System.out.println("Total value of ingredients: " + total);
    }

}
