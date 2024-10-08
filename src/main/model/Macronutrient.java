package model;

// represents a macronutrient of either protein, fat, carbohydrate, or fibre with a String 
// saying which macronutrient it is and an int as the amount in grams (g) present in the meal
public class Macronutrient {
    private String macronutrient;
    private int amount;

    // REQUIRES: macronutrient is one of: protein, carbohydrate, fat, fibre
    // and int >= 0
    public Macronutrient(String macronutrient, int amount) {
        // stub
    }

    public String getMacronutrient() {
        return "";
    }

    public int getAmount() {
        return 0;
    }

    public void setMacronutrient(String macronutrient) {
        // stub
    }

    public void setAmount(int amount) {
        // stub
    }
}
