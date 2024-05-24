package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class DefaultObject {

    public BufferedImage image;
    public String objName;
    public boolean hasCollision = false;
    public int xCords, yCords;

    public void drawObject(Graphics2D g2D, GamePanel gamePanel) {
        int xScreen = xCords - gamePanel.player.xCords + gamePanel.player.screenX;
        int yScreen = yCords - gamePanel.player.yCords + gamePanel.player.screenY;

        if (xCords + gamePanel.scaledTileSize > gamePanel.player.xCords - gamePanel.player.screenX && xCords - gamePanel.scaledTileSize < gamePanel.player.xCords + gamePanel.player.screenX && yCords + gamePanel.scaledTileSize > gamePanel.player.yCords - gamePanel.player.screenY && yCords - gamePanel.scaledTileSize < gamePanel.player.yCords + gamePanel.player.screenY) {
            g2D.drawImage(image, xScreen, yScreen, gamePanel.scaledTileSize, gamePanel.scaledTileSize, null);
        }

    }

}