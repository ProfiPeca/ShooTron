package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UserInterface {
    private GamePanel gamePanel;
    private Graphics2D g2D;
    private Font interfaceFont, bigInterfaceFont, messageFont;

    //PopUpTextParameters
    private String popUpText = "";
    private int popUpTextDuration = 0;
    private int charToDraw = 0;
    private boolean toggle = false;

    //TITLESCREEN
    private int waitNum = 10;
    private int currOption = 0;
    private String typedInTitle = "";
    private BufferedImage shooTronHD;

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
        bigInterfaceFont = new Font("Arial", Font.BOLD, 90);
        messageFont = new Font("Arial", Font.PLAIN, 50);
        try {
            shooTronHD = ImageIO.read(getClass().getResourceAsStream("/misc/shooTronHD.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void textPopUp(String information) {
        popUpText = information;
        activePopUpText = true;
    }

    /**
     * draws the user interface on top of everything else
     * UIBackground is used to change visibility of anything drawn to around 70%, it is used for drawing rectangles that makes UI more visible, while still letting player see behind the interface
     * UIInfo sets visibility back to 100%, so you can see the text better
     * if level is cleared by going into the elevator, a text will appear
     * levelTimer displays time that has passed in the level in seconds
     */
    public void drawInterface(Graphics2D g2D) {

        this.g2D = g2D;

        g2D.setColor(Color.GREEN);
        g2D.setFont(interfaceFont);

        if (gamePanel.currGameState == gamePanel.gameTitle) {
            titleInterface();
        }
        if (gamePanel.currGameState == gamePanel.gameRunning) {
            //Background
            g2D.setColor(Color.BLACK);
            g2D.setComposite(UIBackground);
            //leftUp rect
            g2D.fillRect(0, 0, gamePanel.scaledTileSize * 16, gamePanel.scaledTileSize * 1);
            //rightUp rect CURRENTLY SUBBED BY LEFTUP
            //g2D.fillRect(gamePanel.scaledTileSize * 13 + 70, 0, gamePanel.scaledTileSize * 3, gamePanel.scaledTileSize);
            //rightDown rect
            g2D.fillRect(gamePanel.scaledTileSize * 12, gamePanel.scaledTileSize * 10, gamePanel.scaledTileSize * 4, gamePanel.scaledTileSize * 3);

            //Interface
            //g2D.setFont(interfaceFont);
            g2D.setColor(Color.GREEN);
            g2D.setComposite(UIInfo);

            g2D.drawString("KEYCARDS: " + gamePanel.player.keyCardNumber, 40, 50);
            levelTimer += (double) 1 / 60;
            g2D.drawString("TIME: " + decimalFormat.format(levelTimer), gamePanel.scaledTileSize * 14, 50);


            displayHP();
        }
        if (gamePanel.currGameState == gamePanel.gamePaused) {
            pauseInterface();
        }
        if (gamePanel.currGameState == gamePanel.gameDialogue) {
            dialogueInterface();
        }
    }

    private void displayHP() {

        int xPos = gamePanel.scaledTileSize * 12 + 10;
        int yPos = gamePanel.scaledTileSize * 10 + 10;
        int barWidth = gamePanel.player.getMaxHP();
        int barHeight = gamePanel.scaledTileSize / 2;


        g2D.setColor(Color.GRAY);
        g2D.setComposite(UIInfo);
        //HEALTHBG
        g2D.fillRect(xPos, yPos, barWidth, barHeight);
        //HEALTHMETER
        g2D.setColor(Color.GREEN);
        g2D.fillRect(xPos, yPos + 10, gamePanel.player.getCurrHP(), barHeight - 20);
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

        g2D.setFont(bigInterfaceFont);
        String interfaceText = "GAME PAUSED";
        int screenX = textCenterX(interfaceText);
        int screenY = gamePanel.screenHeight / 2;
        g2D.drawString(interfaceText, screenX, screenY);

        g2D.setFont(interfaceFont);
        interfaceText = "PRESS Q TO EXIT TO GAME MENU";
        g2D.drawString(interfaceText, textCenterX(interfaceText), screenY + 50);
    }

    /**
     * draws the title screen
     * waitNum is used so that the typedInTitle is written in a sequence by adding titleArray indexes to it
     */

    private void titleInterface() {
        g2D.setColor(Color.GREEN);
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 150f));
        g2D.setComposite(UIInfo);

        String menuText = "SHOOTRON";
        String[] titleArray = menuText.split("");
        int xPos = textCenterX(menuText);
        int yPos = gamePanel.scaledTileSize * 3;
        //draws the image of shooTron
        g2D.drawImage(shooTronHD, 500, 500, 700, 700, null);

        //draws the title name in order and then toggles the underscore on and off
        waitNum++;
        if (waitNum >= 30) {
            if (typedInTitle.length() < menuText.length()) {
                typedInTitle += titleArray[charToDraw];
                charToDraw++;
            }
            toggle = !toggle;
            waitNum = 0;
        }
        if (toggle && menuText.length() == charToDraw) {
            g2D.drawString(typedInTitle + "_", xPos, yPos);
        } else {
            g2D.drawString(typedInTitle, xPos, yPos);
        }

        //draws the options listed in main menu and puts an arrow next to the text
        g2D.setFont(messageFont);

        menuText = "START_GAME";

        xPos = 225;
        yPos += gamePanel.scaledTileSize * 2;
        if (currOption == 0) {
            if (waitNum < 15) {
                g2D.drawString(menuText + " <", xPos, yPos);
            }
            if (waitNum >= 15) {
                g2D.drawString(menuText + "  <", xPos, yPos);
            }
        } else {
            g2D.drawString(menuText, xPos, yPos);
        }

        menuText = "LOAD_GAME";

        yPos += gamePanel.scaledTileSize;
        if (currOption == 1) {
            if (waitNum < 15) {
                g2D.drawString(menuText + " <", xPos, yPos);
            }
            if (waitNum >= 15) {
                g2D.drawString(menuText + "  <", xPos, yPos);
            }
        } else {
            g2D.drawString(menuText, xPos, yPos);
        }

        menuText = "EXIT";
        yPos += gamePanel.scaledTileSize;
        if (currOption == 2) {
            if (waitNum < 15) {
                g2D.drawString(menuText + " <", xPos, yPos);
            }
            if (waitNum >= 15) {
                g2D.drawString(menuText + "  <", xPos, yPos);
            }
        } else {
            g2D.drawString(menuText, xPos, yPos);
        }

    }

    /**
     * displays dialogues, you can add a line by putting ' !ENTER ' in the desired string
     */
    private void dialogueInterface() {
        int xPos = gamePanel.scaledTileSize * 2, yPos = gamePanel.scaledTileSize / 2, windowWidth = gamePanel.screenWidth - (gamePanel.scaledTileSize * 4), windowHeight = gamePanel.scaledTileSize * 5;
        dialogueBackground(xPos, yPos, windowWidth, windowHeight);

        g2D.setFont(messageFont);

        //g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 50f));
        xPos += gamePanel.scaledTileSize;
        yPos += gamePanel.scaledTileSize;

        for (String stringRow : currEntityDialogue.split(" !ENTER ")) {
            g2D.drawString(stringRow, xPos, yPos);
            yPos += 50;
        }
    }

    /**
     * displays the background of messages so they are more visible
     *
     * @param x      is the value of the position of the leftmost corner of the rectangle
     * @param y      is the value of the position of the top side of the rectangle
     * @param width  is the value of the width of the rectangle
     * @param height is the value of the height of the rectangle
     */
    private void dialogueBackground(int x, int y, int width, int height) {
        g2D.setColor(Color.BLACK);
        g2D.setComposite(UIBackground);
        g2D.fillRect(x, y, width, height);

        g2D.setColor(Color.GREEN);
        g2D.setStroke(new BasicStroke(10));
        g2D.drawRect(x, y, width, height);
    }

    private int textCenterX(String interfaceText) {
        int interfaceTextLength = (int) g2D.getFontMetrics().getStringBounds(interfaceText, g2D).getWidth();
        int screenX = (gamePanel.screenWidth - interfaceTextLength) / 2;
        return screenX;
    }

    public void setLevelCleared(boolean levelCleared) {
        this.levelCleared = levelCleared;
    }

    //region GETSET
    public int getCurrOption() {
        return currOption;
    }

    public void setCurrOption(int currOption) {
        this.currOption = currOption;
    }
    //endregion
}
