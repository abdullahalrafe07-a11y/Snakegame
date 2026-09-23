package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {

    public SnakeGame() {

        setTitle("Snake Game - Week 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new GamePanel());

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new SnakeGame().setVisible(true);
    }

    static class GamePanel extends JPanel {

        final int SIZE = 25;
        final int COLS = 24;
        final int ROWS = 20;

        LinkedList<Point> snake = new LinkedList<>();

        Point food;

        Random random = new Random();

        Direction direction = Direction.RIGHT;
        Direction nextDirection = Direction.RIGHT;

        Timer timer;

        int score = 0;
        boolean gameOver = false;

        GamePanel() {

            setPreferredSize(
                new Dimension(COLS * SIZE, ROWS * SIZE + 50)
            );

            setBackground(Color.BLACK);
            setFocusable(true);

            setupKeys();
            startGame();
        }

        void startGame() {

            snake.clear();

            snake.add(new Point(10, 10));
            snake.add(new Point(9, 10));
            snake.add(new Point(8, 10));

            direction = Direction.RIGHT;
            nextDirection = Direction.RIGHT;

            score = 0;
            gameOver = false;

            createFood();

            timer = new Timer(120, e -> gameLoop());
            timer.start();
        }

        void gameLoop() {

            if (gameOver)
                return;

            direction = nextDirection;

            Point head = snake.getFirst();
            Point newHead = new Point(head);

            if (direction == Direction.UP)
                newHead.y--;

            if (direction == Direction.DOWN)
                newHead.y++;

            if (direction == Direction.LEFT)
                newHead.x--;

            if (direction == Direction.RIGHT)
                newHead.x++;

            // Collision
            if (newHead.x < 0 || newHead.x >= COLS ||
                newHead.y < 0 || newHead.y >= ROWS ||
                snake.contains(newHead)) {

                gameOver = true;
                timer.stop();
                repaint();
                return;
            }

            snake.addFirst(newHead);

            // Food
            if (newHead.equals(food)) {

                score += 10;
                createFood();

            } else {

                snake.removeLast();
            }

            repaint();
        }

        void createFood() {

            do {

                food = new Point(
                    random.nextInt(COLS),
                    random.nextInt(ROWS)
                );

            } while (snake.contains(food));
        }

        void setupKeys() {

            bindKey("UP", "up");
            bindKey("DOWN", "down");
            bindKey("LEFT", "left");
            bindKey("RIGHT", "right");
        }

        void bindKey(String key, String action) {

            getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(key), action);

            getActionMap().put(action, new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (action.equals("up")
                            && direction != Direction.DOWN)
                        nextDirection = Direction.UP;

                    if (action.equals("down")
                            && direction != Direction.UP)
                        nextDirection = Direction.DOWN;

                    if (action.equals("left")
                            && direction != Direction.RIGHT)
                        nextDirection = Direction.LEFT;

                    if (action.equals("right")
                            && direction != Direction.LEFT)
                        nextDirection = Direction.RIGHT;
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            // Score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));

            g.drawString(
                "Score: " + score,
                10,
                25
            );

            // Food
            g.setColor(Color.RED);

            g.fillOval(
                food.x * SIZE,
                50 + food.y * SIZE,
                SIZE,
                SIZE
            );

            // Snake
            for (int i = 0; i < snake.size(); i++) {

                Point p = snake.get(i);

                g.setColor(
                    i == 0
                    ? Color.PINK
                    : new Color(50, 180, 50)
                );

                g.fillRect(
                    p.x * SIZE,
                    50 + p.y * SIZE,
                    SIZE - 2,
                    SIZE - 2
                );
            }

            // Game Over
            if (gameOver) {

                g.setColor(Color.WHITE);
                g.setFont(
                    new Font("Arial", Font.BOLD, 30)
                );

                g.drawString(
                    "GAME OVER",
                    210,
                    280
                );
            }
        }
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
