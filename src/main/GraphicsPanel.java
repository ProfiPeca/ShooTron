package main;

import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel implements Runnable{

    final int tileSize = 16;
    final int itemScale = 3;
    final int scaledTileSize = tileSize * itemScale;

    final int screenColumnNumber = 16;
    final int screenRowNumber = 12;
    final int screenHeight = scaledTileSize * screenRowNumber;
    final int screenWidth = scaledTileSize * screenColumnNumber;

    Thread gameThread;
    public GraphicsPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    @Override
    public void run() {

    }
}
