package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private GamePanel gamePanel;

    public boolean upDirPr, downDirPr, leftDirPr, rightDirPr;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * registers keys that are pressed
     * some keys do different actions depending on the state of the game
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int pressedKey = e.getKeyCode();
        // CHANGES THE CHOSEN OPTION IN MAIN MENU
        if (gamePanel.currGameState == gamePanel.gameTitle) {

            if (gamePanel.userInterface.getCurrOption() == 0) {
                if (pressedKey == KeyEvent.VK_W) {
                    gamePanel.userInterface.setCurrOption(2);
                }
                if (pressedKey == KeyEvent.VK_S) {
                    gamePanel.userInterface.setCurrOption(gamePanel.userInterface.getCurrOption() + 1);
                }
                if (pressedKey == KeyEvent.VK_ENTER) {

                    gamePanel.stopSound_MUSIC();
                    gamePanel.playSound_MUSIC(1);
                    gamePanel.currGameState = gamePanel.gameRunning;
                }

            } else if (gamePanel.userInterface.getCurrOption() == 1) {
                if (pressedKey == KeyEvent.VK_W) {
                    gamePanel.userInterface.setCurrOption(gamePanel.userInterface.getCurrOption() - 1);
                }
                if (pressedKey == KeyEvent.VK_S) {
                    gamePanel.userInterface.setCurrOption(gamePanel.userInterface.getCurrOption() + 1);
                }
                if (pressedKey == KeyEvent.VK_ENTER) {
                    System.out.println("does nothing yet");
                }

            } else if (gamePanel.userInterface.getCurrOption() == 2) {
                if (pressedKey == KeyEvent.VK_W) {
                    gamePanel.userInterface.setCurrOption(gamePanel.userInterface.getCurrOption() - 1);
                }
                if (pressedKey == KeyEvent.VK_S) {
                    gamePanel.userInterface.setCurrOption(0);
                }
                if (pressedKey == KeyEvent.VK_ENTER) {
                    System.exit(0);
                }
            }
            System.out.println("CURRENTLY CHOSEN BUTTON " + gamePanel.userInterface.getCurrOption());
        }

        if (gamePanel.currGameState == gamePanel.gameRunning) {
            if (pressedKey == KeyEvent.VK_W) {
                upDirPr = true;
            }
            if (pressedKey == KeyEvent.VK_S) {
                downDirPr = true;
            }
            if (pressedKey == KeyEvent.VK_A) {
                leftDirPr = true;
            }
            if (pressedKey == KeyEvent.VK_D) {
                rightDirPr = true;
            }
            if (pressedKey == KeyEvent.VK_ESCAPE) {
                gamePanel.currGameState = gamePanel.gamePaused;
            }
        } else if (gamePanel.currGameState == gamePanel.gamePaused) {

            if (pressedKey == KeyEvent.VK_Q) {
                gamePanel.stopSound_MUSIC();
                gamePanel.playSound_MUSIC(0);
                gamePanel.currGameState = gamePanel.gameTitle;
            }
            if (pressedKey == KeyEvent.VK_ESCAPE) {
                gamePanel.currGameState = gamePanel.gameRunning;
            }
        }

        if (gamePanel.currGameState == gamePanel.gameDialogue) {

            if (pressedKey == KeyEvent.VK_ENTER) {
                gamePanel.currGameState = gamePanel.gameRunning;
            }
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {
        int pressedKey = e.getKeyCode();

        if (pressedKey == KeyEvent.VK_W) {
            upDirPr = false;
        }
        if (pressedKey == KeyEvent.VK_S) {
            downDirPr = false;
        }
        if (pressedKey == KeyEvent.VK_A) {
            leftDirPr = false;
        }
        if (pressedKey == KeyEvent.VK_D) {
            rightDirPr = false;
        }
    }
}
