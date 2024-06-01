package objects;

import entities.DefaultEntity;
import main.GamePanel;

public class LabDoorOpen_OBJ extends DefaultEntity {

    public LabDoorOpen_OBJ(GamePanel gamePanel) {
        super(gamePanel);
        entityName = "doorOpen";
        filePath = "/objects/";

        downFr1 = entityIMGPreScale(entityName);
    }
}
