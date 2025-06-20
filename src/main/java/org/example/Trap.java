package org.example;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Trap extends Entity {

    public static final int TRAP_DAMAGE = 200;
    private int lifeTicks = 400;

    public Trap(int x, int y) {
        super("/images/trap.png", x, y);
    }

    public void draw(Graphics g, ImageObserver observer) {
        super.draw(g, observer);
    }

    public int getDamage() {
        return TRAP_DAMAGE;
    }

    public void tick() {
        lifeTicks--;
    }

    public boolean isExpired() {
        return lifeTicks <= 0;
    }
}
