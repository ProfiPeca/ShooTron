package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LabDoorClosed_OBJ extends DefaultObject {

    private GamePanel gamePanel;
    public LabDoorClosed_OBJ(GamePanel gamePanel) {
        objName = "ClosedDoor";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/doorClosed.png"));
            gameOptimizer.imagePreScale(image, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        hasCollision = true;
    }
}
