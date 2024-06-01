package objects;

import entities.DefaultEntity;
import main.GamePanel;

public class KeyCard_OBJ extends DefaultEntity {

    public KeyCard_OBJ(GamePanel gamePanel) {
        super(gamePanel);
        entityName = "keyCard";
        filePath = "/objects/";

        downFr1 = entityIMGPreScale(entityName);
    }
}
