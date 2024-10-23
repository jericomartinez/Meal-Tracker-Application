package persistence;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import model.Macronutrient;
import model.Meal;
import model.MealTracker;

public class TestJsonWriter extends TestJson {
    @Test
    void testWriterInvalidFile() {
        try {
            MealTracker mt = new MealTracker(0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MealTracker mt = new MealTracker(0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMealTracker.json");
            writer.open();
            writer.write(mt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMealTracker.json");
            mt = reader.read();
            assertEquals(0, mt.getCalorieGoal());
            ArrayList<Meal> meals = new ArrayList<>();
            assertEquals(meals, mt.getMeals());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterGeneralWorkroom() {
        try {
            MealTracker mt = new MealTracker(2500);
            ArrayList<Macronutrient> macronutrients1 = new ArrayList<>();
            ArrayList<Macronutrient> macronutrients2 = new ArrayList<>();
            Macronutrient m1 = new Macronutrient("protein", 5);
            Macronutrient m2 = new Macronutrient("carbohydrate", 15);
            macronutrients2.add(m1);
            macronutrients2.add(m2);

            mt.addMeal(new Meal("water", 1000, macronutrients1));
            mt.addMeal(new Meal("banana bread", 150, macronutrients2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMealTracker.json");
            writer.open();
            writer.write(mt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMealTracker.json");
            mt = reader.read();
            assertEquals(2500, mt.getCalorieGoal());
            ArrayList<Meal> meals = mt.getMeals();
            
            assertEquals(2, meals.size());
            checkMeal(meals.get(0), "water", 1000);
            checkMeal(meals.get(1), "banana bread", 150);
            checkMacronutrient(meals.get(1).getMacronutrients().get(0), "protein", 5);
            checkMacronutrient(meals.get(1).getMacronutrients().get(1), "carbohydrate", 15);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
