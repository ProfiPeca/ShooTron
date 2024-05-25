package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyCard_OBJ extends DefaultObject {

    private GamePanel gamePanel;
    public KeyCard_OBJ(GamePanel gamePanel) {
        objName = "KeyCard";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/keyCard.png"));
            gameOptimizer.imagePreScale(image, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
