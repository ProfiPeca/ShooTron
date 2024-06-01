package entities;

import main.GamePanel;
import main.KeyHandler;
import objects.LabDoorClosed_OBJ;


import java.awt.*;
import java.awt.image.BufferedImage;
;


public class Player extends DefaultEntity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    private LabDoorClosed_OBJ labDoorClosed;

    public final int screenX;
    public final int screenY;

    public int keyCardNumber = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        // screenX and screenY make sure that the player always stays at the center of the screen
        screenX = gamePanel.screenWidth / 2 - (gamePanel.scaledTileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.scaledTileSize / 2);

        collisionBox = new Rectangle(gamePanel.scaledTileSize / 6, gamePanel.scaledTileSize / 3, gamePanel.scaledTileSize - gamePanel.scaledTileSize / 3, gamePanel.scaledTileSize - gamePanel.scaledTileSize / 3);
        defColX = collisionBox.x;
        defColY = collisionBox.y;

        playerSetter();
        getPlayerImage();
    }

    /**
     * sets the coordinates and stats of the player
     */
    public void playerSetter() {
        xCords = gamePanel.scaledTileSize * 23;
        yCords = gamePanel.scaledTileSize * 21;
        speed = 7;
        dir = "down";

        maxHP = 100;
        currHP = maxHP;
    }

    /**
     * does different stuff based on the activate method
     *
     * @param i an index of the object array entity
     */
    public void objectPickUpper(int i) {

        if (i != 9999) {

            String pickedUpObjectName = gamePanel.objArray[i].entityName;

            switch (pickedUpObjectName) {
                case ("keyCard"):
                    gamePanel.playSound_EFFECT(4);
                    keyCardNumber++;
                    gamePanel.objArray[i] = null;

                    gamePanel.userInterface.textPopUp("Key+");
                    break;
                case ("doorClosed"):
                    if (keyCardNumber > 0) {
                        gamePanel.objArray[i].entityAction();
                        gamePanel.playSound_EFFECT(5);
                        keyCardNumber--;
                        System.out.println("keys: " + keyCardNumber);
                    }
                    break;
                case ("doorOpen"):
                    System.out.println("it just works");
                    break;
                case ("levelEndElevator"):
                    System.out.println("gonna implement this soon");

                    gamePanel.player.xCords += 500;
                    /*
                    gamePanel.stopSound_MUSIC();
                    gamePanel.playSound_EFFECT(6);
                     */

                    gamePanel.objArray[i].entityName = "noMoreTP";

                    break;
                default:
                    System.out.println("you cant pick this");
                    break;
            }


        }
    }

    /**
     * performs action with entity
     *
     * @param i id of entity
     */
    public void entityInteractionOnCol(int i) {
        if (i != 9999) {
            String entityName = gamePanel.entityArray[i].entityName;

            switch (entityName) {
                case ("testBot"):
                    gamePanel.currGameState = gamePanel.gameDialogue;
                    gamePanel.entityArray[i].entityDialogue();
                    System.out.println("pewBot: COLLISION WILL NOT BE TOLERATED");
                    break;
                case ("ramBot"):
                    gamePanel.player.dmgCooldown++;
                    if (dmgCooldown >= 15) {
                        gamePanel.entityArray[i].currHP -= 16;
                        gamePanel.player.dmgCooldown = 0;
                        System.out.println("IM HURT IM LOW ON HP + " + gamePanel.entityArray[i].currHP);
                        gamePanel.playSound_EFFECT(3);
                    }

                    if (gamePanel.entityArray[i].isAlive == false && gamePanel.entityArray[i].dyingFrameInt >= 50) {
                        gamePanel.entityArray[i] = null;
                    }
                    break;
            }
        }
    }

    public void getPlayerImage() {
        upFr1 = playerIMGPreScale("shooTronUpFr1");
        upFr2 = playerIMGPreScale("shooTronUpFr2");
        downFr1 = playerIMGPreScale("shooTronDownFr1");
        downFr2 = playerIMGPreScale("shooTronDownFr2");
        leftFr1 = playerIMGPreScale("shooTronLeftFr1");
        leftFr2 = playerIMGPreScale("shooTronLeftFr2");
        rightFr1 = playerIMGPreScale("shooTronRightFr1");
        rightFr2 = playerIMGPreScale("shooTronRightFr2");
    }

    public BufferedImage playerIMGPreScale(String imageName) {
        this.filePath = "/player/";
        return entityIMGPreScale(imageName);
    }

    /**
     * updates the movement of the player by continuously checking if the player is holding that key
     * the first 4 ifs are for diagonal directions, the last 4 are for vertical and horizontal
     * player can only move through tiles without collision, otherwise he can't
     */
    public void update() {
        if (keyHandler.upDirPr || keyHandler.downDirPr || keyHandler.leftDirPr || keyHandler.rightDirPr) {

            if (keyHandler.upDirPr) {
                dir = "up";
            } else if (keyHandler.downDirPr) {
                dir = "down";
            } else if (keyHandler.leftDirPr) {
                dir = "left";
            } else if (keyHandler.rightDirPr) {
                dir = "right";
            }

            entityCollision = false;

            //collision checks
            gamePanel.gameCollision.collisionTileChecker(this);
            int collidedObject = gamePanel.gameCollision.collisionObjectChecker(this, true);
            int collidedEntity = gamePanel.gameCollision.collisionEntityChecker(this, gamePanel.entityArray);
            objectPickUpper(collidedObject);
            entityInteractionOnCol(collidedEntity);

            if (entityCollision == false) {
                if (keyHandler.upDirPr && keyHandler.leftDirPr) {
                    yCords -= speed / 1.5;
                    xCords -= speed / 1.5;
                } else if (keyHandler.upDirPr && keyHandler.rightDirPr) {
                    yCords -= speed / 1.5;
                    xCords += speed / 1.5;
                } else if (keyHandler.downDirPr && keyHandler.leftDirPr) {
                    yCords += speed / 1.5;
                    xCords -= speed / 1.5;
                } else if (keyHandler.downDirPr && keyHandler.rightDirPr) {
                    yCords += speed / 1.5;
                    xCords += speed / 1.5;
                } else if (keyHandler.upDirPr) {
                    dir = "up";
                    yCords -= speed;
                } else if (keyHandler.downDirPr) {
                    dir = "down";
                    yCords += speed;
                } else if (keyHandler.leftDirPr) {
                    dir = "left";
                    xCords -= speed;
                } else if (keyHandler.rightDirPr) {
                    dir = "right";
                    xCords += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 5) {
                if (spriteFrame == 1) {
                    spriteFrame = 2;
                } else if (spriteFrame == 2) {
                    spriteFrame = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2D) {

        BufferedImage image = null;
        switch (dir) {
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
        }
        g2D.drawImage(image, screenX, screenY, null);


    }
}
