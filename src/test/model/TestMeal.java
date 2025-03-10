package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestMeal {

    private Meal meal;

    private ArrayList<Macronutrient> macronutrients1;
    private ArrayList<Macronutrient> macronutrients2;

    private Macronutrient m1;
    private Macronutrient m2;
    private Macronutrient m3;
    private Macronutrient m4;

    @BeforeEach
    public void runBefore() {
        m1 = new Macronutrient("protein", 8);
        m2 = new Macronutrient("fat", 20);
        m3 = new Macronutrient("carbohydrate", 80);
        m4 = new Macronutrient("fibre", 30);

        macronutrients1 = new ArrayList<>();
        macronutrients1.add(m1);
        macronutrients1.add(m2);  

        macronutrients2 = new ArrayList<>();
        macronutrients2.add(m1);
        macronutrients2.add(m2);

        meal = new Meal("Egg Fried Rice", 800, macronutrients1);
    }

    @Test
    void testMeal() {
        assertEquals("Egg Fried Rice", meal.getName());
        assertEquals(800, meal.getCalories());
        assertEquals(macronutrients1, meal.getMacronutrients());
    }

    @Test
    void testGetMealName() {
        assertEquals("Egg Fried Rice", meal.getName());
    }

    @Test
    void testGetCalories() {
        assertEquals(800, meal.getCalories());
    }

    @Test
    void testGetMacronutrients() {
        assertEquals(macronutrients1, meal.getMacronutrients());
    }

    @Test
    public void testSetMealName() {
        meal.setName("Fish and Chips");
        assertEquals("Fish and Chips", meal.getName());
    }

    @Test
    void testSetMacronutrients() {
        macronutrients2 = new ArrayList<>();
        macronutrients2.add(m3);
        macronutrients2.add(m4);

        meal.setMacronutrients(macronutrients2);
        assertEquals(macronutrients2, meal.getMacronutrients());
        meal.setMacronutrients(macronutrients1);
        assertEquals(macronutrients1, meal.getMacronutrients());
    }

    @Test
    void testSetCalories() {
        meal.setCalories(300);
        assertEquals(300, meal.getCalories());
    }

    @Test
    void testAddMacronutrient() {
        macronutrients2.add(m3);

        meal.addMacronutrient(m3);
        assertEquals(macronutrients2, meal.getMacronutrients());
        macronutrients2.add(m4);
        meal.addMacronutrient(m4);
        assertEquals(macronutrients2, meal.getMacronutrients());
        meal.addMacronutrient(m4);
        assertEquals(macronutrients2, meal.getMacronutrients());
        Macronutrient m5 = new Macronutrient("protein", 3);
        meal.addMacronutrient(m5);
        assertEquals(macronutrients2, meal.getMacronutrients());
    }

    @Test
    void testRemoveMacronutrient() {
        meal.removeMacronutrient("protein");
        macronutrients2.remove(0);
        assertEquals(macronutrients2, meal.getMacronutrients());
        meal.removeMacronutrient("carbohydrate");
        assertEquals(macronutrients2, meal.getMacronutrients());
        meal.removeMacronutrient("fat");
        macronutrients2.remove(0);
        assertEquals(macronutrients2, meal.getMacronutrients());
    }

    @Test
    void testSelectMacronutrient() {
        assertEquals(m1, meal.selectMacronutrient("protein"));
        assertEquals(m2, meal.selectMacronutrient("fat"));
        assertEquals(null, meal.selectMacronutrient("carbohydrate"));        
    }
}
