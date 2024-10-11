package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestMealTracker {

    private Meal meal1;
    private Meal meal2;

    private Macronutrient m1;
    private Macronutrient m2;
    private Macronutrient m3;
    private Macronutrient m4;
    private Macronutrient m5;
    private Macronutrient m6;
    private Macronutrient m7;
    private Macronutrient m8;

    private ArrayList<Macronutrient> macronutrients1;
    private ArrayList<Macronutrient> macronutrients2;

    private ArrayList<Meal> meals1;
    private ArrayList<Meal> meals2;

    private MealTracker mealTracker1;
    private MealTracker mealTracker2;

    @BeforeEach
    void runBefore() {
        
        m1 = new Macronutrient("protein", 8);
        m2 = new Macronutrient("fat", 20);
        m3 = new Macronutrient("carbohydrate", 80);
        m4 = new Macronutrient("fibre", 30);
        m5 = new Macronutrient("protein", 12);
        m6 = new Macronutrient("fat", 17);
        m7 = new Macronutrient("carbohydrate", 40);
        m8 = new Macronutrient("fibre", 1);

        macronutrients1 = new ArrayList<>();
        macronutrients1.add(m1);
        macronutrients1.add(m2);
        macronutrients1.add(m3);
        macronutrients1.add(m4);

        macronutrients2 = new ArrayList<>();
        macronutrients2.add(m5);
        macronutrients2.add(m6);
        macronutrients2.add(m7);
        macronutrients2.add(m8);

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
        ArrayList<Meal> emptyList = new ArrayList<>();

        assertEquals(emptyList, mealTracker2.getMeals());
        assertEquals(1500, mealTracker2.getCalorieGoal());
    }

    @Test
    void testGetMeals() {
        assertEquals(meals1, mealTracker1.getMeals());
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
        assertEquals(null, mealTracker1.selectMeal("Beef Noodles"));
    }

    @Test
    void testAddMeal() {
        mealTracker2.addMeal(meal1);
        assertEquals(meals2, mealTracker2.getMeals());
        mealTracker2.addMeal(meal2);
        assertEquals(meals1, mealTracker2.getMeals());
    }

    @Test
    void testRemoveMeal() {
        Meal meal3 = new Meal("Yogurt and Granola", 450, macronutrients1);
        mealTracker1.addMeal(meal3);

        mealTracker1.removeMeal("Yogurt and Granola");
        assertEquals(meals1, mealTracker1.getMeals());

        mealTracker1.removeMeal("Egg Fried Rice");
        meals1.remove(0);
        assertEquals(meals1, mealTracker1.getMeals());

        mealTracker1.removeMeal("Chicken and Waffles");
        meals1.remove(0);
        assertEquals(meals1, mealTracker1.getMeals());

        mealTracker1.removeMeal("Bubbles");
    }

    @Test
    void testSumTotalCalories() {
        assertEquals(2000, mealTracker1.sumTotalCalories());
    }

    @Test
    void testSumTotalProtein() {
        assertEquals(20, mealTracker1.sumTotalProtein());
    }

    @Test
    void sumTotalFat() {
        assertEquals(37, mealTracker1.sumTotalFat());
    }

    @Test
    void sumTotalFibre() {
        assertEquals(31, mealTracker1.sumTotalFibre());
    }

    @Test
    void sumTotalCarbohydrate() {
        assertEquals(120, mealTracker1.sumTotalCarbohydrate());
    }
}
