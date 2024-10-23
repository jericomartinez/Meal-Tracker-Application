package persistence;

import model.Meal;
import model.MealTracker;

import java.io.IOException;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // stub
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MealTracker read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses mealtracker from JSON object and returns it
    private MealTracker parseMealTracker(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: mt
    // EFFECTS: parses meals from JSON object and adds them to mealtracker
    private void addMeals(MealTracker mt, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: mt
    // EFFECTS: parses meal from JSON object and adds it to mealtracker
    private void addMeal(MealTracker mt, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: m
    // EFFECTS: parses macronutrients from JSON object and adds it to meal
    private void addMacronutrients(Meal m, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: m
    // EFFECTS: parses macronutrient from JSON object and adds it to meal
    private void addMacronutrient(Meal m, JSONObject jsonObject) {
        // stub
    }
}