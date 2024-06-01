package main;

import entities.DefaultEntity;
import entities.EnemyBot_ENM;
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

    public void entitySetter() {
        entityParameterSetter(0, new TestBot(gamePanel), 10, 10);

        entityParameterSetter(1, new EnemyBot_ENM(gamePanel), 12, 12);

    }

    /**
     * simplifies placement of entities
     *
     * @param index  of the item in the array
     * @param object what will it draw
     * @param xCords location
     * @param yCords location
     */
    public void objectParameterSetter(int index, DefaultEntity object, int xCords, int yCords) {
        gamePanel.objArray[index] = object;
        gamePanel.objArray[index].xCords = gamePanel.scaledTileSize * xCords;
        gamePanel.objArray[index].yCords = gamePanel.scaledTileSize * yCords;
    }


    public void entityParameterSetter(int index, DefaultEntity object, int xCords, int yCords) {
        gamePanel.entityArray[index] = object;
        gamePanel.entityArray[index].xCords = gamePanel.scaledTileSize * xCords;
        gamePanel.entityArray[index].yCords = gamePanel.scaledTileSize * yCords;
    }
}
