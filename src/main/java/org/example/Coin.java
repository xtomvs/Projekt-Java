package org.example;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Coin {

    private BufferedImage image;
    private Point pos;

    public Coin(int x, int y) {
        loadImage();

        pos = new Point(x, y);
    }

    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/images/coin.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading player image: " + e.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                pos.x * Board.TILE_SIZE,
                pos.y * Board.TILE_SIZE,
                observer
        );
    }

    public Point getPos() {
        return pos;
    }

}