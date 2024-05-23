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
    public void collisionChecker(Entity entity) {

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
                if(gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision){
                    entity.entityCollision = true;
                }
                break;
            case ("down"):
                scaledBSC = (bottomSideCollision + entity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledBSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledBSC];
                if(gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision){
                    entity.entityCollision = true;
                }
                break;
            case ("left"):
                scaledLSC = (leftSideCollision - entity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledTSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledLSC][scaledBSC];
                if(gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision){
                    entity.entityCollision = true;
                }
                break;
            case ("right"):
                scaledRSC = (rightSideCollision + entity.speed) / gamePanel.scaledTileSize;
                tile1 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledTSC];
                tile2 = gamePanel.tileManager.tilePosStorer[scaledRSC][scaledBSC];
                if(gamePanel.tileManager.tiles[tile1].hasCollision || gamePanel.tileManager.tiles[tile2].hasCollision){
                    entity.entityCollision = true;
                }

                break;

        }

    }
    /*
            case ("up"):
                if (spriteFrame == 1) {
                    image = upFr1;
                }
                if (spriteFrame == 2) {
                    image = upFr2;
                }
                break;
            case ("down"):
                if (spriteFrame == 1) {
                    image = downFr1;
                }
                if (spriteFrame == 2) {
                    image = downFr2;
                }
                break;
            case ("left"):
                if (spriteFrame == 1) {
                    image = leftFr1;
                }
                if (spriteFrame == 2) {
                    image = leftFr2;
                }
                break;
            case ("right"):
                if (spriteFrame == 1) {
                    image = rightFr1;
                }
                if (spriteFrame == 2) {
                    image = rightFr2;
                }
                break;
             */


}
