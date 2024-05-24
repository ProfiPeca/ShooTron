package main;

import entities.Entity;

public class GameCollision {

    GamePanel gamePanel;


    public GameCollision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * checks collision of each side of the entity hit-box;
     */
    public void collisionTileChecker(Entity entity) {

        int leftSideCollision = entity.xCords + entity.collisionBox.x;
        int rightSideCollision = entity.xCords + entity.collisionBox.x + entity.collisionBox.width;
        int topSideCollision = entity.yCords + entity.collisionBox.y;
        int bottomSideCollision = entity.yCords + entity.collisionBox.y + entity.collisionBox.height;

        int scaledLSC = leftSideCollision / gamePanel.scaledTileSize;
        int scaledRSC = rightSideCollision / gamePanel.scaledTileSize;
        int scaledTSC = topSideCollision / gamePanel.scaledTileSize;
        int scaledBSC = bottomSideCollision / gamePanel.scaledTileSize;

        int tile1, tile2;
        switch (entity.dir) {

            case ("up"):
                scaledTSC = (topSideCollision - entity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledTSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledTSC];
                if (gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision) {
                    entity.entityCollision = true;
                }
                break;
            case ("down"):
                scaledBSC = (bottomSideCollision + entity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledBSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledBSC];
                if (gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision) {
                    entity.entityCollision = true;
                }
                break;
            case ("left"):
                scaledLSC = (leftSideCollision - entity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledTSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledBSC];
                if (gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision) {
                    entity.entityCollision = true;
                }
                break;
            case ("right"):
                scaledRSC = (rightSideCollision + entity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledTSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledBSC];
                if (gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision) {
                    entity.entityCollision = true;
                }
                break;
        }
    }

    public int collisionObjectChecker(Entity entity, boolean isPlayer) {
        int itemIndex = 9999;

        for (int i = 0; i < gamePanel.objArray.length; i++) {
            if (gamePanel.objArray[i] != null) {
                entity.collisionBox.x = entity.xCords + entity.collisionBox.x;
                entity.collisionBox.y = entity.yCords + entity.collisionBox.y;

                gamePanel.objArray[i].itemCollisionBox.x = gamePanel.objArray[i].xCords + gamePanel.objArray[i].itemCollisionBox.x;
                gamePanel.objArray[i].itemCollisionBox.y = gamePanel.objArray[i].yCords + gamePanel.objArray[i].itemCollisionBox.y;

                switch (entity.dir) {
                    case ("up"):
                        entity.collisionBox.y -= entity.speed;

                        if (entity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                            if (gamePanel.objArray[i].hasCollision){
                                entity.entityCollision=true;
                            }
                            if (isPlayer){
                                itemIndex = i;
                            }
                        }
                        break;
                    case ("down"):
                        entity.collisionBox.y += entity.speed;

                        if (entity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                            if (gamePanel.objArray[i].hasCollision){
                                entity.entityCollision=true;
                            }
                            if (isPlayer){
                                itemIndex = i;
                            }
                        }
                        if (entity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                        }
                        break;
                    case ("left"):
                        entity.collisionBox.x -= entity.speed;

                        if (entity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                            if (gamePanel.objArray[i].hasCollision){
                                entity.entityCollision=true;
                            }
                            if (isPlayer){
                                itemIndex = i;
                            }
                        }
                        if (entity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                        }
                        break;
                    case ("right"):
                        entity.collisionBox.x += entity.speed;

                        if (entity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                            if (gamePanel.objArray[i].hasCollision){
                                entity.entityCollision=true;
                            }
                            if (isPlayer){
                                itemIndex = i;
                            }
                        }
                        if (entity.collisionBox.intersects(gamePanel.objArray[i].itemCollisionBox)) {
                        }
                        break;
                }
                entity.collisionBox.x = entity.defColX;
                entity.collisionBox.y = entity.defColY;
                gamePanel.objArray[i].itemCollisionBox.x = gamePanel.objArray[i].defColX;
                gamePanel.objArray[i].itemCollisionBox.y = gamePanel.objArray[i].defColY;
            }
        }
        return itemIndex;
    }
}
