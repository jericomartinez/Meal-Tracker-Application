package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.util.List;

public class TestMealTracker {

    private Meal meal1;
    private Meal meal2;


    private ArrayList<Macronutrient> macronutrients1;
    private ArrayList<Macronutrient> macronutrients2;

    private ArrayList<Meal> meals1;
    private ArrayList<Meal> meals2;
    private Macronutrient m1;
    private Macronutrient m2;
    private Macronutrient m3;
    private Macronutrient m4;

    private MealTracker mealTracker1;
    private MealTracker mealTracker2;

    
    @BeforeEach
    void runBefore() {
        macronutrients1 = new ArrayList<>();

        macronutrients2 = new ArrayList<>();

        meal1 = new Meal("Egg Fried Rice", 800, macronutrients1);
        meal2 = new Meal("Chicken and Waffles", 1200, macronutrients2);
       
        mealTracker1 = new MealTracker(2000);
        mealTracker2 = new MealTracker(1500);

        meals1 = new ArrayList<>();
        meals2 = new ArrayList<>();

        meals1.add(meal1);
        meals1.add(meal2);
        meals2.add(meal1);

        mealTracker1.addMeal(meal1);
        mealTracker1.addMeal(meal2);
    }

    @Test
    void testMealTracker() {
        assertEquals(null, mealTracker1.getMeals());
        assertEquals(2000, mealTracker1.getCalorieGoal());
    }

    @Test
    void testGetMeals() {
        assertEquals(1, mealTracker1.getMeals());
    }
    
    @Test
    void testGetCalorieGoal() {
        assertEquals(2000, mealTracker1.getCalorieGoal());
    }

    @Test
    void testSetCalorieGoal() {
        mealTracker1.setCalorieGoal(200);
        assertEquals(200, mealTracker1.getCalorieGoal());
    }

    @Test
    void testSelectMeal() {
        assertEquals(meal1, mealTracker1.selectMeal("Egg Fried Rice"));
        assertEquals(meal2, mealTracker1.selectMeal("Chicken and Waffles"));
    }

    @Test
    void testAddMeal() {
        mealTracker2.addMeal(meal1);
        assertEquals(meals2, mealTracker1.getMeals());
        mealTracker2.addMeal(meal2);
        assertEquals(meals1, mealTracker1.getMeals());
    }
}
