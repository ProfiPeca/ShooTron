package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int xCords, yCords;
    public int speed;

    public BufferedImage upFr1, upFr2, downFr1, downFr2, leftFr1, leftFr2, rightFr1, rightFr2;

    public String dir;
    public int spriteCounter = 0;
    public int spriteFrame = 1;
    public boolean entityCollision = false;
    public Rectangle collisionBox;
    public int defColX, defColY;
}
