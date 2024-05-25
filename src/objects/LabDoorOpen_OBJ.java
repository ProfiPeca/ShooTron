package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LabDoorOpen_OBJ extends DefaultObject{

    GamePanel gamePanel;
    public LabDoorOpen_OBJ(GamePanel gamePanel) {
        objName = "OpenDoor";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/doorOpen.png"));
            gameOptimizer.imagePreScale(image, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
