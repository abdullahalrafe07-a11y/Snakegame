# Snake Game

## 1. Project Description

Snake Game is a simple one-player arcade game developed using Java and Java Swing. The player controls a snake using the keyboard, collects randomly generated food, increases the score, and grows the snake while avoiding collisions with the walls and its own body.

The project was developed progressively over a ten-week development process. The first three weeks focused on project planning, requirements, game rules, and system design. The following weeks focused on coding, gameplay features, testing, integration, and final completion.

---

## 2. Project Objective

The main objective of this project is to develop a functional Snake Game using Java Swing.

The game allows the player to:

* Control the snake using arrow keys
* Collect randomly generated food
* Increase the score
* Grow the snake after eating food
* Avoid wall collisions
* Avoid collision with its own body
* Pause and resume the game
* Restart the game after Game Over

---

## 3. Features

The final version of the Snake Game includes:

* Simple Java Swing graphical user interface
* Continuous snake movement
* Arrow-key controls
* Random food generation
* Score display
* Score increase after eating food
* Snake growth
* Wall collision detection
* Self-collision detection
* Game Over state
* Pause and resume functionality
* Restart functionality
* Simple game-state display

---

## 4. Technologies Used

* Java
* Java Swing
* Java AWT
* Apache NetBeans
* LinkedList
* Point
* Random
* Timer
* Key Bindings

---

## 5. System Requirements

To run this project, the following are required:

* Java Development Kit (JDK)
* Apache NetBeans or another Java IDE
* A computer capable of running a Java Swing application

---

## 6. How to Run

1. Open the project in Apache NetBeans or another Java IDE.
2. Make sure the Java source file is placed inside the correct package.
3. Build or compile the project.
4. Run the main class `SnakeGame`.
5. The game window will open.
6. Use the arrow keys to control the snake.

---

## 7. Controls

| Key         | Function                |
| ----------- | ----------------------- |
| Up Arrow    | Move Up                 |
| Down Arrow  | Move Down               |
| Left Arrow  | Move Left               |
| Right Arrow | Move Right              |
| P           | Pause / Resume          |
| R           | Restart after Game Over |

---

## 8. Game Rules

1. The snake moves continuously during active gameplay.
2. The player controls the snake using the arrow keys.
3. Food appears at random positions on the game board.
4. Eating food increases the score.
5. The snake grows after eating food.
6. The game ends if the snake hits the wall.
7. The game ends if the snake collides with its own body.
8. The player can pause and resume the game.
9. The player can restart the game after Game Over.

---

## 9. Project Structure

The main application is centered around the `SnakeGame` class.

The project uses a `GamePanel` to manage the main gameplay functions, including:

* Game board
* Snake
* Food
* Score
* Movement
* Collision detection
* Game states
* Drawing
* Keyboard input

Java's standard library classes and data structures are used to manage snake positions, food generation, and repeated game updates.

---

## 10. Main Class

The main class of the project is:

`SnakeGame`

The `SnakeGame` class creates the main game window and contains the `GamePanel` responsible for the gameplay.

The game uses a timer-driven game loop to update the snake's movement and repaint the game interface.

---

## 11. Snake Movement

The snake moves continuously according to its current direction.

The player changes the direction using the arrow keys. The snake body is represented using a sequence of positions, allowing the body to move forward and grow when food is collected.

The movement system also prevents invalid opposite-direction movement during gameplay.

---

## 12. Food and Score System

Food is generated randomly on the game board.

When the snake reaches the food:

1. The score increases.
2. The snake grows.
3. New food is generated.
4. The game continues.

This creates the main gameplay cycle of collecting food and increasing the player's score.

---

## 13. Collision Detection

The game checks for two main types of collision.

### Wall Collision

If the snake moves outside the game board, the game ends and the Game Over state is displayed.

### Self-Collision

The game also checks whether the snake's head touches its own body. If this happens, the game ends.

Collision detection is continuously performed during gameplay.

---

## 14. Game Loop

The game uses a timer-based game loop.

The game loop repeatedly:

* Updates the snake's position
* Checks food collection
* Checks collisions
* Updates the score
* Updates the snake's size when food is collected
* Repaints the game interface

This allows the game to run continuously while responding to player input.

---

## 15. Game States

The game contains different gameplay states.

### Normal / Running

The snake moves continuously and the player can control it.

### Paused

The game movement is temporarily stopped until the player resumes the game using the `P` key.

### Game Over

The game stops when a collision occurs. The player can restart the game using the `R` key.

---

# 16. Weekly Development

The project was developed progressively over **10 weeks**.

## Week 1 – Project Planning and Topic Selection

