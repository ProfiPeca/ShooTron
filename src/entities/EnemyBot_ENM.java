package entities;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBot_ENM extends DefaultEntity {

    public EnemyBot_ENM(GamePanel gamePanel) {
        super(gamePanel);

        entityName = "ramBot";
        speed = 3;
        maxHP = 30;
        currHP = maxHP;
        collisionBox.y = 22;
        collisionBox.x = 10;
        collisionBox.height = 70;
        collisionBox.width = 50;

        getLilDroidImage();
        entityAction();
    }

    public void getLilDroidImage() {
        upFr1 = lilDroidIMGPreScale("lilDroidUpFr1");
        upFr2 = lilDroidIMGPreScale("lilDroidUpFr2");
        downFr1 = lilDroidIMGPreScale("lilDroidDownFr1");
        downFr2 = lilDroidIMGPreScale("lilDroidDownFr2");
        leftFr1 = lilDroidIMGPreScale("lilDroidLeftFr1");
        leftFr2 = lilDroidIMGPreScale("lilDroidLeftFr2");
        rightFr1 = lilDroidIMGPreScale("lilDroidRightFr1");
        rightFr2 = lilDroidIMGPreScale("lilDroidRightFr2");
    }

    public BufferedImage lilDroidIMGPreScale(String imageName) {
        this.filePath = "/bots/";
        return entityIMGPreScale(imageName);
    }

    public void entityAction() {
        actionCoolDown++;
        this.dmgCooldown++;

        if (actionCoolDown == 90) {
            Random randomDir = new Random();
            int i = randomDir.nextInt(100) + 1;

            if (i <= 25) {
                dir = "up";
            }
            if (i > 25 && i <= 50) {
                dir = "down";
            }
            if (i > 50 && i <= 75) {
                dir = "left";
            }
            if (i > 75) {
                dir = "right";
            }
            actionCoolDown = 0;
        }
        if (entityCollisionWPlayer && dmgCooldown >= 15) {
            gamePanel.player.setCurrHP(gamePanel.player.getCurrHP() - 15);
            dmgCooldown = 0;
            gamePanel.playSound_EFFECT(3);

        }
    }

    /**
     * removes enemy if its hp is zero or lower after it plays the animation
     */
    public void entityUpdate() {
        super.entityUpdate();
        if (currHP <= 0) {
            isExploding = true;
        }
        if (isAlive == false) {
            for (int i = 0; i < gamePanel.entityArray.length; i++) {
                if (gamePanel.entityArray[i] != null) {
                    if (gamePanel.entityArray[i].equals(this)) {
                        gamePanel.entityArray[i] = null;
                    }
                }
            }
        }
    }
}
