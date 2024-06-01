package objects;

import entities.DefaultEntity;
import main.GamePanel;

public class LabDoorClosed_OBJ extends DefaultEntity {

    public LabDoorClosed_OBJ(GamePanel gamePanel) {
        super(gamePanel);
        entityName = "doorClosed";
        filePath = "/objects/";
        hasCollision = true;

        downFr1 = entityIMGPreScale(entityName);
    }
    public void entityAction(){
        this.hasCollision = false;
        this.entityName = "doorOpen";
        downFr1 = entityIMGPreScale(entityName);
    }
}
