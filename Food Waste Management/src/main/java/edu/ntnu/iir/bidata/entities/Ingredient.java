package edu.ntnu.iir.bidata.entities;

import java.util.Date;

// Ingredient class, used to store information about ingredients
public class Ingredient {
    private String name;
    private int price;
    private int ppu;
    private int amount;
    private Date expDate;

    // Constructor for Ingredient, takes name, price, amount and expiration date
    public Ingredient(String name, int price, int amount, Date expDate) {
        this.name = name;
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.amount = amount;
        
        this.expDate = expDate;
        this.ppu = price / amount;
    }

    //Get methods
    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPpu() {
        return ppu;
    }

    public Date getExpDate() {
        return expDate;
    }

    //set methods
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
        this.ppu = price / amount;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    //toString method, easier for debugging
    @Override
    public String toString() {
        return "Ingredient [amount=" + amount + ", name=" + name + ", price=" + price + ", expDate=" + expDate + "]";
    }

    //addAmount method adding amount and updating PPU
    public void addAmount(int amount) {
        System.out.println("Adding " + amount + " to " + this.name);
        System.out.println("Presuming price is the same per unit (" + ppu + ")");
        this.amount += amount;
        this.price += amount * this.ppu;
    }
}
