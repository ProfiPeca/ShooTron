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

    public String currEntityDialogue = "";
    private boolean activePopUpText = false;
    private boolean levelCleared = false;
    private double levelTimer;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    //sets opacity of drawn objects
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
     * levelTimer displays time that has passed in the level in seconds
     */
    public void drawInterface(Graphics2D g2D) {
        this.g2D = g2D;

        g2D.setColor(Color.GREEN);
        g2D.setFont(interfaceFont);

        if (gamePanel.currGameState == gamePanel.gameRunning) {
            //Background
            g2D.setColor(Color.BLACK);
            g2D.setComposite(UIBackground);
            g2D.fillRect(0, 0, gamePanel.scaledTileSize * 3, gamePanel.scaledTileSize * 2);
            g2D.fillRect(gamePanel.scaledTileSize * 13 + 70, 0, gamePanel.scaledTileSize * 3, gamePanel.scaledTileSize);
            g2D.setComposite(UIInfo);
            //Interface
            //g2D.setFont(interfaceFont);
            g2D.setColor(Color.GREEN);


            g2D.drawString("KEYCARDS: " + gamePanel.player.keyCardNumber, 40, 50);

            levelTimer += (double) 1 / 60;
            g2D.drawString("TIME: " + decimalFormat.format(levelTimer), gamePanel.scaledTileSize * 14, 50);
        }
        if (gamePanel.currGameState == gamePanel.gamePaused) {
            pauseInterface();
        }
        if (gamePanel.currGameState == gamePanel.gameDialogue) {
            dialogueInterface();
        }
    }

    /**
     * changes interface to show GAME PAUSED on the screen
     * also puts black background with some opacity to make the text more visible
     */
    private void pauseInterface() {
        //displays pause background
        g2D.setColor(Color.BLACK);
        g2D.setComposite(UIBackground);

        g2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        //displays game paused text
        g2D.setColor(Color.GREEN);
        g2D.setComposite(UIInfo);

        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 90f));
        String interfaceText = "GAME PAUSED";
        int screenX = textCenterX(interfaceText);
        int screenY = gamePanel.screenHeight / 2;
        g2D.drawString(interfaceText, screenX, screenY);
    }

    private void dialogueInterface() {

        int xPos = gamePanel.scaledTileSize * 2, yPos = gamePanel.scaledTileSize / 2, windowWidth = gamePanel.screenWidth - (gamePanel.scaledTileSize * 4), windowHeight = gamePanel.scaledTileSize * 5;

        dialogueWindow(xPos, yPos, windowWidth, windowHeight);
    }

    private void dialogueWindow(int x, int y, int width, int height) {
        g2D.setColor(Color.BLACK);
        g2D.setComposite(UIBackground);
        g2D.fillRect(x, y, width, height);

        g2D.setColor(Color.GREEN);
        g2D.setStroke(new BasicStroke(10));
        g2D.drawRect(x, y, width, height);
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
