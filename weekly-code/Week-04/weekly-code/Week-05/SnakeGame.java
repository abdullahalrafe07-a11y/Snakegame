package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        setTitle("Snake Game - Week 5");
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

        Timer timer;

        int dx = 1;
        int dy = 0;

        GamePanel() {

            setPreferredSize(
                new Dimension(COLS * SIZE, ROWS * SIZE)
            );

            setBackground(Color.BLACK);

            // Initial Snake
            snake.add(new Point(10, 10));
            snake.add(new Point(9, 10));
            snake.add(new Point(8, 10));

            // Keyboard control
            setFocusable(true);
            addKeyListener(this);

            // Game timer
            timer = new Timer(150, this);
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            // Create new head position
            Point head = snake.getFirst();

            int newX = head.x + dx;
            int newY = head.y + dy;

            Point newHead = new Point(newX, newY);

            // Move snake
            snake.addFirst(newHead);
            snake.removeLast();

            repaint();
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
        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if (dy == 0) {
                        dx = 0;
                        dy = -1;
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (dy == 0) {
                        dx = 0;
                        dy = 1;
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if (dx == 0) {
                        dx = -1;
                        dy = 0;
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (dx == 0) {
                        dx = 1;
                        dy = 0;
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
