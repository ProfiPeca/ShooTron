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

                gamePanel.objArray[i].collisionBox.x = gamePanel.objArray[i].xCords + gamePanel.objArray[i].collisionBox.x;
                gamePanel.objArray[i].collisionBox.y = gamePanel.objArray[i].yCords + gamePanel.objArray[i].collisionBox.y;

                switch (defaultEntity.dir) {
                    case ("up"):
                        defaultEntity.collisionBox.y -= defaultEntity.speed;

                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].collisionBox)) {
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

                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].collisionBox)) {
                            if (gamePanel.objArray[i].hasCollision) {
                                defaultEntity.entityCollision = true;
                            }
                            if (isPlayer) {
                                itemIndex = i;
                            }
                        }

                        break;
                    case ("left"):
                        defaultEntity.collisionBox.x -= defaultEntity.speed;

                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].collisionBox)) {
                            if (gamePanel.objArray[i].hasCollision) {
                                defaultEntity.entityCollision = true;
                            }
                            if (isPlayer) {
                                itemIndex = i;
                            }
                        }

                        break;
                    case ("right"):
                        defaultEntity.collisionBox.x += defaultEntity.speed;

                        if (defaultEntity.collisionBox.intersects(gamePanel.objArray[i].collisionBox)) {
                            if (gamePanel.objArray[i].hasCollision) {
                                defaultEntity.entityCollision = true;
                            }
                            if (isPlayer) {
                                itemIndex = i;
                            }
                        }

                        break;
                }
                defaultEntity.collisionBox.x = defaultEntity.defColX;
                defaultEntity.collisionBox.y = defaultEntity.defColY;
                gamePanel.objArray[i].collisionBox.x = gamePanel.objArray[i].defColX;
                gamePanel.objArray[i].collisionBox.y = gamePanel.objArray[i].defColY;
            }
        }
        return itemIndex;
    }

    public int collisionEntityChecker(DefaultEntity entity, DefaultEntity[] collidedEntity) {
        int itemIndex = 9999;

        for (int i = 0; i < collidedEntity.length; i++) {
            if (collidedEntity[i] != null) {
                entity.collisionBox.x = entity.xCords + entity.collisionBox.x;
                entity.collisionBox.y = entity.yCords + entity.collisionBox.y;

                collidedEntity[i].collisionBox.x = collidedEntity[i].xCords + collidedEntity[i].collisionBox.x;
                collidedEntity[i].collisionBox.y = collidedEntity[i].yCords + collidedEntity[i].collisionBox.y;

                switch (entity.dir) {
                    case ("up"):
                        entity.collisionBox.y -= entity.speed;

                        if (entity.collisionBox.intersects(collidedEntity[i].collisionBox)) {

                            entity.entityCollision = true;
                            itemIndex = i;
                        }
                        break;
                    case ("down"):
                        entity.collisionBox.y += entity.speed;

                        if (entity.collisionBox.intersects(collidedEntity[i].collisionBox)) {
                            entity.entityCollision = true;
                            itemIndex = i;
                        }
                        break;
                    case ("left"):
                        entity.collisionBox.x -= entity.speed;

                        if (entity.collisionBox.intersects(collidedEntity[i].collisionBox)) {
                            entity.entityCollision = true;
                            itemIndex = i;
                        }

                        break;
                    case ("right"):
                        entity.collisionBox.x += entity.speed;

                        if (entity.collisionBox.intersects(collidedEntity[i].collisionBox)) {
                            entity.entityCollision = true;
                            itemIndex = i;
                        }

                        break;
                }
                entity.collisionBox.x = entity.defColX;
                entity.collisionBox.y = entity.defColY;
                collidedEntity[i].collisionBox.x = collidedEntity[i].defColX;
                collidedEntity[i].collisionBox.y = collidedEntity[i].defColY;
            }
        }
        return itemIndex;
    }

    public void collisionPlayerChecker(DefaultEntity entity) {
        entity.collisionBox.x = entity.xCords + entity.collisionBox.x;
        entity.collisionBox.y = entity.yCords + entity.collisionBox.y;

        gamePanel.player.collisionBox.x = gamePanel.player.xCords + gamePanel.player.collisionBox.x;
        gamePanel.player.collisionBox.y = gamePanel.player.yCords + gamePanel.player.collisionBox.y;

        switch (entity.dir) {
            case ("up"):
                entity.collisionBox.y -= entity.speed;

                if (entity.collisionBox.intersects(gamePanel.player.collisionBox)) {

                    entity.entityCollision = true;
                }
                break;
            case ("down"):
                entity.collisionBox.y += entity.speed;

                if (entity.collisionBox.intersects(gamePanel.player.collisionBox)) {
                    entity.entityCollision = true;
                }
                break;
            case ("left"):
                entity.collisionBox.x -= entity.speed;

                if (entity.collisionBox.intersects(gamePanel.player.collisionBox)) {
                    entity.entityCollision = true;
                }

                break;
            case ("right"):
                entity.collisionBox.x += entity.speed;

                if (entity.collisionBox.intersects(gamePanel.player.collisionBox)) {
                    entity.entityCollision = true;
                }

                break;
        }
        entity.collisionBox.x = entity.defColX;
        entity.collisionBox.y = entity.defColY;
        gamePanel.player.collisionBox.x = gamePanel.player.defColX;
        gamePanel.player.collisionBox.y = gamePanel.player.defColY;
    }
}
