package entities;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int keyCardNumber = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
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

    public void playerSetter() {
        xCords = gamePanel.scaledTileSize * 23;
        yCords = gamePanel.scaledTileSize * 21;
        speed = 7;
        dir = "down";
    }

    public void objectPickUper(int i) {
        if(i != 9999) {
            String pickedUpObjectName = gamePanel.objArray[i].objName;

            switch (pickedUpObjectName) {
                case ("KeyCard"):
                    gamePanel.playSound_EFFECT(3);
                    keyCardNumber++;
                    gamePanel.objArray[i] = null;
                    System.out.println("keys: "+keyCardNumber);
                    break;
                case ("ClosedDoor"):
                    if(keyCardNumber > 0) {
                        gamePanel.playSound_EFFECT(4);
                        keyCardNumber--;
                        try {
                            gamePanel.objArray[i].image = ImageIO.read(getClass().getResourceAsStream("/objects/doorOpen.png"));
                            gamePanel.objArray[i].hasCollision = false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("keys: "+keyCardNumber);
                    }

                    break;
                case ("ClosedChest"):

                    break;
                default:
                    System.out.println("i picked up something weird");
                    break;
            }
        }

    }

    public void getPlayerImage() {

        try {
            upFr1 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronUpFr1.png"));
            upFr2 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronUpFr2.png"));
            downFr1 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronDownFr1.png"));
            downFr2 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronDownFr2.png"));
            leftFr1 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronLeftFr1.png"));
            leftFr2 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronLeftFr2.png"));
            rightFr1 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronRightFr1.png"));
            rightFr2 = ImageIO.read(getClass().getResourceAsStream("/player/shooTronRightFr2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates the movement of the player by continuously checking if the player is holding that key
     * the first 4 ifs are for diagonal directions, the last 4 are for vertical and horizontal
     * player can only move through tiles without collision, otherwise he can't
     */
    public void update() {
        if (keyHandler.upDirPr || keyHandler.downDirPr || keyHandler.leftDirPr || keyHandler.rightDirPr) {

            /*
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
            }

             */
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
            objectPickUper(collidedObject);

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

        /*
        g2D.setColor(Color.white);
        g2D.fillRect(xCords, yCords, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
         */
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
        g2D.drawImage(image, screenX, screenY, gamePanel.scaledTileSize, gamePanel.scaledTileSize, null);
    }
}
