package model;

import org.json.JSONObject;

import persistence.Writable;

// represents a macronutrient of either protein, fat, carbohydrate, or fibre with a String 
// saying which macronutrient it is and an int as the amount in grams (g) present in the meal
public class Macronutrient implements Writable {
    private String name;
    private int amount;

    // REQUIRES: macronutrient is one of: protein, carbohydrate, fat, fibre
    // and int >= 0
    public Macronutrient(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setName(String macronutrient) {
        this.name = macronutrient;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("amount", this.amount);
        return json;
    }
}
