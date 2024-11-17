package ui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Meal;
import model.MealTracker;

// Referenced from the AlarmSystem lecture-lab
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
// Represents a screen printer for printing meal names to screen
public class ScreenPrinter extends JInternalFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    private JTextArea logArea;
    
    // creates a ScreenPrinter window which will have the meal names printed onto
    public ScreenPrinter(Component parent) {
        super("View Meals", false, true, false, false);
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }
    
    // MODIFIES: this
    // EFFECTS:  prints log of meals
    public void printLog(MealTracker mt) {
        ArrayList<Meal> meals = mt.getMeals();

        for (Meal next : meals) {
            logArea.setText(logArea.getText() + next.getName() + "\namount of calories: " 
                    + next.getCalories() + "\n\n");
        }

        repaint();
    }
    
    // EFFECTS: places the position of the window relative to the parents window
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 20,
                parent.getHeight() - getHeight() - 20);
    }
}

