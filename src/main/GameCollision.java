package main;

import entities.DefaultEntity;

public class GameCollision {

    GamePanel gamePanel;


    public GameCollision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * checks collision of each side of the entity hit-box;
     */
    public void collisionTileChecker(DefaultEntity defaultEntity) {

        int leftSideCollision = defaultEntity.xCords + defaultEntity.collisionBox.x;
        int rightSideCollision = defaultEntity.xCords + defaultEntity.collisionBox.x + defaultEntity.collisionBox.width;
        int topSideCollision = defaultEntity.yCords + defaultEntity.collisionBox.y;
        int bottomSideCollision = defaultEntity.yCords + defaultEntity.collisionBox.y + defaultEntity.collisionBox.height;

        int scaledLSC = leftSideCollision / gamePanel.scaledTileSize;
        int scaledRSC = rightSideCollision / gamePanel.scaledTileSize;
        int scaledTSC = topSideCollision / gamePanel.scaledTileSize;
        int scaledBSC = bottomSideCollision / gamePanel.scaledTileSize;

        int tile1, tile2;
        switch (defaultEntity.dir) {

            case ("up"):
                scaledTSC = (topSideCollision - defaultEntity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledTSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledTSC];
                if (gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision) {
                    defaultEntity.entityCollision = true;
                }
                break;
            case ("down"):
                scaledBSC = (bottomSideCollision + defaultEntity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledBSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledBSC];
                if (gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision) {
                    defaultEntity.entityCollision = true;
                }
                break;
            case ("left"):
                scaledLSC = (leftSideCollision - defaultEntity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledTSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledBSC];
                if (gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision) {
                    defaultEntity.entityCollision = true;
                }
                break;
            case ("right"):
                scaledRSC = (rightSideCollision + defaultEntity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledTSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledBSC];
                if (gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision) {
                    defaultEntity.entityCollision = true;
                }
                break;
        }
    }

    public int collisionObjectChecker(DefaultEntity defaultEntity, boolean isPlayer) {
        int itemIndex = 9999;

        for (int i = 0; i < gamePanel.objArray.length; i++) {
            if (gamePanel.objArray[i] != null) {
                defaultEntity.collisionBox.x = defaultEntity.xCords + defaultEntity.collisionBox.x;
                defaultEntity.collisionBox.y = defaultEntity.yCords + defaultEntity.collisionBox.y;

                gamePanel.objArray[i].itemCollisionBox.x = gamePanel.objArray[i].xCords + gamePanel.objArray[i].itemCollisionBox.x;
                gamePanel.objArray[i].itemCollisionBox.y = gamePanel.objArray[i].yCords + gamePanel.objArray[i].itemCollisionBox.y;

                switch (defaultEntity.dir) {
                    case ("up"):
                        defaultEntity.collisionBox.y -= defaultEntity.speed;

                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                            if (gamePanel.objArray[i].hasCollision) {
                                defaultEntity.entityCollision = true;
                            }
                            if (isPlayer) {
                                itemIndex = i;
                            }
                        }
                        break;
                    case ("down"):
                        defaultEntity.collisionBox.y += defaultEntity.speed;

                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                            if (gamePanel.objArray[i].hasCollision) {
                                defaultEntity.entityCollision = true;
                            }
                            if (isPlayer) {
                                itemIndex = i;
                            }
                        }
                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                        }
                        break;
                    case ("left"):
                        defaultEntity.collisionBox.x -= defaultEntity.speed;

                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                            if (gamePanel.objArray[i].hasCollision) {
                                defaultEntity.entityCollision = true;
                            }
                            if (isPlayer) {
                                itemIndex = i;
                            }
                        }
                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                        }
                        break;
                    case ("right"):
                        defaultEntity.collisionBox.x += defaultEntity.speed;

                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                            if (gamePanel.objArray[i].hasCollision) {
                                defaultEntity.entityCollision = true;
                            }
                            if (isPlayer) {
                                itemIndex = i;
                            }
                        }
                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                        }
                        break;
                }
                defaultEntity.collisionBox.x = defaultEntity.defColX;
                defaultEntity.collisionBox.y = defaultEntity.defColY;
                gamePanel.objArray[i].itemCollisionBox.x = gamePanel.objArray[i].defColX;
                gamePanel.objArray[i].itemCollisionBox.y = gamePanel.objArray[i].defColY;
            }
        }
        return itemIndex;
    }
}
