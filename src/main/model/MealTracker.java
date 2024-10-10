package model;

import java.util.ArrayList;

// represents a list of meals where you can view the meals you've eaten, select
// a meal and edit the details of it, set a calorie goal, delete a meal, and view
// a summary of your day displaying total calories and macronutrients consumed
public class MealTracker {
    

    // EFFECTS: creates a Meal Tracker that is empty
    public MealTracker() {

    }

    public ArrayList<Meal> getMeals() {
        return null;
    }

    public int getCalorieGoal() {
        return 0;
    }

    public void setMeals() {
        // stub
    }

    public void setCalorieGoal() {
        // stub
    }

    // REQUIRES: name.length() > 0
    public Meal selectMeal(String name) {
        return null;
    }

    // REQUIRES: name.length() > 0 && calories > 0 && amountMacronutrient > 0 && 
    //          (macronutrient == "protein" || "fat" || "carbohydrates" || "fibre")
    // MODIFIES: this
    // EFFECTS:  modifies name, calories, and macronutrients of a meal
    public void editMeal(String name, int calories, int amountMacronutrient, String macronutrient) {
        // stub
    }
}
