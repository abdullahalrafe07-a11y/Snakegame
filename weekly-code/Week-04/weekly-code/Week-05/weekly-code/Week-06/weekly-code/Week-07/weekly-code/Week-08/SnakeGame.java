package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        setTitle("Snake Game - Week 8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new GamePanel());

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new SnakeGame().setVisible(true);
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
            timer.start();
        }

        // Initialize game
        void initializeGame() {

            snake.clear();

            snake.add(new Point(10, 10));
            snake.add(new Point(9, 10));
            snake.add(new Point(8, 10));

            score = 0;

            direction = Direction.RIGHT;

            gameOver = false;

            createFood();
        }

        // Create food at random position
        void createFood() {

            do {

                food = new Point(
                    random.nextInt(COLS),
                    random.nextInt(ROWS)
                );

            } while (snake.contains(food));
        }

        // Move snake
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

            // Wall collision
            if (newHead.x < 0 ||
                newHead.x >= COLS ||
                newHead.y < 0 ||
                newHead.y >= ROWS) {

                gameOver = true;
                timer.stop();
                repaint();

                return;
            }

            // Self collision
            if (snake.contains(newHead)) {

                gameOver = true;
                timer.stop();
                repaint();

                return;
            }

            // Add new head
            snake.addFirst(newHead);

            // Food collision
            if (newHead.equals(food)) {

                score++;

                createFood();

            } else {

                snake.removeLast();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!gameOver) {

                moveSnake();

                repaint();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            // Draw snake
            for (int i = 0; i < snake.size(); i++) {

                Point p = snake.get(i);

                if (i == 0) {

                    // Snake head
                    g.setColor(Color.PINK);

                } else {

                    // Snake body
                    g.setColor(new Color(50, 180, 50));
                }

                g.fillRect(
                    p.x * SIZE,
                    p.y * SIZE,
                    SIZE - 2,
                    SIZE - 2
                );
            }

            // Draw food
            g.setColor(Color.RED);

            g.fillOval(
                food.x * SIZE,
                food.y * SIZE,
                SIZE - 2,
                SIZE - 2
            );

            // UI - Score
            g.setColor(Color.WHITE);

            g.setFont(
                new Font("Arial", Font.BOLD, 18)
            );

            g.drawString(
                "Score: " + score,
                10,
                ROWS * SIZE + 32
            );

            // Game Over Screen
            if (gameOver) {

                // Dark overlay
                g.setColor(
                    new Color(0, 0, 0, 170)
                );

                g.fillRect(
                    0,
                    0,
                    COLS * SIZE,
                    ROWS * SIZE
                );

                // Game Over text
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

                g.drawString(message, x, y);

                // Final score
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

                // Restart instruction
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

        // Restart game
        void restartGame() {

            initializeGame();

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

                // Restart after Game Over
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
