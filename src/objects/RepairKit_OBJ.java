package objects;

import entities.DefaultEntity;
import main.GamePanel;

public class RepairKit_OBJ extends DefaultEntity {

    public RepairKit_OBJ(GamePanel gamePanel) {
        super(gamePanel);
        entityName = "repairKit";
        filePath = "/objects/";

        downFr1 = entityIMGPreScale(entityName);
    }
}
