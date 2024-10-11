package ui;

import java.util.Scanner;

import java.util.ArrayList;

import model.Macronutrient;
import model.Meal;
import model.MealTracker;

// meal tracker application
public class MealTrackerApp {

    private Scanner input;
    private MealTracker mealTracker;

    public MealTrackerApp() {
        runMealTracker();
    }

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

    private static boolean isValidInput(String str) {
        if (str.length() != 1) {
            return false;
        }
        char c = Character.toLowerCase(str.charAt(0));
        return c >= 'a' && c <= 'z';
    }

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
        System.out.println("\t s -> view summary");
        System.out.println("\t v -> view meals");
        System.out.println("\t q -> quit");
    }

    // MODIFIES: THIS
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
        } else if (command.equals("s")) {
            doSummary();
        } else if (command.equals("v")) {
            doViewMeals();
        } else {
            System.out.println("Please try again");
        }
    }

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

    private void doDeleteMeal() {
        System.out.println("Enter name of meal:");
        String name = input.next();
        mealTracker.removeMeal(name);
    }

    @SuppressWarnings("methodlength")
    private void doEditMeal() {
        System.out.println("Enter name of meal:");
        String name = input.next();
        System.out.println("What do you want to change?");
        System.out.println("\t n -> name");
        System.out.println("\t c -> calories");
        System.out.println("\t m -> macronutrients");
        String changingElement = input.next();

        if (changingElement.equalsIgnoreCase("n")) {
            System.out.println("What do you want to rename your meal to?");
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
        } else {
            //make them restart if invalid input
        }
    }

    private void doSetCalorieGoal() {
        System.out.println("Input your calorie goal:");
        int calorieGoal = input.nextInt();
        this.mealTracker.setCalorieGoal(calorieGoal);
    }

    private void doSummary() {
        System.out.println("Total calories consumed: " + mealTracker.sumTotalCalories());
        System.out.println("Total amount protein consumed (g): " + mealTracker.sumTotalProtein());
        System.out.println("Total amount fat consumed (g): " + mealTracker.sumTotalFat());
        System.out.println("Total amount fibre consumed (g): " + mealTracker.sumTotalFibre());
        System.out.println("Total amount carbohydrate consumed (g): " + mealTracker.sumTotalCarbohydrate());
    }

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

    private void doViewMeals() {
        ArrayList<Meal> meals = new ArrayList<>();
        ArrayList<String> mealsNames = new ArrayList<>();
        meals = mealTracker.getMeals();

        for (Meal next : meals) {
            mealsNames.add(next.getName());
        }
        String listString = String.join(", ", mealsNames);
        System.out.println(listString);

        // String listString = mealsNames.toString();
        //  listString;
    }
}
