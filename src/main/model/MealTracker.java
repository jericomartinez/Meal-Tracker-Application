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

    // REQUIRES: name is the name of a meal present in this
    // MODIFIES: this
    // EFFECTS: deletes meal from meals
    public void removeMeal(String name) {
        Meal selectedMeal = null;

        for (Meal next : meals) {
            if (next.getName().equalsIgnoreCase(name)) {
                selectedMeal = next;
                break;
            }
        }
        meals.remove(selectedMeal);    
    }

    // EFFECTS: returns sum of calories consumed from all meals
    public int sumTotalCalories() {
        int totalCalories = 0;
        for (Meal next : meals) {
            totalCalories += next.getCalories();
        }
        return totalCalories;
    }

    // EFFECTS: returns sum of protein consumed from all meals
    public int sumTotalProtein() {
        int totalProtein = 0;
        for (Meal next : meals) {
            totalProtein += next.selectMacronutrient("protein").getAmount();
        }
        return totalProtein;
    }

    // EFFECTS: returns sum of fat consumed from all meals
    public int sumTotalFat() {
        int totalFat = 0;
        for (Meal next : meals) {
            totalFat += next.selectMacronutrient("fat").getAmount();
        }
        return totalFat;
    }

    // EFFECTS: returns sum of fibre consumed from all meals
    public int sumTotalFibre() {
        int totalFibre = 0;
        for (Meal next : meals) {
            totalFibre += next.selectMacronutrient("fibre").getAmount();
        }
        return totalFibre;
    }

    // EFFECTS: returns sum of carbohydrates consumed from all meals
    public int sumTotalCarbohydrate() {
        int totalCarbohydrate = 0;
        for (Meal next : meals) {
            totalCarbohydrate += next.selectMacronutrient("carbohydrate").getAmount();
        }
        return totalCarbohydrate;
    }
}
