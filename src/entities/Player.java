package entities;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        playerSetter();
        getPlayerImage();
    }

    public void playerSetter() {
        xCords = 100;
        yCords = 100;
        speed = 7;
        dir = "down";
    }

    public void getPlayerImage() {

        try {
            upFr1 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronUpFr1.png"));
            upFr2 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronUpFr2.png"));
            downFr1 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronDownFr1.png"));
            downFr2 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronDownFr2.png"));
            leftFr1 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronLeftFr1.png"));
            leftFr2 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronLeftFr2.png"));
            rightFr1 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronRightFr1.png"));
            rightFr2 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronRightFr2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates the movement of the player by continuously checking if the player is holding that key
     * the first 4 ifs are for diagonal directions, the last 4 are for vertical and horizontal
     */
    public void update() {
        if (keyHandler.upDirPr || keyHandler.downDirPr || keyHandler.leftDirPr || keyHandler.rightDirPr) {

            if (keyHandler.upDirPr && keyHandler.leftDirPr) {
                yCords -= speed / 1.5;
                xCords -= speed / 1.5;
            } else if (keyHandler.upDirPr && keyHandler.rightDirPr) {
                yCords -= speed / 1.5;
                xCords += speed / 1.5;
            } else if (keyHandler.downDirPr && keyHandler.leftDirPr) {
                yCords += speed / 1.5;
                xCords -= speed / 1.5;
            } else if (keyHandler.downDirPr && keyHandler.rightDirPr) {
                yCords += speed / 1.5;
                xCords += speed / 1.5;
            } else if (keyHandler.upDirPr) {
                dir = "up";
                yCords -= speed;
            } else if (keyHandler.downDirPr) {
                dir = "down";
                yCords += speed;
            } else if (keyHandler.leftDirPr) {
                dir = "left";
                xCords -= speed;
            } else if (keyHandler.rightDirPr) {
                dir = "right";
                xCords += speed;
            }

            spriteCounter++;
            if (spriteCounter > 5) {
                if (spriteFrame == 1) {
                    spriteFrame = 2;
                } else if (spriteFrame == 2) {
                    spriteFrame = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2D) {

        /*
        g2D.setColor(Color.white);
        g2D.fillRect(xCords, yCords, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
         */
        BufferedImage image = null;
        switch (dir) {
            case ("up"):
                if (spriteFrame == 1) {
                    image = upFr1;
                }
                if (spriteFrame == 2) {
                    image = upFr2;
                }
                break;
            case ("down"):
                if (spriteFrame == 1) {
                    image = downFr1;
                }
                if (spriteFrame == 2) {
                    image = downFr2;
                }
                break;
            case ("left"):
                if (spriteFrame == 1) {
                    image = leftFr1;
                }
                if (spriteFrame == 2) {
                    image = leftFr2;
                }
                break;
            case ("right"):
                if (spriteFrame == 1) {
                    image = rightFr1;
                }
                if (spriteFrame == 2) {
                    image = rightFr2;
                }
                break;
        }
        g2D.drawImage(image, xCords, yCords, gamePanel.scaledTileSize, gamePanel.scaledTileSize, null);
    }
}
