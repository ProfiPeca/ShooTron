package main;

import entities.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int tileSize = 16;
    final int itemScale = 5;
    public final int scaledTileSize = tileSize * itemScale;

    public final int screenColumnNumber = 16;
    public final int screenRowNumber = 12;
    public final int screenHeight = scaledTileSize * screenRowNumber;
    public final int screenWidth = scaledTileSize * screenColumnNumber;

    int gameFPS = 60;


    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void threadStart() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        /**
         * continuously updates the game, providing constant information both graphically and internally
         * @param FPSLimiter limits the game to run at 60 FPS
         */

        double FPSLimiter = 1000000000 / gameFPS;
        double delta = 0;
        long prevTime = System.nanoTime();
        long currTime;
        long FPSCounter = 0;
        int FPSShower = 0;

        while (gameThread != null) {
            currTime = System.nanoTime();

            delta += (currTime - prevTime) / FPSLimiter;
            FPSCounter += (currTime - prevTime);
            prevTime = currTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                FPSShower++;
            }
            if (FPSCounter >= 1000000000) {
                System.out.println("FPS: " + FPSShower);
                FPSShower = 0;
                FPSCounter = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2D = (Graphics2D) graphics;
        tileManager.tileDraw(g2D);
        player.draw(g2D);
        g2D.dispose();
    }
}
