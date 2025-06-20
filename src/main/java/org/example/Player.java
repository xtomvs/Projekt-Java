package org.example;

import java.awt.event.KeyEvent;

public class Player extends Entity {
    private int score;

    public Player() {
        super("/images/player.png", 0, 0);
        score = 0;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) pos.translate(0, -1);
        if (key == KeyEvent.VK_D) pos.translate(1, 0);
        if (key == KeyEvent.VK_S) pos.translate(0, 1);
        if (key == KeyEvent.VK_A) pos.translate(-1, 0);
    }

    public void tick() {
        if (pos.x < 0) {
            pos.x = Board.COLUMNS - 1;
        } else if (pos.x >= Board.COLUMNS) {
            pos.x = 0;
        }

        if (pos.y < 0) {
            pos.y = Board.ROWS - 1;
        } else if (pos.y >= Board.ROWS) {
            pos.y = 0;
        }
    }


    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }
}
