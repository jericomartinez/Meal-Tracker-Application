package ui;

import java.awt.event.ActionEvent;
import javax.swing.*;

// Referenced from the AlarmSystem lecture-lab
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
// Represents Meal Tracker App's main window frame
public class MealTrackerAppUI extends JFrame {

    // creates MealTrackerApp window
    public MealTrackerAppUI() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: sets up the the MealTrackerApp window
    private void initialize() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds buttom panel to window
    private void addButtonPanel() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds menu bar to window
    private void addMenu() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: helper to add menu items to menu
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        // stub
    }

    // Represents the save action
    private class AddSaveAction extends AbstractAction {

        // creates save action
        AddSaveAction() {
            // stub
        }

        // MODIFIES: this
        // EFFECTS: saves MealTracker to file
        @Override
        public void actionPerformed(ActionEvent evt) {
            // stub
        }
    }

    // Represents the load action
    private class AddLoadAction extends AbstractAction {

        // creates load action
        AddLoadAction() {
            // stub
        }

        // MODIFIES: this
        // EFFECTS: loads MealTracker from file
        @Override
        public void actionPerformed(ActionEvent evt) {
            // stub
        }
    }

    // Represents the add meal action
    private class AddMealAction extends AbstractAction {

        // creates add meal action
        AddMealAction() {
            // stub
        }

        // MODIFIES: this
        // EFFECTS: creates and adds a meal to MealTracker
        @Override
        public void actionPerformed(ActionEvent evt) {
            // stub
        }
    }

    // Represents the delete meal action
    private class DeleteMealAction extends AbstractAction {

        // creates delete meal action
        DeleteMealAction() {
            // stub
        }

        // REQUIRES: mealTracker.contains(name)
        // MODIFIES: this
        // EFFECTS: deletes meal from MealTracker
        @Override
        public void actionPerformed(ActionEvent evt) {
            // stub
        }
    }

    // Represents the set calorie goal action
    private class SetCalorieGoalAction extends AbstractAction {

        // creates a set calorie goal action
        SetCalorieGoalAction() {
            // stub
        }

        // REQUIRES: calorieGoal > 0
        // MODIFIES: this
        // EFFECTS: sets the calorie goal
        @Override
        public void actionPerformed(ActionEvent evt) {
            // stub
        }
    }

    // Represents view meal action
    private class ViewMealsAction extends AbstractAction {

        // creates view meal action
        ViewMealsAction() {
            // stub
        }

        // EFFECTS: creates a window that displays all meals added to mealTracker
        @Override
        public void actionPerformed(ActionEvent evt) {
            // stub
        }
    }

    // Represents view summary action
    private class ViewSummaryAction extends AbstractAction {

        // creates view summary action
        ViewSummaryAction() {
            // stub
        }

        // EFFECTS: creates a window that displays a summary of calories
        // and macronutrients consumed, and the amount of
        // calories remaining to reach the set calorie goal
        @Override
        public void actionPerformed(ActionEvent evt) {
            // stub
        }

    }

    // Represents edit meal action
    private class EditMealAction extends AbstractAction {

        // creates edit meal action
        EditMealAction() {
            // stub
        }

        // REQUIRES: this.mealTracker.contains(name)
        // MODIFIES: this
        // EFFECTS: edits details of meal with name from mealTracker
        @Override
        public void actionPerformed(ActionEvent evt) {
            // stub
        }

        // REQUIRES: this.mealTracker.contains(name)
        // MODIFIES: this
        // EFFECTS: edits the detail of the meal
        private void doEdit(String name, String changingElement) {
            // stub
        }
    }

    // REQUIRES: message != null && !message.isEmpty()
    // && title != null && !title.isEmpty()
    // MODIFIES: this
    // EFFECTS: helper to create JOptionPane dialog windows
    private String optionPaneMaker(String message, String title) {
        return "";
    }

    // MODIFIES: this
    // EFFECTS: helper to centre the window on screen
    private void centreOnScreen() {
        // stub
    }
}
