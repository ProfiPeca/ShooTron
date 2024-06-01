package entities;

import main.GameOptimizer;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class DefaultEntity {

    protected GamePanel gamePanel;
    protected GameOptimizer gameOptimizer;
    public int xCords, yCords;
    public int speed;

    public BufferedImage upFr1, upFr2, downFr1, downFr2, leftFr1, leftFr2, rightFr1, rightFr2;

    public String dir = "down";
    protected String filePath = "";
    public int spriteCounter = 0;
    public int spriteFrame = 1;
    public boolean entityCollision = false;
    public boolean entityCollisionWPlayer = false;
    public Rectangle collisionBox = new Rectangle(0, 0, 80, 80);
    public int defColX, defColY;
    public String[] dialogueArray = new String[20];
    public int dialogueNumber = 0;
    protected int actionCoolDown = 0;
    protected int currHP, maxHP;
    public String entityName;
    public boolean hasCollision = false;

    public DefaultEntity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * pre-scales all entities pngs
     * filePath determines the folder that the method will try to find the png in, it's assigned in the specific entity child
     *
     * @param imageName name of the png that you want to pre-scale
     * @return pre-scaled image that you chose
     */
    public BufferedImage entityIMGPreScale(String imageName) {
        GameOptimizer gameOptimizer = new GameOptimizer();
        BufferedImage preScaledImage = null;
        try {
            preScaledImage = ImageIO.read(getClass().getResourceAsStream(filePath + imageName + ".png"));
            preScaledImage = gameOptimizer.imagePreScale(preScaledImage, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return preScaledImage;
    }

    public void entityAction() {
    }

    public void entityDialogue() {
    }

    public void entityUpdate() {
        entityAction();
        entityCollision = false;
        gamePanel.gameCollision.collisionTileChecker(this);
        gamePanel.gameCollision.collisionObjectChecker(this, false);
        gamePanel.gameCollision.collisionPlayerChecker(this);

        if (entityCollision == false) {
            switch (dir) {
                case ("up"):
                    yCords -= speed;
                    break;
                case ("down"):
                    yCords += speed;
                    break;
                case ("left"):
                    xCords -= speed;
                    break;
                case ("right"):
                    xCords += speed;
                    break;
            }
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

    public void entityDraw(Graphics2D g2D) {
        BufferedImage entityImage = null;
        int xScreen = xCords - gamePanel.player.xCords + gamePanel.player.screenX;
        int yScreen = yCords - gamePanel.player.yCords + gamePanel.player.screenY;

        if (xCords + gamePanel.scaledTileSize > gamePanel.player.xCords - gamePanel.player.screenX && xCords - gamePanel.scaledTileSize < gamePanel.player.xCords + gamePanel.player.screenX && yCords + gamePanel.scaledTileSize > gamePanel.player.yCords - gamePanel.player.screenY && yCords - gamePanel.scaledTileSize < gamePanel.player.yCords + gamePanel.player.screenY) {

            switch (dir) {
                case ("up"):
                    if (spriteFrame == 1) {
                        entityImage = upFr1;
                    }
                    if (spriteFrame == 2) {
                        entityImage = upFr2;
                    }
                    break;
                case ("down"):
                    if (spriteFrame == 1) {
                        entityImage = downFr1;
                    }
                    if (spriteFrame == 2) {
                        entityImage = downFr2;
                    }
                    break;
                case ("left"):
                    if (spriteFrame == 1) {
                        entityImage = leftFr1;
                    }
                    if (spriteFrame == 2) {
                        entityImage = leftFr2;
                    }
                    break;
                case ("right"):
                    if (spriteFrame == 1) {
                        entityImage = rightFr1;
                    }
                    if (spriteFrame == 2) {
                        entityImage = rightFr2;
                    }
                    break;
            }
            g2D.drawImage(entityImage, xScreen, yScreen, gamePanel.scaledTileSize, gamePanel.scaledTileSize, null);
        }
    }

    public int getCurrHP() {
        return currHP;
    }

    public void setCurrHP(int currHP) {
        if (this.currHP > maxHP) {
            this.currHP = maxHP;
        } else {
            this.currHP = currHP;
        }
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
}
