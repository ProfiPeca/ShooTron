package main;

import objects.KeyCard_OBJ;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UserInterface {
    private GamePanel gamePanel;
    private Font interfaceFont;
    private Font levelClearedFont;
    private BufferedImage keyNumber_IMG;

    //PopUpTextParameters
    private String popUpText = "";
    private int popUpTextDuration = 0;
    private boolean activePopUpText = false;
    private boolean levelCleared = false;
    private double levelTimer;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private AlphaComposite UIBackground = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
    private AlphaComposite UIInfo = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        interfaceFont = new Font("Arial", Font.PLAIN, 25);
        levelClearedFont = new Font("Arial", Font.BOLD, 50);

        KeyCard_OBJ keyCard = new KeyCard_OBJ(gamePanel);
        keyNumber_IMG = keyCard.image;
    }

    public void textPopUp(String information) {
        popUpText = information;
        activePopUpText = true;
    }

    /**
     * draws the user interface on top of everything else
     * UIBackground is used to change visibility of anything drawn to around 70%, it is used for drawing a rectangle that makes UI more visible, while still letting player see behind the interface
     * UIInfo sets visibility back to 100%, so you can see the text better
     * if level is cleared by going into the elevator, a text will appear
     */
    public void drawInterface(Graphics2D g2D) {
        if (levelCleared) {
            g2D.setFont(levelClearedFont);
            g2D.setColor(Color.GREEN);

            String clearText;
            int clearTextX, clearTextY, clearTextLength;

            clearText = "LEVEL CLEARED IN: " + decimalFormat.format(levelTimer) + " SECONDS";
            clearTextLength = (int) g2D.getFontMetrics().getStringBounds(clearText, g2D).getWidth();
            clearTextX = (gamePanel.screenWidth - clearTextLength) / 2;
            clearTextY = (gamePanel.screenHeight) / 2;
            g2D.drawString(clearText, clearTextX, clearTextY);

            //stops the game
            gamePanel.gameThread = null;

        } else {
            //Background
            g2D.setColor(Color.BLACK);
            g2D.setComposite(UIBackground);
            g2D.fillRect(0, 0, gamePanel.scaledTileSize * 3, gamePanel.scaledTileSize * 2);
            g2D.fillRect(gamePanel.scaledTileSize * 13 + 70, 0, gamePanel.scaledTileSize * 3, gamePanel.scaledTileSize);
            g2D.setComposite(UIInfo);
            //Interface
            g2D.setFont(interfaceFont);
            g2D.setColor(Color.GREEN);

            g2D.drawImage(keyNumber_IMG, -(gamePanel.scaledTileSize / 4), 0, gamePanel.scaledTileSize, gamePanel.scaledTileSize, null);
            g2D.drawString("KEYCARDS: " + gamePanel.player.keyCardNumber, 40, 50);

            levelTimer += (double) 1 / 60;
            g2D.drawString("TIME: " + decimalFormat.format(levelTimer), gamePanel.scaledTileSize * 14, 50);

            if (activePopUpText) {
                g2D.setFont(g2D.getFont().deriveFont(30f));
                g2D.drawString(popUpText, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
                popUpTextDuration++;
                if (popUpTextDuration > 120) {
                    popUpTextDuration = 0;
                    activePopUpText = false;
                }
            }
        }
    }

    public void setLevelCleared(boolean levelCleared) {
        this.levelCleared = levelCleared;
    }
}
