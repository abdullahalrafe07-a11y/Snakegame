# 🐍 Snake Game

A simple 2D Snake Game developed in **Java Swing** as a university project. The player controls a snake, collects food, increases the score, grows the snake, and avoids hitting the walls or itself.

## 🎮 Features

* Snake movement using Arrow Keys
* Random food generation
* Snake growth after eating food
* Score system
* Wall collision detection
* Snake self-collision detection
* Game Over system
* Pause system
* Restart system
* Simple graphical game board
* Continuous game loop

## 🛠️ Technologies

* **Java**
* **Java Swing**
* **Java AWT**
* **LinkedList**
* **Point**
* **Random**
* **Java Timer**
* **Key Bindings**

## 💻 Requirements

* JDK 17 or newer
* Any Java IDE such as NetBeans, IntelliJ IDEA, or Eclipse

## 🚀 How to Run

1. Clone the repository:

```bash
git clone https://github.com/your-username/SnakeGame.git
```

2. Open the project in a Java IDE.
3. Locate `SnakeGame.java`.
4. Make sure the package is:

```java
package snakegame;
```

5. Run the `main()` method.
6. The Snake Game window will open.

## 🎮 Controls

| **Key** | **Action**              |
| ------- | ----------------------- |
| `↑`     | Move Up                 |
| `↓`     | Move Down               |
| `←`     | Move Left               |
| `→`     | Move Right              |
| `P`     | Pause / Resume          |
| `R`     | Restart after Game Over |

## 📁 Project Structure

```text
src/
└── snakegame/
    └── SnakeGame.java
```

### Main Class

* **SnakeGame.java** — Starts the game window and contains the main game panel, snake movement, food generation, collision detection, score system, keyboard controls, pause, restart, and game display.

## 🐍 Snake Movement

The snake is stored using Java's `LinkedList<Point>`.

```java
LinkedList<Point> snake = new LinkedList<>();
```

The first element represents the snake's head, while the remaining elements represent its body.

During each game loop, a new head position is created and added to the beginning of the list.

```java
snake.addFirst(newHead);
```

If the snake does not eat food, the last body segment is removed:

```java
snake.removeLast();
```

When the snake eats food, the last segment is not removed, allowing the snake to grow.

## 🍎 Food & Score System

Food is generated randomly on the game board using the `Random` class.

```java
food = new Point(
    random.nextInt(COLS),
    random.nextInt(ROWS)
);
```

The game checks whether the snake's head reaches the food.

```java
if (newHead.equals(food)) {
    score += 10;
    createFood();
}
```

Each food item increases the score by **10 points** and makes the snake longer.

## 💥 Collision Detection

Collision detection is handled inside the main game loop.

The game checks three types of collision:

### 1. Wall Collision

The game ends if the snake moves outside the board.

```java
if (newHead.x < 0 || newHead.x >= COLS ||
    newHead.y < 0 || newHead.y >= ROWS)
```

### 2. Self Collision

The game also checks whether the snake's head touches its own body.

```java
snake.contains(newHead)
```

### 3. Combined Collision

Both conditions are checked together:

```java
if (newHead.x < 0 || newHead.x >= COLS ||
    newHead.y < 0 || newHead.y >= ROWS ||
    snake.contains(newHead)) {

    gameOver = true;
}
```

## ⏱️ Game Loop

The game uses Java Swing's `Timer` to repeatedly update the game.

```java
timer = new Timer(120, e -> gameLoop());
```

The game loop:

1. Reads the current direction.
2. Calculates the new head position.
3. Checks collision.
4. Moves the snake.
5. Checks for food.
6. Updates the score.
7. Repaints the game board.

## 📅 Weekly Development

### Week 1 — Game Window & Basic Board

Created the initial Java Swing project and designed the basic game window.

Implemented:

* JFrame game window
* GamePanel
* Game board
* Initial snake
* Basic graphical display

At this stage, the snake was displayed on the board but could not move.

### Week 2 — Snake Movement & Keyboard Control

Added the basic movement system and keyboard controls.

Implemented:

* Snake movement
* Game loop
* Java Timer
* Arrow-key controls
* Direction management
* Basic wall boundary checking

The player could now control the snake using the keyboard.

### Week 3 — Food, Score & Collision

Added the main gameplay mechanics.

Implemented:

* Random food generation
* Food collection
* Snake growth
* Score system
* Wall collision
* Self-collision
* Game Over system

The game became fully playable at this stage.

### Week 4 — Pause, Restart & Final Version

Added the remaining features and completed the game.

Implemented:

* Pause / Resume system
* Restart system
* Final Game Over screen
* Score display
* Improved game display
* Final testing and debugging

The complete Snake Game was finalized and prepared for project submission.

## 🏆 Game Rules

* Control the snake using the **Arrow Keys**.
* Eat the red food to increase the score.
* Each food item gives **10 points**.
* Eating food makes the snake longer.
* Do not hit the walls.
* Do not hit the snake's own body.
* Hitting a wall or the snake's body results in **Game Over**.
* Press `P` to pause or resume the game.
* Press `R` after Game Over to restart the game.

## 🔮 Future Improvements

* Better graphics and animations
* Different food types
* High-score system
* Increasing snake speed
* Multiple difficulty levels
* Sound effects and background music
* Main menu
* Start screen
* Multiple game modes
* Improved game-over screen
* Save high scores

## 👨‍💻 Project Information

**Project:** Snake Game
**Language:** Java
**GUI:** Java Swing / AWT
**Data Structure:** LinkedList
**Type:** University Project
**Development:** 4 Weeks
**Status:** Completed
