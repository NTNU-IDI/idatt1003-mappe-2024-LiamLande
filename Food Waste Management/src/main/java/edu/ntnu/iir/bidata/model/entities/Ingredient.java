package edu.ntnu.iir.bidata.model.entities;

import java.util.Date;

// Ingredient class, used to store information about ingredients
public class Ingredient {
    private final String name;
    private double price;
    private double ppu;
    private double amount;
    private Date expDate;
    private final String unit;

    // Constructor for Ingredient, takes name, price, amount and expiration date
    public Ingredient(String name, double price, double amount, Date expDate, String unit) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.expDate = expDate;
        this.unit = unit;
        this.ppu = price / amount;
    }

    public String getUnit() {
        return unit;
    }

    //Get methods
    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getPpu() {
        return ppu;
    }

    public Date getExpDate() {
        return expDate;
    }

    //set methods
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public void setPrice(double price) {
        this.price = price;
        this.ppu = price / amount;
    }


    //addAmount method adding amount and updating PPU
    public void addAmount(double amount) {
        System.out.println("Adding " + amount + " to " + this.name);
        System.out.println("Presuming price is the same per unit (" + ppu + ")");

        this.amount += amount;
        this.price += amount * this.ppu;
    }
}
