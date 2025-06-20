package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

public class Player extends Entity {
    private int score;
    private Board board;

    public Player(Board board) {
        super("/images/player.png", 0, 0);
        this.board = board;
        score = 0;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Point nextPos = new Point(pos);

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            nextPos.translate(0, -1);
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            nextPos.translate(1, 0);
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            nextPos.translate(0, 1);
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            nextPos.translate(-1, 0);
        }

        if (nextPos.x < 0) nextPos.x = Board.COLUMNS - 1;
        if (nextPos.x >= Board.COLUMNS) nextPos.x = 0;
        if (nextPos.y < 0) nextPos.y = Board.ROWS - 1;
        if (nextPos.y >= Board.ROWS) nextPos.y = 0;

        if (!board.isObstacleAt(nextPos)) {
            pos = nextPos;
        }
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
