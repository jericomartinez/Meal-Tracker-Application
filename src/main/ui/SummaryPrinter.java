package ui;

import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.MealTracker;

// Referenced from the AlarmSystem lecture-lab
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
// Represents a summary printer for printing the summary to screen
public class SummaryPrinter extends JInternalFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    private JTextArea logArea;

    // creates a SummaryPrinter window which will have the summary printed onto
    public SummaryPrinter(Component parent) {
        super("Summary", false, true, false, false);
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: prints summary
    public void printSummary(MealTracker mt) {
        logArea.setText(logArea.getText() + "Total calories consumed: "
                + mt.sumTotalCalories() + "\n\n");
        logArea.setText(logArea.getText() + "Total amount protein consumed (g): "
                + mt.sumTotalProtein() + "\n\n");
        logArea.setText(logArea.getText() + "Total amount fat consumed (g): "
                + mt.sumTotalFat() + "\n\n");
        logArea.setText(logArea.getText() + "Total amount fibre consumed (g): "
                + mt.sumTotalFibre() + "\n\n");
        logArea.setText(logArea.getText() + "Total amount carbohydrate consumed (g): "
                + mt.sumTotalCarbohydrate() + "\n\n");
        if (mt.getCalorieGoal() == 0) {
            logArea.setText(logArea.getText() + "Calorie goal has not been set");
        } else if (mt.sumTotalCalories() >= mt.getCalorieGoal()) {
            logArea.setText(logArea.getText() + "Calorie goal of " + mt.getCalorieGoal() + " calories has been met!");
        } else {
            logArea.setText(logArea.getText() + "Total calories remaining to reach calorie goal: "
                    + (mt.getCalorieGoal() - mt.sumTotalCalories()));
        }

        repaint();
    }

    // EFFECTS: places the position of the window relative to the parents window
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 20,
                parent.getHeight() - getHeight() - 20);
    }
}
