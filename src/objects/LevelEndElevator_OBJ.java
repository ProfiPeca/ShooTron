package objects;

import entities.DefaultEntity;
import main.GamePanel;

public class LevelEndElevator_OBJ extends DefaultEntity {

    public LevelEndElevator_OBJ(GamePanel gamePanel) {
        super(gamePanel);
        entityName = "levelEndElevator";
        filePath = "/objects/";

        downFr1 = entityIMGPreScale(entityName);
    }
}
