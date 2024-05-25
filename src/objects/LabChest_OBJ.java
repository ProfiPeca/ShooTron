package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LabChest_OBJ extends DefaultObject {

    GamePanel gamePanel;
    public LabChest_OBJ(GamePanel gamePanel) {
        objName = "ClosedChest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chestClosed.png"));
            gameOptimizer.imagePreScale(image, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
