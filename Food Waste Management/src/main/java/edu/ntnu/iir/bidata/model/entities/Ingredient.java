package edu.ntnu.iir.bidata.model.entities;

import edu.ntnu.iir.bidata.controller.validator.ArgumentValidator;
import edu.ntnu.iir.bidata.view.PrintModel;

import java.util.Date;

/**
 * Represents an ingredient with a name, price, amount, expiration date, and unit.
 */
public class Ingredient {
    private final String name;
    private double price;
    private double ppu;
    private double amount;
    private Date expDate;
    private final String unit;

    /**
     * Constructs a new Ingredient with the specified name, price, amount, expiration date, and unit.
     *
     * @param name    the name of the ingredient
     * @param price   the price of the ingredient
     * @param amount  the amount of the ingredient
     * @param expDate the expiration date of the ingredient
     * @param unit    the unit of measurement for the ingredient
     */
    public Ingredient(String name, double price, double amount, Date expDate, String unit)
            throws IllegalArgumentException {
        ArgumentValidator.IngredientValidator(name, price, amount, unit, expDate);
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.expDate = expDate;
        this.unit = unit;
        this.ppu = price / amount;
    }

    /**
     * Constructs a new Ingredient with the specified name, price, amount, and unit.
     * This constructor will only be used for ingredients pertaining to @see Recipe,
     * as they do not have an expiry date.
     *
     * @param name   the name of the ingredient
     * @param price  the price of the ingredient
     * @param amount the amount of the ingredient
     * @param unit   the unit of measurement for the ingredient
     */
    public Ingredient(String name, double price, double amount, String unit) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.expDate = null;
        this.unit = unit;
        this.ppu = price / amount;
    }

    /**
     * Returns the unit of measurement for the ingredient.
     *
     * @return the unit of measurement
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Returns the amount of the ingredient.
     *
     * @return the amount of the ingredient
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the name of the ingredient.
     *
     * @return the name of the ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the ingredient.
     *
     * @return the price of the ingredient
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the price per unit of the ingredient.
     *
     * @return the price per unit of the ingredient
     */
    public double getPpu() {
        return ppu;
    }

    /**
     * Returns the expiration date of the ingredient.
     *
     * @return the expiration date of the ingredient
     */
    public Date getExpDate() {
        return expDate;
    }

    /**
     * Sets the amount of the ingredient.
     *
     * @param amount the new amount of the ingredient
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets the expiration date of the ingredient.
     *
     * @param expDate the new expiration date of the ingredient
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    /**
     * Sets the price of the ingredient and updates the price per unit.
     *
     * @param price the new price of the ingredient
     */
    public void setPrice(double price) {
        this.price = price;
        this.ppu = price / amount;
    }

    /**
     * Adds the specified amount to the ingredient and updates the price.
     *
     * @param amount the amount to add
     */
    public void addAmount(double amount) {
        PrintModel.print("Adding " + amount + " to " + this.name);
        PrintModel.print("Presuming price is the same per unit (" + ppu + ")");

        this.amount += amount;
        this.price += amount * this.ppu;
    }
}