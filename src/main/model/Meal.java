package model;

import java.util.ArrayList;

// Represents a meal having a name, number of calories, and a list of macronutrients.
public class Meal {

    private String name;                      // name of meal
    private int calories;                     // number of calories
    private ArrayList<Macronutrient> macronutrients; // list of macronutrients

    // REQUIRES: name.length() > 0 && calories > 0 && macronutrients != null
    // EFFECTS:  creates a meal with this.name is set to name; this.calories 
    //           is set to calories; this.macronutrients is set to macronutrients.
    public Meal(String name, int calories, ArrayList<String> macronutrients) {
        // stub
    }

    public String getName() {
        return "";
    }

    public int getCalories() {
        return 0;
    }

    public void setName(String name) {
        // stub
    }

    public void setCalories(int calories) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS:  adds macronutrient m to macronutrients
    public void addMacronutrient(Macronutrient m) {
        // stub
    }

    // REQUIRES: s =! ""
    // MODIFIES: this
    // EFFECTS:  removes macronutrient that has the same name as s
    public void removeMacronutrient(String s) {
        // stub
    }
}
