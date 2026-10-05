package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        setTitle("Snake Game - Week 9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new GamePanel());

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SnakeGame().setVisible(true);
        });
    }

    static class GamePanel extends JPanel implements ActionListener, KeyListener {

        final int SIZE = 25;
        final int COLS = 24;
        final int ROWS = 20;

        LinkedList<Point> snake = new LinkedList<>();

        Point food;

        Random random = new Random();

        Timer timer;

        int score = 0;

        boolean gameOver = false;
        boolean paused = false;

        enum Direction {
            UP, DOWN, LEFT, RIGHT
        }

        Direction direction = Direction.RIGHT;

        GamePanel() {

            setPreferredSize(
                new Dimension(COLS * SIZE, ROWS * SIZE + 50)
            );

            setBackground(Color.BLACK);

            setFocusable(true);
            addKeyListener(this);

            initializeGame();

            timer = new Timer(120, this);
            timer.setCoalesce(true);
            timer.start();

            requestFocusInWindow();
        }

        // Initialize Game
        void initializeGame() {

            snake.clear();

            snake.add(new Point(10, 10));
            snake.add(new Point(9, 10));
            snake.add(new Point(8, 10));

            score = 0;

            direction = Direction.RIGHT;

            gameOver = false;
            paused = false;

            createFood();
        }

        // Create Food
        void createFood() {

            do {

                food = new Point(
                    random.nextInt(COLS),
                    random.nextInt(ROWS)
                );

            } while (snake.contains(food));
        }

        // Move Snake
        void moveSnake() {

            Point head = snake.getFirst();

            Point newHead = new Point(head);

            switch (direction) {

                case UP:
                    newHead.y--;
                    break;

                case DOWN:
                    newHead.y++;
                    break;

                case LEFT:
                    newHead.x--;
                    break;

                case RIGHT:
                    newHead.x++;
                    break;
            }

            // Wall Collision
            if (newHead.x < 0 ||
                newHead.x >= COLS ||
                newHead.y < 0 ||
                newHead.y >= ROWS) {

                endGame();
                return;
            }

            // Self Collision
            if (snake.contains(newHead)) {

                endGame();
                return;
            }

            snake.addFirst(newHead);

            // Food Collision
            if (newHead.equals(food)) {

                score++;

                createFood();

            } else {

                snake.removeLast();
            }
        }

        // End Game
        void endGame() {

            gameOver = true;

            timer.stop();

            repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!gameOver && !paused) {

                moveSnake();

                repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            // Draw Snake
            for (int i = 0; i < snake.size(); i++) {

                Point p = snake.get(i);

                if (i == 0) {

                    g.setColor(Color.PINK);

                } else {

                    g.setColor(new Color(50, 180, 50));
                }

                g.fillRect(
                    p.x * SIZE,
                    p.y * SIZE,
                    SIZE - 2,
                    SIZE - 2
                );
            }

            // Draw Food
            g.setColor(Color.RED);

            g.fillOval(
                food.x * SIZE,
                food.y * SIZE,
                SIZE - 2,
                SIZE - 2
            );
            // Score Background
            g.setColor(Color.DARK_GRAY);

             g.fillRect(
            0,
             ROWS * SIZE,
             COLS * SIZE,
              50
             );
            // Draw Score
            g.setColor(Color.WHITE);

            g.setFont(
                new Font("Arial", Font.BOLD, 18)
            );

            g.drawString(
                "Score: " + score,
                10,
                ROWS * SIZE + 32
            );

            // Draw Pause Screen
            if (paused && !gameOver) {

                g.setColor(
                    new Color(0, 0, 0, 170)
                );

                g.fillRect(
                    0,
                    0,
                    COLS * SIZE,
                    ROWS * SIZE
                );

                g.setColor(Color.YELLOW);

                g.setFont(
                    new Font("Arial", Font.BOLD, 36)
                );

                String pauseText = "PAUSED";

                FontMetrics fm = g.getFontMetrics();

                int x =
                    (COLS * SIZE - fm.stringWidth(pauseText)) / 2;

                int y =
                    (ROWS * SIZE) / 2;

                g.drawString(
                    pauseText,
                    x,
                    y
                );

                g.setFont(
                    new Font("Arial", Font.PLAIN, 16)
                );

                String resumeText =
                    "Press P to Resume";

                FontMetrics fm2 = g.getFontMetrics();

                int x2 =
                    (COLS * SIZE - fm2.stringWidth(resumeText)) / 2;

                g.drawString(
                    resumeText,
                    x2,
                    y + 35
                );
            }

            // Game Over Screen
            if (gameOver) {

                g.setColor(
                    new Color(0, 0, 0, 170)
                );

                g.fillRect(
                    0,
                    0,
                    COLS * SIZE,
                    ROWS * SIZE
                );

                g.setColor(Color.RED);

                g.setFont(
                    new Font("Arial", Font.BOLD, 36)
                );

                String message = "GAME OVER";

                FontMetrics fm = g.getFontMetrics();

                int x =
                    (COLS * SIZE - fm.stringWidth(message)) / 2;

                int y =
                    (ROWS * SIZE) / 2 - 20;

                g.drawString(
                    message,
                    x,
                    y
                );

                // Final Score
                g.setColor(Color.WHITE);

                g.setFont(
                    new Font("Arial", Font.BOLD, 20)
                );

                String scoreText =
                    "Final Score: " + score;

                FontMetrics fm2 = g.getFontMetrics();

                int x2 =
                    (COLS * SIZE - fm2.stringWidth(scoreText)) / 2;

                g.drawString(
                    scoreText,
                    x2,
                    y + 40
                );

                // Restart Instruction
                g.setFont(
                    new Font("Arial", Font.PLAIN, 16)
                );

                String restartText =
                    "Press R to Restart";

                FontMetrics fm3 = g.getFontMetrics();

                int x3 =
                    (COLS * SIZE - fm3.stringWidth(restartText)) / 2;

                g.drawString(
                    restartText,
                    x3,
                    y + 70
                );
            }
        }

        // Restart Game
        void restartGame() {

            initializeGame();

            timer.setDelay(120);

            timer.start();

            requestFocusInWindow();

            repaint();
        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:

                    if (direction != Direction.DOWN) {

                        direction = Direction.UP;
                    }

                    break;

                case KeyEvent.VK_DOWN:

                    if (direction != Direction.UP) {

                        direction = Direction.DOWN;
                    }

                    break;

                case KeyEvent.VK_LEFT:

                    if (direction != Direction.RIGHT) {

                        direction = Direction.LEFT;
                    }

                    break;

                case KeyEvent.VK_RIGHT:

                    if (direction != Direction.LEFT) {

                        direction = Direction.RIGHT;
                    }

                    break;

                // Pause / Resume
                case KeyEvent.VK_P:

                    if (!gameOver) {

                        paused = !paused;

                        repaint();
                    }

                    break;

                // Restart
                case KeyEvent.VK_R:

                    if (gameOver) {

                        restartGame();
                    }

                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
