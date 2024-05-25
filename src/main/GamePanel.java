package main;

import entities.Player;
import objects.DefaultObject;
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

    public final int worldColumnNumber = 50;
    public final int worldRowNumber = 50;


    int gameFPS = 60;


    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    GameSound gameSound = new GameSound();
    GameSound gameMusic = new GameSound();
    public UserInterface userInterface = new UserInterface(this);
    public GameCollision gameCollision = new GameCollision(this);
    public ObjectPlacer objectPlacer = new ObjectPlacer(this);
    public DefaultObject objArray[] = new DefaultObject[20];

    public void game_stuffInitializer() {
        objectPlacer.objectSetter();
        playSound_MUSIC(0);
    }

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

    /**
     * draws everything in the game
     * the placement of drawing different stuff is important, otherwise it would incorrectly overlap
     * tiles are at the bottom, objects are second priority, player third and interface is always on top
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2D = (Graphics2D) graphics;
        tileManager.tileDraw(g2D);

        for (int i = 0; i < objArray.length; i++) {
            if (objArray[i] != null) {
                objArray[i].drawObject(g2D, this);
            }
        }
        player.draw(g2D);
        userInterface.drawInterface(g2D);
        g2D.dispose();
    }
    public void playSound_MUSIC(int i) {
        gameMusic.soundGetter(i);
        gameMusic.soundPlay();
        gameMusic.soundLoop();
    }
    public void stopSound_MUSIC(){
        gameMusic.soundStop();
    }
    public void playSound_EFFECT(int i) {
        gameSound.soundGetter(i);
        gameSound.soundPlay();
    }
}
