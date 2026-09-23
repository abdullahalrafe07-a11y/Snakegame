package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {

    // ================= GAME WINDOW =================

    public SnakeGame() {

        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(new GamePanel());

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        new SnakeGame().setVisible(true);
    }


    // ================= GAME PANEL =================

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
        boolean paused = false;


        // ================= BOARD SETUP =================

        GamePanel() {

            setPreferredSize(
                new Dimension(
                    COLS * SIZE,
                    ROWS * SIZE + 50
                )
            );

            setBackground(Color.BLACK);
            setFocusable(true);

            setupKeys();
            startGame();
        }


        // ================= START GAME =================

        void startGame() {

            snake.clear();

            snake.add(new Point(10, 10));
            snake.add(new Point(9, 10));
            snake.add(new Point(8, 10));

            direction = Direction.RIGHT;
            nextDirection = Direction.RIGHT;

            score = 0;

            gameOver = false;
            paused = false;

            createFood();

            if (timer != null)
                timer.stop();

            timer = new Timer(
                120,
                e -> gameLoop()
            );

            timer.start();
        }


        // ================= GAME LOOP =================

        void gameLoop() {

            if (gameOver || paused)
                return;

            direction = nextDirection;

            Point head = snake.getFirst();

            Point newHead = new Point(head);


            // Snake Movement

            if (direction == Direction.UP)
                newHead.y--;

            if (direction == Direction.DOWN)
                newHead.y++;

            if (direction == Direction.LEFT)
                newHead.x--;

            if (direction == Direction.RIGHT)
                newHead.x++;


            // ================= COLLISION =================

            if (newHead.x < 0 ||
                newHead.x >= COLS ||
                newHead.y < 0 ||
                newHead.y >= ROWS ||
                snake.contains(newHead)) {

                gameOver = true;

                timer.stop();

                repaint();

                return;
            }


            snake.addFirst(newHead);


            // ================= FOOD + SCORE =================

            if (newHead.equals(food)) {

                score += 10;

                createFood();

            } else {

                snake.removeLast();
            }

            repaint();
        }


        // ================= CREATE FOOD =================

        void createFood() {

            do {

                food = new Point(
                    random.nextInt(COLS),
                    random.nextInt(ROWS)
                );

            } while (snake.contains(food));
        }


        // ================= KEYBOARD CONTROL =================

        void setupKeys() {

            bindKey("UP", "up");
            bindKey("DOWN", "down");
            bindKey("LEFT", "left");
            bindKey("RIGHT", "right");

            bindKey("P", "pause");
            bindKey("R", "restart");
        }


        void bindKey(String key, String action) {

            getInputMap(
                WHEN_IN_FOCUSED_WINDOW
            ).put(
                KeyStroke.getKeyStroke(key),
                action
            );


            getActionMap().put(
                action,
                new AbstractAction() {

                    @Override
                    public void actionPerformed(
                        ActionEvent e
                    ) {

                        // Direction Control

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


                        // ================= PAUSE =================

                        if (action.equals("pause")
                                && !gameOver)

                            paused = !paused;


                        // ================= RESTART =================

                        if (action.equals("restart")
                                && gameOver)

                            startGame();


                        repaint();
                    }
                }
            );
        }


        // ================= GAME DISPLAY =================

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);


            // Score

            g.setColor(Color.WHITE);

            g.setFont(
                new Font(
                    "Arial",
                    Font.BOLD,
                    18
                )
            );

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


            // ================= PAUSE =================

            if (paused) {

                g.setColor(Color.WHITE);

                g.setFont(
                    new Font(
                        "Arial",
                        Font.BOLD,
                        30
                    )
                );

                g.drawString(
                    "PAUSED",
                    250,
                    300
                );
            }


            // ================= GAME OVER =================

            if (gameOver) {

                g.setColor(Color.WHITE);

                g.setFont(
                    new Font(
                        "Arial",
                        Font.BOLD,
                        30
                    )
                );

                g.drawString(
                    "GAME OVER",
                    210,
                    280
                );


                g.setFont(
                    new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                    )
                );

                g.drawString(
                    "Press R to Restart",
                    220,
                    315
                );
            }
        }
    }


    // ================= DIRECTION =================

    enum Direction {

        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
