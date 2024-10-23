package persistence;

import model.Macronutrient;
import model.Meal;
import model.MealTracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MealTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMealTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses mealtracker from JSON object and returns it
    private MealTracker parseMealTracker(JSONObject jsonObject) {
        int calorieGoal = jsonObject.getInt("calorieGoal");
        MealTracker mt = new MealTracker(calorieGoal);
        addMeals(mt, jsonObject);
        return mt;
    }

    // MODIFIES: mt
    // EFFECTS: parses meals from JSON object and adds them to mealtracker
    private void addMeals(MealTracker mt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(mt, nextMeal);
        }
    }

    // MODIFIES: mt
    // EFFECTS: parses meal from JSON object and adds it to mealtracker
    private void addMeal(MealTracker mt, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int calories = jsonObject.getInt("calories");
        ArrayList<Macronutrient> macronutrients = new ArrayList<>();
        Meal m = new Meal(name, calories, macronutrients);
        addMacronutrients(m, jsonObject);
        mt.addMeal(m);
    }

    // MODIFIES: m
    // EFFECTS: parses macronutrients from JSON object and adds it to meal
    private void addMacronutrients(Meal m, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("macronutrients");
        for (Object json : jsonArray) {
            JSONObject nextMacronutrient = (JSONObject) json;
            addMacronutrient(m, nextMacronutrient);
        }
    }

    // MODIFIES: m
    // EFFECTS: parses macronutrient from JSON object and adds it to meal
    private void addMacronutrient(Meal m, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int amount = jsonObject.getInt("amount");
        Macronutrient macronutrient = new Macronutrient(name, amount);
        m.addMacronutrient(macronutrient);
    }
}
