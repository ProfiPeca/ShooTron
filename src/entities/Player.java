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

    public void update() {

        if (keyHandler.upDirPr == true) {
            dir = "up";
            yCords -= speed;
        } else if (keyHandler.downDirPr == true) {
            dir = "down";
            yCords += speed;
        } else if (keyHandler.leftDirPr == true) {
            dir = "left";
            xCords -= speed;
        } else if (keyHandler.rightDirPr == true) {
            dir = "right";
            xCords += speed;
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
                image = upFr1;
                break;
            case ("down"):
                image = downFr1;
                break;
            case ("left"):
                image = leftFr1;
                break;
            case ("right"):
                image = rightFr1;
                break;
        }
        g2D.drawImage(image, xCords, yCords, gamePanel.scaledTileSize, gamePanel.scaledTileSize, null);
    }
}
