package model;

import java.util.ArrayList;

// represents a list of meals where you can view the meals you've eaten, select
// a meal and edit the details of it, set a calorie goal, delete a meal, and view
// a summary of your day displaying total calories and macronutrients consumed
public class MealTracker {

    int calorieGoal;
    ArrayList<Meal> meals;

    // EFFECTS: creates a Meal Tracker that is empty with a calorie goal
    public MealTracker(int calorieGoal) {
        meals = new ArrayList<>();
        this.calorieGoal = calorieGoal;
    }

    public ArrayList<Meal> getMeals() {
        return this.meals;
    }

    public int getCalorieGoal() {
        return this.calorieGoal;
    }

    public void setCalorieGoal(int newCalorieGoal) {
        this.calorieGoal = newCalorieGoal;
    }

    // REQUIRES: name.length() > 0
    // EFFECTS: returns meal with same name, otherwise returns null
    public Meal selectMeal(String name) {
        for (Meal next : meals) {
            if (next.getName() == name) {
                return next;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds meal to meals
    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }
}
