package main;

import entities.TestBot;
import objects.*;


public class ObjectPlacer {
    GamePanel gamePanel;

    public ObjectPlacer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * places objects in the location you want them to be
     */
    public void objectSetter() {

        objectParameterSetter(0, new KeyCard_OBJ(gamePanel), 18, 18);
        objectParameterSetter(1, new KeyCard_OBJ(gamePanel), 19, 18);
        objectParameterSetter(2, new KeyCard_OBJ(gamePanel), 20, 18);

        objectParameterSetter(3, new LabDoorClosed_OBJ(gamePanel), 26, 18);
        objectParameterSetter(4, new LabDoorClosed_OBJ(gamePanel), 30, 22);

        objectParameterSetter(5, new LabChest_OBJ(gamePanel), 40, 20);

        objectParameterSetter(6, new LevelEndElevator_OBJ(gamePanel), 30, 18);

    }

    public void objectParameterSetter(int index, DefaultObject object, int xCords, int yCords) {
        gamePanel.objArray[index] = object;
        gamePanel.objArray[index].xCords = gamePanel.scaledTileSize * xCords;
        gamePanel.objArray[index].yCords = gamePanel.scaledTileSize * yCords;
    }

    public void entitySetter() {
        gamePanel.entityArray[0] = new TestBot(gamePanel);
        gamePanel.entityArray[0].xCords = gamePanel.scaledTileSize * 10;
        gamePanel.entityArray[0].yCords = gamePanel.scaledTileSize * 10;
    }
}
