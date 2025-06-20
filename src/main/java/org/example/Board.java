package org.example;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, KeyListener {

    private final int DELAY = 25;
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 10;
    public static final int COLUMNS = 20;
    public static final int NUM_COINS = 5;
    public static final int POINTS_PER_COIN = 250;
    private static final long serialVersionUID = 490905409104883233L;
    private static final int MAX_TIME_SECONDS = 60;

    private Timer timer;
    private Player player;
    private ArrayList<Coin> coins;
    private int gameTicks = 0;
    private BufferedImage backgroundImage;
    private BufferedImage winImage;
    private BufferedImage loseImage;
    private boolean gameOver = false;
    private static final int WINNING_SCORE = 10000;
    private double remainingTime = 30.0;
    private int ticksSinceLastCheck = 0;
    private static final double TIME_PER_TICK = 0.025;
    private int highScore = 0;
    private final File highScoreFile = new File("highscore.txt");


    public Board() {
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        setBackground(new Color(200, 220, 255));

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/background.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading background: " + e.getMessage());
        }

        player = new Player();
        coins = populateCoins();

        loadHighScore();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        player.tick();

        for (Coin coin : coins) {
            coin.tick();
        }

        collectCoins();
        removeExpiredCoins();
        maybeSpawnRandomCoin();

        remainingTime -= TIME_PER_TICK;
        if (remainingTime <= 0) {
            gameOver = true;
            timer.stop();
            repaint();
        }

        int currentScore = Integer.parseInt(player.getScore());
        if (currentScore > highScore) {
            highScore = currentScore;
            saveHighScore();
        }

        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            drawEndScreen(g);
            return;
        }

        drawBackground(g);
        drawHUD(g);
        for (Coin coin : coins) {
            coin.draw(g, this);
        }
        player.draw(g, this);

        Toolkit.getDefaultToolkit().sync();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (gameOver && key == KeyEvent.VK_R) {
            restartGame();
            return;
        }

        if (!gameOver) {
            player.keyPressed(e);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void drawBackground(Graphics g) {
        if (backgroundImage != null) {
            for (int y = 0; y < getHeight(); y += backgroundImage.getHeight()) {
                for (int x = 0; x < getWidth(); x += backgroundImage.getWidth()) {
                    g.drawImage(backgroundImage, x, y, this);
                }
            }
        }
    }


    private void drawHUD(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(new Color(30, 201, 139));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));

        String scoreText = "$" + player.getScore();
        String highText = "Best: $" + highScore;
        String timeText = formatTime(remainingTime);


        g2d.drawString(scoreText, 20, TILE_SIZE * ROWS - 40);
        g2d.drawString(highText, 20, TILE_SIZE * ROWS - 15);
        g2d.drawString(timeText, TILE_SIZE * COLUMNS - 100, TILE_SIZE * ROWS - 15);
    }



    private ArrayList<Coin> populateCoins() {
        ArrayList<Coin> coinList = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < NUM_COINS; i++) {
            int coinX = rand.nextInt(COLUMNS);
            int coinY = rand.nextInt(ROWS);
            coinList.add(new Coin(coinX, coinY));
        }

        return coinList;
    }

    private void collectCoins() {
        ArrayList<Coin> collectedCoins = new ArrayList<>();
        ArrayList<Coin> coinsToAdd = new ArrayList<>();
        for (Coin coin : coins) {
            if (player.getPos().equals(coin.getPos())) {
                player.addScore(coin.getValue());
                collectedCoins.add(coin);
                remainingTime += 0.25;
                coinsToAdd.add(generateRandomCoin());
            }
        }
        coins.removeAll(collectedCoins);
        coins.addAll(coinsToAdd);
    }

    private Coin generateRandomCoin() {
        Random rand = new Random();
        Point point;
        do {
            int coinX = rand.nextInt(COLUMNS);
            int coinY = rand.nextInt(ROWS);
            point = new Point(coinX, coinY);
        } while (positionIsOccupied(point));

        return new Coin(point.x, point.y);
    }


    private void removeExpiredCoins() {
        coins.removeIf(Coin::isExpired);
    }

    private void maybeSpawnRandomCoin() {
        gameTicks++;
        if (gameTicks % 50 == 0) {
            Random rand = new Random();

            if (rand.nextDouble() < 0.3) {
                Point point;
                do {
                    point = new Point(rand.nextInt(COLUMNS), rand.nextInt(ROWS));
                } while (positionIsOccupied(point));

                if (rand.nextDouble() < 0.2) {
                    coins.add(new SpecialCoin(point.x, point.y));
                } else {
                    coins.add(new Coin(point.x, point.y));
                }
            }
        }
    }


    private boolean positionIsOccupied(Point p) {
        if (player.getPos().equals(p)) {
            return true;
        }
        for (Coin coin : coins) {
            if (coin.getPos().equals(p)) {
                return true;
            }
        }
        return false;
    }


    private void restartGame() {
        player = new Player();
        coins = populateCoins();
        gameOver = false;
        gameTicks = 0;
        remainingTime = 30.0;
        timer.start();
    }


    private String formatTime(double seconds) {
        int whole = (int) seconds;
        int tenths = (int) ((seconds - whole) * 10);
        return String.format("%02d.%d", whole, tenths);
    }


    private void drawEndScreen(Graphics g) {
        g.setColor(new Color(40, 40, 80));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("Time's up!", getWidth() / 2 - 160, getHeight() / 2 - 40);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Your score: " + player.getScore(), getWidth() / 2 - 100, getHeight() / 2 + 20);
        g.drawString("High score: $" + highScore, getWidth()/2 - 100, getHeight()/2 + 60);
        g.drawString("Press R to try again", getWidth() / 2 - 120, getHeight() / 2 + 100);
    }

    private void loadHighScore() {
        if (highScoreFile.exists()) {
            try (Scanner scanner = new Scanner(highScoreFile)) {
                if (scanner.hasNextInt()) {
                    highScore = scanner.nextInt();
                }
            } catch (IOException e) {
                System.out.println("Couldn't read high score: " + e.getMessage());
            }
        }
    }

    private void saveHighScore() {
        try (PrintWriter writer = new PrintWriter(highScoreFile)) {
            writer.println(highScore);
        } catch (IOException e) {
            System.out.println("Couldn't save high score: " + e.getMessage());
        }
    }








}