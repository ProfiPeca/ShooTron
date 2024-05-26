package main;

import objects.KeyCard_OBJ;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UserInterface {
    private GamePanel gamePanel;
    private Graphics2D g2D;
    private Font interfaceFont, levelClearedFont;

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
        this.g2D = g2D;
        g2D.setColor(Color.GREEN);
        g2D.setFont(interfaceFont);

        if (gamePanel.currGameState == gamePanel.gameRunning) {

        }
        if (gamePanel.currGameState == gamePanel.gamePaused) {
            pauseInterface();
        }
    }

    public void pauseInterface() {
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 90f));
        String interfaceText = "GAME PAUSED";
        int screenX = textCenterX(interfaceText);
        int screenY = gamePanel.screenHeight / 2;
        g2D.drawString(interfaceText, screenX, screenY);

    }
    public int textCenterX(String interfaceText) {
        int interfaceTextLength = (int) g2D.getFontMetrics().getStringBounds(interfaceText, g2D).getWidth();
        int screenX = (gamePanel.screenWidth - interfaceTextLength) / 2;
        return screenX;
    }

    public void setLevelCleared(boolean levelCleared) {
        this.levelCleared = levelCleared;
    }
}
