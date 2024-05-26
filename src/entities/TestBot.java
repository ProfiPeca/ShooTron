package entities;

import main.GamePanel;

import java.awt.image.BufferedImage;

public class TestBot extends DefaultEntity {
    GamePanel gamePanel;

    public TestBot(GamePanel gamePanel) {
        super(gamePanel);
        dir = "down";
        speed = 5;

        getPewBotImage();
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
        this.filePath = "/testBot/";
        return entityIMGPreScale(imageName);
    }
}
