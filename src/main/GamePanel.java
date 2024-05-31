package main;

import entities.DefaultEntity;
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


    public KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    GameSound gameSound = new GameSound();
    GameSound gameMusic = new GameSound();
    public UserInterface userInterface = new UserInterface(this);
    public GameCollision gameCollision = new GameCollision(this);
    public ObjectPlacer objectPlacer = new ObjectPlacer(this);
    public DefaultObject objArray[] = new DefaultObject[20];
    public DefaultEntity entityArray[] = new DefaultEntity[20];

    public int currGameState;
    public final int gameTitle = 0, gameRunning = 1, gamePaused = 2, gameDialogue = 3;


    /**
     * initializes stuff at the start of the game, including placing entities and objects on their corresponding spots, starts playing music and displaying titlescreen
     * any sort of audio can be played as long as you type in its index
     */
    public void game_stuffInitializer() {
        objectPlacer.objectSetter();
        objectPlacer.entitySetter();
        playSound_MUSIC(0);
        //stopSound_MUSIC();
        currGameState = gameTitle;
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
                //System.out.println("FPS: " + FPSShower);
                System.out.println("GAME RUNNING");
                FPSShower = 0;
                FPSCounter = 0;
            }
        }
    }

    public void update() {

        if (currGameState == gameRunning) {
            player.update();
            for (int i = 0; i < entityArray.length; i++) {
                if (entityArray[i] != null) {
                    entityArray[i].entityUpdate();
                }
            }
        }
        if (currGameState == gamePaused) {

        }


    }

    /**
     * draws everything in the game
     * the placement of drawing different stuff is important, otherwise it would incorrectly overlap
     * tiles are drawn first, objects are second, entities third, player fourth and interface is last
     * only draws them if the game isn't on the title screen
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2D = (Graphics2D) graphics;

        if (currGameState == gameTitle) {
            userInterface.drawInterface(g2D);
        } else {
            tileManager.tileDraw(g2D);

            for (int i = 0; i < objArray.length; i++) {
                if (objArray[i] != null) {
                    objArray[i].drawObject(g2D, this);
                }
            }

            for (int i = 0; i < entityArray.length; i++) {
                if (entityArray[i] != null) {
                    entityArray[i].entityDraw(g2D);
                }
            }

            player.draw(g2D);

            userInterface.drawInterface(g2D);

            g2D.dispose();
        }
    }

    public void playSound_MUSIC(int i) {
        gameMusic.soundGetter(i);
        gameMusic.soundPlay();
        gameMusic.soundLoop();
    }

    public void stopSound_MUSIC() {
        gameMusic.soundStop();
    }

    public void playSound_EFFECT(int i) {
        gameSound.soundGetter(i);
        gameSound.soundPlay();
    }
}
