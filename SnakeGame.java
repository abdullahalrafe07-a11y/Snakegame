package snakegame;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        setTitle("Snake Game - Week 1");
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

        GamePanel() {

            setPreferredSize(
                new Dimension(COLS * SIZE, ROWS * SIZE)
            );

            setBackground(Color.BLACK);

            // Initial Snake
            snake.add(new Point(10, 10));
            snake.add(new Point(9, 10));
            snake.add(new Point(8, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            // Draw Snake
            for (int i = 0; i < snake.size(); i++) {

                Point p = snake.get(i);

                if (i == 0)
                    g.setColor(Color.PINK);
                else
                    g.setColor(new Color(50, 180, 50));

                g.fillRect(
                    p.x * SIZE,
                    p.y * SIZE,
                    SIZE - 2,
                    SIZE - 2
                );
            }
        }
    }
}