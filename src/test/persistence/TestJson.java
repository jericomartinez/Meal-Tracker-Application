package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;


import model.Macronutrient;
import model.Meal;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class TestJson {
    protected void checkMeal(Meal meal, String name, int calories) {
        assertEquals(name, meal.getName());
        assertEquals(calories, meal.getCalories());
    }
    
    protected void checkMacronutrient(Macronutrient macronutrient, String name, int amount) {
        assertEquals(name, macronutrient.getName());
        assertEquals(amount, macronutrient.getAmount());
    }
}
