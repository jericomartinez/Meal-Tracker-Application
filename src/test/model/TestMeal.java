package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        
        meal = new Meal("Egg Fried Rice", 800, macronutrients1);
    }

    @Test
    public void testMeal() {
        assertEquals("Egg Fried Rice", meal.getName());
        assertEquals(800, meal.getCalories());
        assertEquals(macronutrients1, meal.getMacronutrients());
    }

    @Test
    public void testGetMealName() {
        assertEquals("Egg Fried Rice", meal.getName());
    }

    @Test
    public void testGetCalories() {
        assertEquals(800, meal.getCalories());
    }

    @Test
    public void testGetMacronutrients() {
        assertEquals(macronutrients1, meal.getMacronutrients());
    }

    @Test
    public void testSetMealName() {
        meal.setName("Fish and Chips");
        assertEquals("Fish and Chips", meal.getName());
    }

    @Test
    public void testSetMacronutrients() {
        macronutrients2 = new ArrayList<>();
        macronutrients2.add(m3);
        macronutrients2.add(m4);

        meal.setMacronutrients(macronutrients2);
        assertEquals(macronutrients2, meal.getMacronutrients());
        meal.setMacronutrients(macronutrients1);
        assertEquals(macronutrients1, meal.getMacronutrients());
    }

    @Test
    public void testAddMacronutrient() {
        macronutrients1.add(m3);
        meal.addMacronutrient(m3);
        assertEquals(macronutrients1, meal.getMacronutrients());
        macronutrients1.add(m4);
        meal.addMacronutrient(m4);
        assertEquals(macronutrients1, meal.getMacronutrients());
        meal.addMacronutrient(m4);
        assertEquals(macronutrients1, meal.getMacronutrients());
        Macronutrient m5 = new Macronutrient("protein", 3);
        meal.addMacronutrient(m5);
        assertEquals(macronutrients1, meal.getMacronutrients());
    }

    @Test
    public void testRemoveMacronutrient() {
        meal.removeMacronutrient("protein");
        macronutrients1.remove(0);
        assertEquals(macronutrients1, meal.getMacronutrients());
        meal.removeMacronutrient("carbohydrate");
        assertEquals(macronutrients1, meal.getMacronutrients());
        meal.removeMacronutrient("fat");
        macronutrients1.remove(0);
        assertEquals(macronutrients1, meal.getMacronutrients());
    }
}
