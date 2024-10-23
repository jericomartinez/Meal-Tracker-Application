package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Meal;
import model.MealTracker;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends TestJson {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MealTracker mt = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMealTracker.json");
        try {
            MealTracker mt = reader.read();
            assertEquals(0, mt.getCalorieGoal());
            ArrayList<Meal> meals = new ArrayList<>();
            assertEquals(meals, mt.getMeals());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMealTracker.json");
        try {
            MealTracker mt = reader.read();
            assertEquals(2500, mt.getCalorieGoal());
            ArrayList<Meal> meals = mt.getMeals();
            assertEquals(2, meals.size());
            checkMeal(mt.getMeals().get(0), "water", 1000);
            checkMeal(mt.getMeals().get(1), "banana bread", 150);
            checkMacronutrient(meals.get(1).getMacronutrients().get(0), "protein", 5);
            checkMacronutrient(meals.get(1).getMacronutrients().get(1), "carbohydrate", 15);        
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}