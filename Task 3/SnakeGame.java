import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int UNIT_SIZE = 20;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 100;

    private final ArrayList<Integer> snakeX = new ArrayList<>();
    private final ArrayList<Integer> snakeY = new ArrayList<>();
    private int snakeLength = 1;

    private int foodX;
    private int foodY;

    private char direction = 'R';
    private boolean running = false;

    private final Timer timer;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());

        spawnFood();

        timer = new Timer(DELAY, this);
    }

    public void startGame() {
        running = true;
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, WIDTH, i * UNIT_SIZE);
            }

            g.setColor(Color.green);
            g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.blue);
                } else {
                    g.setColor(Color.cyan);
                }
                g.fillRect(snakeX.get(i), snakeY.get(i), UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + (snakeLength - 1), (WIDTH - metrics.stringWidth("Score: " + (snakeLength - 1))) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    private void spawnFood() {
        Random random = new Random();
        foodX = random.nextInt((WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        foodY = random.nextInt((HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    private void move() {
        for (int i = snakeLength - 1; i > 0; i--) {
            snakeX.set(i, snakeX.get(i - 1));
            snakeY.set(i, snakeY.get(i - 1));
        }

        switch (direction) {
            case 'U':
                snakeY.set(0, snakeY.get(0) - UNIT_SIZE);
                break;
            case 'D':
                snakeY.set(0, snakeY.get(0) + UNIT_SIZE);
                break;
            case 'L':
                snakeX.set(0, snakeX.get(0) - UNIT_SIZE);
                break;
            case 'R':
                snakeX.set(0, snakeX.get(0) + UNIT_SIZE);
                break;
        }
    }

    private void checkFoodCollision() {
        if (snakeX.get(0) == foodX && snakeY.get(0) == foodY) {
            snakeLength++;
            spawnFood();
        }
    }

    private void checkCollision() {
        for (int i = snakeLength - 1; i > 0; i--) {
            if (snakeX.get(0) == snakeX.get(i) && snakeY.get(0) == snakeY.get(i)) {
                running = false;
                break;
            }
        }

        if (snakeX.get(0) < 0 || snakeX.get(0) >= WIDTH || snakeY.get(0) < 0 || snakeY.get(0) >= HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFoodCollision();
            checkCollision();
        }
        repaint();
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && direction != 'R') {
                direction = 'L';
            } else if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && direction != 'L') {
                direction = 'R';
            } else if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && direction != 'D') {
                direction = 'U';
            } else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && direction != 'U') {
                direction = 'D';
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame snakeGame = new SnakeGame();
        frame.add(snakeGame);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        snakeGame.startGame();
    }
}
