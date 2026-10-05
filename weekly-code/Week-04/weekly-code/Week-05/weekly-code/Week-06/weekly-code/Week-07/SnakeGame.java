package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        setTitle("Snake Game - Week 7");
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

        enum Direction {
            UP, DOWN, LEFT, RIGHT
        }

        Direction direction = Direction.RIGHT;

        boolean gameOver = false;

        GamePanel() {

            setPreferredSize(
                new Dimension(COLS * SIZE, ROWS * SIZE + 40)
            );

            setBackground(Color.BLACK);

            setFocusable(true);
            addKeyListener(this);

            // Initial Snake
            snake.add(new Point(10, 10));
            snake.add(new Point(9, 10));
            snake.add(new Point(8, 10));

            // Create Food
            createFood();

            // Start Timer
            timer = new Timer(120, this);
            timer.start();
        }

        // Create food at a random position
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

                gameOver = true;
                timer.stop();
                repaint();
                return;
            }

            // Self Collision
            if (snake.contains(newHead)) {

                gameOver = true;
                timer.stop();
                repaint();
                return;
            }

            // Add new head
            snake.addFirst(newHead);

            // Food Collision
            if (newHead.equals(food)) {

                score++;

                // Snake grows by keeping the tail
                createFood();

            } else {

                // Remove tail if food was not eaten
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

            // Draw Score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));

            g.drawString(
                "Score: " + score,
                10,
                ROWS * SIZE + 28
            );

            // Game Over
            if (gameOver) {

                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 30));

                String message = "GAME OVER";

                FontMetrics fm = g.getFontMetrics();

                int x = (COLS * SIZE - fm.stringWidth(message)) / 2;
                int y = (ROWS * SIZE) / 2;

                g.drawString(message, x, y);
            }
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
