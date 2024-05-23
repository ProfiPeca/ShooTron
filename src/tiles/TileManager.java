package tiles;

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
        mapLoader("/maps/testMap2.txt");
    }

    /**
     * loads map from a text file by splitting recently read row
     */
    public void tileImageGetter() {
        try {

            tiles[0] = new Tile();
            tiles[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/brickTile1.png"));
            tiles[1] = new Tile();
            tiles[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/brickTile2.png"));
            tiles[2] = new Tile();
            tiles[2].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/dirtTile.png"));
            tiles[3] = new Tile();
            tiles[3].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grassTile.png"));
            tiles[4] = new Tile();
            tiles[4].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/labTile.png"));
            tiles[5] = new Tile();
            tiles[5].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/labWallTile.png"));
            tiles[5].hasCollision = true;
            tiles[6] = new Tile();
            tiles[6].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/sandTile.png"));
            tiles[7] = new Tile();
            tiles[7].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/waterTile.png"));
            tiles[7].hasCollision = true;
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
     * only draws tiles that are inbound of the screen player is in
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

            if (xWorld + gamePanel.scaledTileSize> gamePanel.player.xCords - gamePanel.player.screenX && xWorld - gamePanel.scaledTileSize< gamePanel.player.xCords + gamePanel.player.screenX && yWorld + gamePanel.scaledTileSize> gamePanel.player.yCords - gamePanel.player.screenY && yWorld - gamePanel.scaledTileSize< gamePanel.player.yCords + gamePanel.player.screenY) {
                g2D.drawImage(tiles[tileID].tileImage, xScreen, yScreen, gamePanel.scaledTileSize, gamePanel.scaledTileSize, null);
            }
            tileColumn++;


            if (tileColumn == gamePanel.worldColumnNumber) {
                tileColumn = 0;

                tileRow++;

            }
        }
    }
}
