# Meal Tracker

## Keep track of your calories and meals with ease

I will be creating a *meal tracker app* which will help you reach your calorie goals. The app will include a daily calorie goal that a person sets and wants to achieve. To reach those goals, the app will propose a multitude of different meals providing the amount of calories and macronutrients in each dish. The user can either choose one of the suggested meals if they decided to cook and eat it or, they will input their own meal with the name of the food along with its calories and macronutients. At the end of the day, the app will sum the calories consumed and give you your daily calorie and macronutrient consumption for the day. 

The people who will use this app are those who would like to keep track of their calorie intake in order to achieve body goals such as gaining or losing weight. This project is of interest to me because I enjoy going to the gym and working out. Unknown to some, eating is just as important as working out for getting bigger and stronger. As such, this project implements my personal interests of fitness and health along with the design processes we learn in class.

A *User Stories* list:
- I want to be able to edit the details of a meal I ate today like the amount of calories it was or the name of the dish.
- I want to be able to view all the meals I ate today.
- I want to be able to set my calorie goal for the day.
- I want to be able to view a summary of my calorie and macronutrient intake for the day.
- I want to be able to delete a meal from my tracker.

- I want to be able to save my meals (if I so choose) for when I reopen my application again
- I want to be able load my saved meals from when I last saved them (if I so choose)

# Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the "Add meal" button, then complete the prompts given. Doing this adds a meal to the meal tracker app.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the "View meals" button. Doing so will create a new window within the application window which will display a list of all the meals in the meal tracker along with the amount of calories each meal has.
- You can generate the third required action related to the user story "adding multiple Xs to a Y" by clicking on the "edit meal" button. Doing so will create a new window within the application window which will prompt you options to edit the details of one of the meals added.
- You can locate my visual component by observing the right side of the window. When the calorie goal is not set or met, there will be a sad image. When the calorie goal is met, there will be a happy image.
- You can save the state of my application by clicking on the "Save" button on the top menu, or by pressing "control + S" on your keyboard.
- You can reload the state of my application by clicking on the "Load" button on the top menu, or by pressing "control + L" on your keyboard.

# Phase 4: Task 2
**Sample eventlog output:**

Added new meal: soup <br>
Set new calories of soup: 60 <br>
Set new calorie goal: 250 <br>
Removed soup from meal tracker <br>

# Phase 4: Task 3
If I had more time to work on my project, I would have MealTrackerAppUI extend MealTrackerApp. Doing so would have allowed me to reuse code that they both shared. Another change I would make would be to extract some of the code in the doEditMeal method in the MealTrackerApp class to create a new method as a helper to improve the readability of my code.
