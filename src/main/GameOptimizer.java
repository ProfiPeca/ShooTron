package main;

import java.awt.*;
import java.awt.image.BufferedImage;


public class GameOptimizer {

    /**
     * scales stuff before it gets rendered, so it increases overall performance
     */
    public BufferedImage imagePreScale(BufferedImage preScaledImage, int imgWidth, int imgHeight) {

        BufferedImage scaledImage = new BufferedImage(imgWidth, imgHeight, preScaledImage.getType());
        Graphics2D g2D = scaledImage.createGraphics();
        g2D.drawImage(preScaledImage, 0, 0, imgWidth, imgHeight, null);
        g2D.dispose();

        return scaledImage;
    }
}
