package entities;

import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class TestBot extends DefaultEntity {


    public TestBot(GamePanel gamePanel) {

        super(gamePanel);

        entityName = "testBot";
        dir = "down";
        speed = 5;

        getPewBotImage();
        pewBotPhrases();
    }

    public void getPewBotImage() {
        upFr1 = pewBotIMGPreScale("pewBotUpFr1");
        upFr2 = pewBotIMGPreScale("pewBotUpFr2");
        downFr1 = pewBotIMGPreScale("pewBotDownFr1");
        downFr2 = pewBotIMGPreScale("pewBotDownFr2");
        leftFr1 = pewBotIMGPreScale("pewBotLeftFr1");
        leftFr2 = pewBotIMGPreScale("pewBotLeftFr2");
        rightFr1 = pewBotIMGPreScale("pewBotRightFr1");
        rightFr2 = pewBotIMGPreScale("pewBotRightFr2");
    }

    public BufferedImage pewBotIMGPreScale(String imageName) {
        this.filePath = "/bots/";
        return entityIMGPreScale(imageName);
    }

    public void pewBotPhrases() {
        dialogueArray[0] = "HELLO_WORLD";
        dialogueArray[1] = "SHOOTRON == BAD >:)";
        dialogueArray[2] = "I LIKE SHOOTING";
        dialogueArray[3] = "PEW PEW";
        dialogueArray[4] = "does it work?";
        dialogueArray[5] = "YEEEEEEEEEEEEEEEEE  !ENTER  EEEES :)";
    }

    public void entityDialogue() {
        gamePanel.userInterface.currEntityDialogue = dialogueArray[dialogueNumber];
        dialogueNumber++;
        if (dialogueArray[dialogueNumber] == null) {
            dialogueNumber = 0;
        }
    }

    public void entityAction() {
        actionCoolDown++;

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
    }
}
