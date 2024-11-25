package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import model.Event;
import model.EventLog;
import model.Macronutrient;
import model.Meal;
import model.MealTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

// Referenced from the AlarmSystem lecture-lab
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
// Represents Meal Tracker App's main window frame
public class MealTrackerAppUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/mealtracker.json";

    private JPanel buttonPanel;
    private JPanel imagePanel;
    private JLabel imageAsLabel;

    private ImageIcon happyImage;
    private ImageIcon sadImage;

    private MealTracker mealTracker;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // creates MealTrackerApp window
    public MealTrackerAppUI() {
        initialize();
    }

    // MODIFIES: this
    // EFFECTS: sets up the the MealTrackerApp window
    private void initialize() {
        mealTracker = new MealTracker(0);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Meal Tracker Application");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();
        addMenu();
        loadImages();
        addImagePanel();
        setImage();

        centreOnScreen();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDescription());
                }
                System.exit(0);
            }
        });
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds buttom panel to window
    private void addButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(new JButton(new AddMealAction()));
        buttonPanel.add(new JButton(new DeleteMealAction()));
        buttonPanel.add(new JButton(new EditMealAction()));
        buttonPanel.add(new JButton(new SetCalorieGoalAction()));
        buttonPanel.add(new JButton(new ViewMealsAction()));
        buttonPanel.add(new JButton(new ViewSummaryAction()));

        add(buttonPanel, BorderLayout.WEST);
    }

    // Referenced from C3-LectureLabStarter
    // https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    // MODIFIES: this
    // EFFECTS: loads images into variables
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        happyImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "happy.png");
        sadImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "sad.png");
    }

    // MODIFIES: this
    // EFFECTS: adds menu bar to window
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu saveMenu = new JMenu("Save");
        saveMenu.setMnemonic('S');
        addMenuItem(saveMenu, new AddSaveAction(),
                KeyStroke.getKeyStroke("control S"));
        menuBar.add(saveMenu);

        JMenu loadMenu = new JMenu("Load");
        loadMenu.setMnemonic('L');
        addMenuItem(loadMenu, new AddLoadAction(),
                KeyStroke.getKeyStroke("control L"));
        menuBar.add(loadMenu);

        setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: helper to add menu items to menu
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    // Represents the save action
    private class AddSaveAction extends AbstractAction {

        // creates save action
        AddSaveAction() {
            super("Save");
        }

        // MODIFIES: this
        // EFFECTS: saves MealTracker to file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(mealTracker);
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Represents the load action
    private class AddLoadAction extends AbstractAction {

        // creates load action
        AddLoadAction() {
            super("Load");
        }

        // MODIFIES: this
        // EFFECTS: loads MealTracker from file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                mealTracker = jsonReader.read();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Represents the add meal action
    private class AddMealAction extends AbstractAction {

        // creates add meal action
        AddMealAction() {
            super("Add meal");
        }

        // MODIFIES: this
        // EFFECTS: creates and adds a meal to MealTracker
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = optionPaneMaker("Meal name?", "Enter name of meal");
            String caloriesString = optionPaneMaker("Number of calories?", "Enter number of calories");
            int calories = Integer.valueOf(caloriesString);
            String sizeString = optionPaneMaker("Number of macronutrients? (0-4)", "Enter number of macronutrients");
            int size = Integer.valueOf(sizeString);
            ArrayList<Macronutrient> macronutrients = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                String macronutrientName = optionPaneMaker("Macronutrient name? (protein, fat, carbohydrate, fibre)",
                        "Enter name of macronutrient");
                String amountString = optionPaneMaker("Amount of " + macronutrientName + " (g)?",
                        "Enter amount of macronutrients in grams");
                int amount = Integer.valueOf(amountString);
                Macronutrient newMacronutrient = new Macronutrient(macronutrientName, amount);
                macronutrients.add(newMacronutrient);
            }
            Meal newMeal = new Meal(name, calories, macronutrients);
            mealTracker.addMeal(newMeal);
            setImage();
        }
    }

    // Represents the delete meal action
    private class DeleteMealAction extends AbstractAction {

        // creates delete meal action
        DeleteMealAction() {
            super("Delete meal");
        }

        // REQUIRES: mealTracker.contains(name)
        // MODIFIES: this
        // EFFECTS: deletes meal from MealTracker
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = JOptionPane.showInputDialog(null,
                    "Meal name to delete?",
                    "Enter name of meal you would like to delete",
                    JOptionPane.QUESTION_MESSAGE);
            mealTracker.removeMeal(name);
            setImage();
        }
    }

    // Represents the set calorie goal action
    private class SetCalorieGoalAction extends AbstractAction {

        // creates a set calorie goal action
        SetCalorieGoalAction() {
            super("Set calorie goal");
        }

        // REQUIRES: calorieGoal > 0
        // MODIFIES: this
        // EFFECTS: sets the calorie goal
        @Override
        public void actionPerformed(ActionEvent evt) {
            String calorieString = optionPaneMaker("Calorie goal?", "Enter calorie goal");
            int calorieGoal = Integer.valueOf(calorieString);
            mealTracker.setCalorieGoal(calorieGoal);
        }
    }

    // Represents view meal action
    private class ViewMealsAction extends AbstractAction {

        // creates view meal action
        ViewMealsAction() {
            super("View meals");
        }

        // EFFECTS: creates a window that displays all meals added to mealTracker
        @Override
        public void actionPerformed(ActionEvent evt) {
            ScreenPrinter sp;
            sp = new ScreenPrinter(MealTrackerAppUI.this);
            add((ScreenPrinter) sp);
            sp.printLog(mealTracker);
            setImage();
        }
    }

    // Represents view summary action
    private class ViewSummaryAction extends AbstractAction {

        // creates view summary action
        ViewSummaryAction() {
            super("View summary");
        }

        // EFFECTS: creates a window that displays a summary of calories
        // and macronutrients consumed, and the amount of
        // calories remaining to reach the set calorie goal
        @Override
        public void actionPerformed(ActionEvent evt) {
            SummaryPrinter sp;
            sp = new SummaryPrinter(MealTrackerAppUI.this);
            add((SummaryPrinter) sp);
            sp.printSummary(mealTracker);
            setImage();
        }

    }

    // Represents edit meal action
    private class EditMealAction extends AbstractAction {

        // creates edit meal action
        EditMealAction() {
            super("Edit meal");
        }

        // REQUIRES: this.mealTracker.contains(name)
        // MODIFIES: this
        // EFFECTS: edits details of meal with name from mealTracker
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = optionPaneMaker("What meal to edit", "Enter name of meal you would like to edit");
            String changingElement = optionPaneMaker("What to edit", "name, calores, or macronutrients");
            doEdit(name, changingElement);
        }

        // REQUIRES: this.mealTracker.contains(name)
        // MODIFIES: this
        // EFFECTS: edits the detail of the meal
        private void doEdit(String name, String changingElement) {
            if (changingElement.equalsIgnoreCase("name")) {
                String newName = optionPaneMaker("New name?", "Enter the new name of the meal.");
                mealTracker.selectMeal(name).setName(newName);
            } else if (changingElement.equalsIgnoreCase("calories")) {
                String newCalorieString = optionPaneMaker("New calories?",
                        "Enter the new amount of calories for the meal.");
                int newCalories = Integer.parseInt(newCalorieString);
                mealTracker.selectMeal(name).setCalories(newCalories);
            } else if (changingElement.equalsIgnoreCase("macronutrients")) {
                String changingMacronutrient = optionPaneMaker("Change Macronutrient?",
                        "Which macronutrient? (protein, fat, carbohydrate, fibre)");
                String newMacronutrient = optionPaneMaker("New Macronutrient?",
                        "Enter name of the new macronutrient you would like to add.");
                String newMacronutrientAmountString = optionPaneMaker("New Macronutrient amount?",
                        "Enter amount of the new macronutrient you would like to add.");
                int newMacronutrientAmount = Integer.parseInt(newMacronutrientAmountString);
                Macronutrient selectedMacronutrient = mealTracker.selectMeal(name)
                        .selectMacronutrient(changingMacronutrient);
                selectedMacronutrient.setName(newMacronutrient);
                selectedMacronutrient.setAmount(newMacronutrientAmount);
            }
        }
    }

    // REQUIRES: message != null && !message.isEmpty()
    // && title != null && !title.isEmpty()
    // MODIFIES: this
    // EFFECTS: helper to create JOptionPane dialog windows
    private String optionPaneMaker(String message, String title) {
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: helper to centre the window on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // Referenced from C3-LectureLabStarter
    // https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    // MODIFIES: this
    // EFFECTS: helper to add imagePanel to window
    private void addImagePanel() {
        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(350, 400));
        add(imagePanel, BorderLayout.EAST);
    }

    // Referenced from C3-LectureLabStarter
    // https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    // MODIFIES: this
    // EFFECTS: removes the image currently displayed on imagePanel
    private void removeExistingImage() {
        if (imageAsLabel != null) {
            imagePanel.remove(imageAsLabel);
        }
    }

    // Referenced from C3-LectureLabStarter
    // https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    // MODIFIES: this
    // EFFECTS: sets image to displayed on imagePanel to sadImage
    private void setSadImage() {
        removeExistingImage();
        imageAsLabel = new JLabel(sadImage);
        imagePanel.add(imageAsLabel);
    }

    // Referenced from C3-LectureLabStarter
    // https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    // MODIFIES: this
    // EFFECTS: sets image to displayed on imagePanel to happyImage
    private void setHappyImage() {
        removeExistingImage();
        imageAsLabel = new JLabel(happyImage);
        imagePanel.add(imageAsLabel);
    }

    // Referenced from C3-LectureLabStarter
    // https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter.git
    // MODIFIES: this
    // EFFECTS: displays happyImage if calorie goal is met, otherwise, displays
    // sadImage
    private void setImage() {
        if (mealTracker.sumTotalCalories() < mealTracker.getCalorieGoal()
                || mealTracker.getCalorieGoal() == 0) {
            setSadImage();
        } else {
            setHappyImage();
        }
    }
}