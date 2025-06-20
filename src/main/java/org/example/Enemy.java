package org.example;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Enemy extends Entity {

    private Random rand = new Random();
    private int moveCooldown = 0;
    private static final int MAX_COOLDOWN = 20;

    public Enemy(int x, int y) {
        super("/images/enemy.png", x, y);
    }

    public void moveRandomly() {
        if (moveCooldown > 0) {
            moveCooldown--;
            return;
        }

        Point next = new Point(pos);
        int dir = rand.nextInt(4);

        switch (dir) {
            case 0 -> next.translate(0, -1);
            case 1 -> next.translate(1, 0);
            case 2 -> next.translate(0, 1);
            case 3 -> next.translate(-1, 0);
        }

        // Wrap-around
        if (next.x < 0) next.x = Board.COLUMNS - 1;
        if (next.x >= Board.COLUMNS) next.x = 0;
        if (next.y < 0) next.y = Board.ROWS - 1;
        if (next.y >= Board.ROWS) next.y = 0;

        pos = next;
        moveCooldown = MAX_COOLDOWN; // zresetuj odliczanie
    }


    public void draw(Graphics g, ImageObserver observer) {
        super.draw(g, observer);
    }
}
