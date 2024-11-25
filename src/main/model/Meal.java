package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a meal having a name, number of calories, and a list of macronutrients.
public class Meal implements Writable {

    private String name; // name of meal
    private int calories; // number of calories
    private ArrayList<Macronutrient> macronutrients; // list of macronutrients

    // REQUIRES: name.length() > 0 && calories > 0
    // EFFECTS: creates a meal with this.name is set to name; this.calories
    // is set to calories; this.macronutrients is set to macronutrients.
    public Meal(String name, int calories, ArrayList<Macronutrient> macronutrients) {
        this.name = name;
        this.calories = calories;
        this.macronutrients = macronutrients;
    }

    public String getName() {
        return this.name;
    }

    public int getCalories() {
        return this.calories;
    }

    public ArrayList<Macronutrient> getMacronutrients() {
        return this.macronutrients;
    }

    public void setName(String name) {
        EventLog.getInstance().logEvent(new Event("Set new name of meal from " + getName() + " to: " + name));
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
        EventLog.getInstance().logEvent(new Event("Set new calories of " + getName() + ": " + calories));
    }

    public void setMacronutrients(ArrayList<Macronutrient> macronutrients) {
        this.macronutrients = macronutrients;
        EventLog.getInstance().logEvent(new Event("Set new macronutrients"));
    }

    // REQUIRES: macronutrients.length() <= 4 && max one of each
    // macronutrient: fat, fibre, carbohydrate, protein
    // MODIFIES: this
    // EFFECTS: adds macronutrient m to macronutrients,
    // if macronutrient is present in macronutrients with same name,
    // do not add m to macronutrients
    public void addMacronutrient(Macronutrient m) {
        ArrayList<String> namesOfMacronutrients = new ArrayList<>();
        String macronutrientName = m.getName();

        for (Macronutrient next : macronutrients) {
            namesOfMacronutrients.add(next.getName());
        }
        if (!namesOfMacronutrients.contains(m.getName())) {
            macronutrients.add(m);
        }
        EventLog.getInstance().logEvent(new Event("Added new macronutrient " + macronutrientName + " to " + getName()));
    }

    // REQUIRES: (s == "protein" || s == "fat" || s == "carbohydtarate" || s ==
    // "fibre")
    // && !macronutrients.isEmpty()
    // MODIFIES: this
    // EFFECTS: removes macronutrient that has name == s
    // if no macronutrient in has name == s, then does nothing
    public void removeMacronutrient(String s) {
        ArrayList<Macronutrient> removeMacronutrients = new ArrayList<>();
        for (Macronutrient next : macronutrients) {
            if (next.getName() == s) {
                removeMacronutrients.add(next);
            }
        }
        macronutrients.removeAll(removeMacronutrients);
        EventLog.getInstance().logEvent(new Event("Removed macronutrient " + s + " from " + getName()));
    }

    // REQUIRES: s.length() > 0
    // EFFECTS: returns macronutrient with same name; otherwise returns null
    public Macronutrient selectMacronutrient(String s) {
        for (Macronutrient next : macronutrients) {
            if (next.getName().equalsIgnoreCase(s)) {
                return next;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("calories", this.calories);
        json.put("macronutrients", macronutrientsToJson());
        return json;
    }

    // EFFECTS: returns macronutrients in this Meal as a JSON array
    private JSONArray macronutrientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Macronutrient m : this.macronutrients) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