During Week 1, the project topic was selected and the basic project plan was prepared.

The main objectives and scope of the Snake Game were defined. Java and Java Swing were selected as the main technologies. Initial research was also performed to understand the basic requirements of developing a simple Snake Game.

### Work Completed

* Project topic selection
* Project objective definition
* Project scope definition
* Technology selection
* Initial research
* Basic development planning

---

## Week 2 – Requirements and Game Rules

During Week 2, the project requirements and gameplay rules were defined.

The required features and expected game behavior were identified. The basic game flow was also planned.

### Work Completed

* Functional requirements
* Game rules
* Required features
* Project scope refinement
* Basic gameplay flow
* Player interaction planning

---

## Week 3 – System Design

During Week 3, the system design was prepared before starting the main implementation.

Several design documents and diagrams were created to describe the structure and behavior of the game.

### Work Completed

* System flowchart
* Use case diagram
* Use case table
* Class diagram
* GUI sketch
* Basic system structure

The design stage helped organize the development process before coding began.

---

## Week 4 & 5 – Initial Coding and Snake Movement

During Weeks 4 and 5, the initial Java Swing implementation was started.

The main game window and board were created. The basic snake structure was implemented, followed by continuous movement and keyboard control.

### Work Completed

* Java Swing project setup
* Main game window
* Game board
* Initial snake structure
* Snake movement
* Continuous movement
* Arrow-key controls

---

## Week 6 & 7 – Food, Score, Growth and Collision

During Weeks 6 and 7, the main gameplay mechanics were implemented.

Random food generation was added along with score calculation and snake growth. Collision detection was also introduced.

### Work Completed

* Random food generation
* Food collection
* Score calculation
* Score display
* Snake growth
* Wall collision detection
* Self-collision detection
* Game Over logic

---

## Week 8 & 9 – Pause, Restart, Interface and Testing

During Weeks 8 and 9, additional game-state features and interface behavior were implemented.

Pause and resume functionality was added. Restart functionality was also introduced so that the player could start a new game after Game Over.

The main gameplay functions and controls were tested during this stage.

### Work Completed

* Pause functionality
* Resume functionality
* Restart functionality
* Game-state handling
* Score display improvement
* Game Over display
* Control finalization
* Gameplay testing

---

## Week 10 – Final Implementation and Completion

During Week 10, all major components were combined into the final version of the Snake Game.

Final testing and review were performed to verify the main gameplay functions and controls. The project was then prepared for final documentation and submission.

### Work Completed

* Final feature integration
* Complete gameplay implementation
* Final testing
* User interface review
* Game-state review
* Final documentation
* Submission preparation

---

# 17. Final Game Flow

The final gameplay process follows these steps:

1. Start the game.
2. Initialize the snake, food, score, and game state.
3. Display the game board, snake, and food.
4. Move the snake continuously.
5. Control the snake using the arrow keys.
6. Check whether the snake has eaten the food.
7. If food is eaten, increase the score and grow the snake.
8. Generate new food.
9. Check for wall and self-collision.
10. If a collision occurs, display Game Over.
11. Allow the player to restart the game or continue using the available controls.

---

# 18. Testing

The completed game was tested by checking the major gameplay functions and controls.

Testing included:

* Arrow-key movement
* Food collection
* Score increase
* Snake growth
* Wall collision
* Self-collision
* Pause functionality
* Resume functionality
* Restart functionality
* Game Over state
* General gameplay behavior

---

# 19. Future Improvements

Possible future improvements include:

* Multiple difficulty levels
* High-score saving
* Sound effects
* Background music
* Additional visual themes
* Additional game modes
* Improved graphical interface
* Player score storage using a file or database

---

# 20. Project Information

**Project:** Snake Game
**Student Name:** Bhuiyan Abdullah Al Rafe
**Student ID:** 11240321715
**Course:** Software Development I
**Course Code:** CSE 2216
**Submitted To:** Vashkar Kar [VK]
**Department:** Computer Science and Engineering
**University:** Northern University of Business and Technology Khulna

---

# 21. Conclusion

The Snake Game project was developed through a structured ten-week development process.

Weeks 1 to 3 focused on project planning, requirements, game rules, and system design. Weeks 4 and 5 introduced the Java Swing implementation and snake movement. Weeks 6 and 7 added food, score, snake growth, and collision detection. Weeks 8 and 9 introduced pause, restart, game-state handling, interface improvements, and testing. Week 10 completed the final integration, testing, and documentation.

The final project provides a functional Java Swing Snake Game with movement, food collection, scoring, snake growth, collision detection, Game Over, pause, and restart functionality.
