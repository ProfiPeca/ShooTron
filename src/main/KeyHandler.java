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
        }

        else if (gamePanel.currGameState == gamePanel.gamePaused) {

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
