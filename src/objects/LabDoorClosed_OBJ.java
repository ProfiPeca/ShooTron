package objects;

import entities.DefaultEntity;
import main.GamePanel;

public class LabDoorClosed_OBJ extends DefaultEntity {

    public LabDoorClosed_OBJ(GamePanel gamePanel) {
        super(gamePanel);
        objName = "doorClosed";
        filePath = "/objects/";
        hasCollision = true;

        downFr1 = entityIMGPreScale(objName);
    }
    public void activate(){
        this.hasCollision = false;
        this.objName = "doorOpen";
        downFr1 = entityIMGPreScale(objName);
    }
}
