package org.example;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Coin extends Entity {
    private int lifeTicks;
    private static final int MAX_LIFE_TICKS = 300;

    protected int value = Board.POINTS_PER_COIN;

    public Coin(int x, int y) {
        super("/images/coin.png", x, y);
        lifeTicks = MAX_LIFE_TICKS;
    }

    public void tick() {
        lifeTicks--;
    }

    public boolean isExpired() {
        return lifeTicks <= 0;
    }

    public int getValue() {
        return value;
    }

    protected void setValue(int value) {
        this.value = value;
    }

    protected void setImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading coin image: " + e.getMessage());
        }
    }
}
