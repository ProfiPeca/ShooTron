package tiles;

import main.GameOptimizer;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int tilePosStorer[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        tilePosStorer = new int[gamePanel.worldColumnNumber][gamePanel.worldRowNumber];
        tileImageGetter();
        mapLoader("/maps/testMap3.txt");
    }

    /**
     * loads map from a text file by splitting recently read row
     */
    public void tileImageGetter() {

        tilePreScale(0, "labTile", false);
        tilePreScale(1, "labWallTile", true);
        tilePreScale(2, "waterTile", true);
        tilePreScale(3, "brickTile1", true);
        tilePreScale(4, "brickTile2", false);
        tilePreScale(5, "dirtTile", false);
        tilePreScale(6, "grassTile", false);
        tilePreScale(7, "sandTile", false);
    }
    public void tilePreScale(int i, String imageName, boolean hasCol) {
        GameOptimizer gameOptimizer = new GameOptimizer();
        try{
            tiles[i] = new Tile();
            tiles[i].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName +".png"));
            tiles[i].tileImage = gameOptimizer.imagePreScale(tiles[i].tileImage, gamePanel.scaledTileSize, gamePanel.scaledTileSize);
            tiles[i].hasCollision = hasCol;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mapLoader(String mapPath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int column = 0;
            int row = 0;
            while (column < gamePanel.worldColumnNumber && row < gamePanel.worldRowNumber) {

                String currRow = bufferedReader.readLine();

                while (column < gamePanel.worldColumnNumber) {
                    String tileRow[] = currRow.split(" ");
                    int tileRowNumToInt = Integer.parseInt(tileRow[column]);
                    tilePosStorer[column][row] = tileRowNumToInt;
                    column++;
                }
                if (column == gamePanel.worldColumnNumber) {
                    column = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
        }
    }

    /**
     * @param g2D draws all objects in the game
     *            only draws tiles that are inbound of the screen player is in
     */
    public void tileDraw(Graphics2D g2D) {

        int tileColumn = 0;
        int tileRow = 0;

        int tileID = 0;

        while (tileColumn < gamePanel.worldColumnNumber && tileRow < gamePanel.worldRowNumber) {

            try {
                tileID = tilePosStorer[tileColumn][tileRow];
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            int xWorld = tileColumn * gamePanel.scaledTileSize;
            int yWorld = tileRow * gamePanel.scaledTileSize;
            int xScreen = xWorld - gamePanel.player.xCords + gamePanel.player.screenX;
            int yScreen = yWorld - gamePanel.player.yCords + gamePanel.player.screenY;

            if (xWorld + gamePanel.scaledTileSize > gamePanel.player.xCords - gamePanel.player.screenX && xWorld - gamePanel.scaledTileSize < gamePanel.player.xCords + gamePanel.player.screenX && yWorld + gamePanel.scaledTileSize > gamePanel.player.yCords - gamePanel.player.screenY && yWorld - gamePanel.scaledTileSize < gamePanel.player.yCords + gamePanel.player.screenY) {
                g2D.drawImage(tiles[tileID].tileImage, xScreen, yScreen, null);
            }
            tileColumn++;


            if (tileColumn == gamePanel.worldColumnNumber) {
                tileColumn = 0;

                tileRow++;

            }
        }
    }
}
