package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public abstract class Entity {
    protected BufferedImage image;
    protected Point pos;

    public Entity(String imagePath, int x, int y) {
        loadImage(imagePath);
        this.pos = new Point(x, y);
    }

    private void loadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                pos.x * Board.TILE_SIZE,
                pos.y * Board.TILE_SIZE + Board.HUD_HEIGHT,
                observer
        );
    }

    public Point getPos() {
        return pos;
    }
}
