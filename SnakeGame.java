package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class SnakeGame extends JFrame {

    public SnakeGame() {

        setTitle("Snake Game - Week 2");
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

        Direction direction = Direction.RIGHT;
        Direction nextDirection = Direction.RIGHT;

        Timer timer;

        GamePanel() {

            setPreferredSize(
                new Dimension(COLS * SIZE, ROWS * SIZE)
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

            timer = new Timer(120, e -> gameLoop());
            timer.start();
        }

        void gameLoop() {

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

            // Wall collision
            if (newHead.x < 0 || newHead.x >= COLS ||
                newHead.y < 0 || newHead.y >= ROWS) {

                timer.stop();
                return;
            }

            snake.addFirst(newHead);
            snake.removeLast();

            repaint();
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

            for (int i = 0; i < snake.size(); i++) {

                Point p = snake.get(i);

                g.setColor(
                    i == 0
                    ? Color.PINK
                    : new Color(50, 180, 50)
                );

                g.fillRect(
                    p.x * SIZE,
                    p.y * SIZE,
                    SIZE - 2,
                    SIZE - 2
                );
            }
        }
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
