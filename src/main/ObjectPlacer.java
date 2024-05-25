package main;

import objects.KeyCard_OBJ;
import objects.LabChest_OBJ;
import objects.LabDoorClosed_OBJ;
import objects.LevelEndElevator_OBJ;


public class ObjectPlacer {
    GamePanel gamePanel;

    public ObjectPlacer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * places objects in the location you want them to be
     */
    public void objectSetter() {


        gamePanel.objArray[0] = new KeyCard_OBJ(gamePanel);
        gamePanel.objArray[0].xCords = gamePanel.scaledTileSize * 28;
        gamePanel.objArray[0].yCords = gamePanel.scaledTileSize * 18;

        gamePanel.objArray[1] = new KeyCard_OBJ(gamePanel);
        gamePanel.objArray[1].xCords = gamePanel.scaledTileSize * 29;
        gamePanel.objArray[1].yCords = gamePanel.scaledTileSize * 18;

        gamePanel.objArray[2] = new KeyCard_OBJ(gamePanel);
        gamePanel.objArray[2].xCords = gamePanel.scaledTileSize * 30;
        gamePanel.objArray[2].yCords = gamePanel.scaledTileSize * 18;

        gamePanel.objArray[3] = new LabDoorClosed_OBJ(gamePanel);
        gamePanel.objArray[3].xCords = gamePanel.scaledTileSize * 39;
        gamePanel.objArray[3].yCords = gamePanel.scaledTileSize * 25;

        gamePanel.objArray[4] = new LabDoorClosed_OBJ(gamePanel);
        gamePanel.objArray[4].xCords = gamePanel.scaledTileSize * 40;
        gamePanel.objArray[4].yCords = gamePanel.scaledTileSize * 25;

        gamePanel.objArray[5] = new LabChest_OBJ(gamePanel);
        gamePanel.objArray[5].xCords = gamePanel.scaledTileSize * 40;
        gamePanel.objArray[5].yCords = gamePanel.scaledTileSize * 20;

        gamePanel.objArray[6] = new LevelEndElevator_OBJ(gamePanel);
        gamePanel.objArray[6].xCords = gamePanel.scaledTileSize * 38;
        gamePanel.objArray[6].yCords = gamePanel.scaledTileSize * 18;


    }
}
