package ui;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.Macronutrient;
import model.Meal;
import model.MealTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

// referenced from the TellerApp
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// meal tracker application
public class MealTrackerApp {
    private static final String JSON_STORE = "./data/mealtracker.json";
    private Scanner input;
    private MealTracker mealTracker;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs mealTracker
    public MealTrackerApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMealTracker();
    }

    // MODIFIES: this
    // EFFECTS:  processes user inputs
    public void runMealTracker() {
        boolean keepRunning = true;
        String command = null;

        initialize(0);

        while (keepRunning) {
            displayMenu();
            command = input.next();
            while (!isValidInput(command)) {
                displayMenu();
                System.out.println("Invalid option, please try again");
                command = input.next();                
            }
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: checks if str is a valid string
    private static boolean isValidInput(String str) {
        if (str.length() != 1) {
            return false;
        }
        char c = Character.toLowerCase(str.charAt(0));
        return c >= 'a' && c <= 'z';
    }
    
    // MODIFIES: this
    // EFFECTS:  starts MealTracker app
    private void initialize(int c) {
        mealTracker = new MealTracker(c);
        input = new Scanner(System.in);
    }
    

    // EFFECTS: displays menu of options for users
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?\n");
        System.out.println("\t a -> add a meal");
        System.out.println("\t d -> delete a meal");
        System.out.println("\t e -> edit a meal");
        System.out.println("\t c -> set calorie goal");
        System.out.println("\t p -> view summary");
        System.out.println("\t m -> view meals");
        System.out.println("\t s -> save meal tracker");
        System.out.println("\t l -> load meal tracker");
        System.out.println("\t q -> quit");
    }

    // REQUIRES: command == "a" || "d" || "e" || "c" || "p" || "v" || "s" || "l"
    // MODIFIES: this
    // EFFECTS: processes the users command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddMeal();
        } else if (command.equals("d")) {
            doDeleteMeal();
        } else if (command.equals("e")) {
            doEditMeal();
        } else if (command.equals("c")) {
            doSetCalorieGoal();
        } else if (command.equals("p")) {
            doSummary();
        } else if (command.equals("m")) {
            doViewMeals();
        } else if (command.equals("s")) {
            saveMealTracker();
        } else if (command.equals("l")) {
            loadMealTracker();
        }else {
            System.out.println("Please try again");
        }
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the mealtracker to file
    private void saveMealTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(mealTracker);
            jsonWriter.close();
            System.out.println("Saved meal tracker with calorie goal:" + mealTracker.getCalorieGoal() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS:  loads mealtracker from file
    private void loadMealTracker() {
        try {
            mealTracker = jsonReader.read();
            System.out.println("Loaded meal tracker with calorie goal:" + mealTracker.getCalorieGoal() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // REQUIRES: name.length() > 0
    // MODIFIES: this
    // EFFECTS:  adds a meal to mealTracker
    private void doAddMeal() {
        System.out.println("Enter name of meal:");
        String name = input.next();
        input.nextLine();
        System.out.println("Enter calories of meal:");
        int calories = input.nextInt();
        ArrayList<Macronutrient> macronutrient = createMacronutrients();
        Meal newMeal = new Meal(name, calories, macronutrient);
        mealTracker.addMeal(newMeal);
    }

    // REQUIRES: this.mealTracker contains a meal with name
    // MODIFIES: this
    // EFFECTS:  deletes meal with name from mealTracker
    private void doDeleteMeal() {
        System.out.println("Enter name of meal:");
        String name = input.next();
        mealTracker.removeMeal(name);
    }

    // REQUIRES: this.mealTracker contains a meal with name
    // MODIFIES: this
    // EFFECTS:  edits details of meal with name from mealTracker
    @SuppressWarnings("methodlength")
    private void doEditMeal() {
        System.out.println("Enter name of meal you would like to edit:");
        String name = input.next();
        System.out.println("What do you want to change?");
        System.out.println("\t n -> name");
        System.out.println("\t c -> calories");
        System.out.println("\t m -> macronutrients");
        String changingElement = input.next();

        if (changingElement.equalsIgnoreCase("n")) {
            System.out.println("What would you like to rename your meal to?");
            String newName = input.next();
            mealTracker.selectMeal(name).setName(newName);
        } else if (changingElement.equalsIgnoreCase("c")) {
            System.out.println("What do you want to your meal's calories to?");
            int newCalories = input.nextInt();
            mealTracker.selectMeal(name).setCalories(newCalories);
        } else if (changingElement.equalsIgnoreCase("m")) {
            System.out.println("Which macronutrient do you want to change?");
            String changeMacronutrient = input.next();
            System.out.println("Input new macronutrient:");
            String newMacronutrient = input.next();
            System.out.println("Input amount of new macronutrient:");
            int newMacronutrientAmount = input.nextInt();
            Macronutrient selectedMacronutrient = mealTracker.selectMeal(name).selectMacronutrient(changeMacronutrient);
            selectedMacronutrient.setName(newMacronutrient);
            selectedMacronutrient.setAmount(newMacronutrientAmount);
        }
    }

    // REQUIRES: calorieGoal > 0
    // MODIFIES: this
    // EFFECTS:  sets a calorie goal
    private void doSetCalorieGoal() {
        System.out.println("Input your calorie goal:");
        int calorieGoal = input.nextInt();
        this.mealTracker.setCalorieGoal(calorieGoal);
    }

    // EFFECTS: prints a summary of calories and macronutrients consumed, and calories remaining to reach calorie goal
    private void doSummary() {
        int calorieGoal = mealTracker.getCalorieGoal();
        int totalCalories = mealTracker.sumTotalCalories();
        System.out.println("Total calories consumed: " + mealTracker.sumTotalCalories());
        System.out.println("Total amount protein consumed (g): " + mealTracker.sumTotalProtein());
        System.out.println("Total amount fat consumed (g): " + mealTracker.sumTotalFat());
        System.out.println("Total amount fibre consumed (g): " + mealTracker.sumTotalFibre());
        System.out.println("Total amount carbohydrate consumed (g): " + mealTracker.sumTotalCarbohydrate());
        if (mealTracker.getCalorieGoal() == 0) {
            System.out.println("Calorie goal not set");
        } else if (mealTracker.sumTotalCalories() > mealTracker.getCalorieGoal()) {
            System.out.println("Calorie goal of " + calorieGoal + " calories has been met!");
        } else {
            System.out.println("Total calories remaining to reach calorie goal: " + (calorieGoal - totalCalories));
        }
    }

    // REQUIRES: size >= 0 && size < 5 && 
    //           name == protein || fat || fibre || carbohydrate &&
    //           amount > 0
    // MODIFIES: this
    // EFFECTS:  creates a list of macronutrients that correspond to the meal being created
    private ArrayList<Macronutrient> createMacronutrients() {
        ArrayList<Macronutrient> macronutrients = new ArrayList<>();

        System.out.println("How many different macronutrients are present in your meal?");
        int size = input.nextInt();

        for (int i = 0; i < size; i++) {
            System.out.println("Input macronutrient name:");
            String name = input.next();
            System.out.println("Input amount of the macronutrient in grams");
            int amount = input.nextInt();
            Macronutrient newMacronutrient = new Macronutrient(name, amount);
            macronutrients.add(newMacronutrient);
        }
        return macronutrients;
    }

    // EFFECTS: prints a string of a list of all meals consumed
    private void doViewMeals() {
        ArrayList<Meal> meals = new ArrayList<>();
        ArrayList<String> mealsNames = new ArrayList<>();
        meals = mealTracker.getMeals();

        for (Meal next : meals) {
            mealsNames.add(next.getName());
        }
        String listString = String.join(", ", mealsNames);
        System.out.println(listString);
    }
}
