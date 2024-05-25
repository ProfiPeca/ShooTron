package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LevelEndElevator_OBJ extends DefaultObject {

    GamePanel gamePanel;
    public LevelEndElevator_OBJ(GamePanel gamePanel) {
        objName = "LevelEndElevator";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/levelEndElevator.png"));
            gameOptimizer.imagePreScale(image, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
