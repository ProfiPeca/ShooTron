package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upDirPr, downDirPr, leftDirPr, rightDirPr;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressedKey = e.getKeyCode();

        if (pressedKey == KeyEvent.VK_W) {
            upDirPr=true;
        }
        if (pressedKey == KeyEvent.VK_S) {
            downDirPr=true;
        }
        if (pressedKey == KeyEvent.VK_A) {
            leftDirPr=true;
        }
        if (pressedKey == KeyEvent.VK_D) {
            rightDirPr=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int pressedKey = e.getKeyCode();

        if (pressedKey == KeyEvent.VK_W) {
            upDirPr=false;
        }
        if (pressedKey == KeyEvent.VK_S) {
            downDirPr=false;
        }
        if (pressedKey == KeyEvent.VK_A) {
            leftDirPr=false;
        }
        if (pressedKey == KeyEvent.VK_D) {
            rightDirPr=false;
        }
    }
}
