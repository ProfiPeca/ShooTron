package objects;

import entities.DefaultEntity;
import main.GamePanel;

public class LabChest_OBJ extends DefaultEntity {

    public LabChest_OBJ(GamePanel gamePanel) {
        super(gamePanel);
        entityName = "chestClosed";
        filePath = "/objects/";

        downFr1 = entityIMGPreScale(entityName);
    }
}
