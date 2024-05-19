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
    Tile[] tiles;
    int tilePosStorer[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        tilePosStorer = new int[gamePanel.screenColumnNumber][gamePanel.screenRowNumber];
        tileImageGetter();
        mapLoader("/maps/gameMap_1.txt");
    }

    /**
     * loads map from a text file by splitting recently read row
     */
    public void tileImageGetter() {
        try {
            tiles[0] = new Tile();
            tiles[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grassTile.png"));
            tiles[1] = new Tile();
            tiles[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/brickTile1.png"));
            tiles[2] = new Tile();
            tiles[2].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/brickTile2.png"));
            tiles[3] = new Tile();
            tiles[3].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/waterTile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mapLoader(String mapPath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int collumn = 0;
            int row = 0;
            while (collumn < gamePanel.screenColumnNumber && row < gamePanel.screenRowNumber) {
                String currRow = bufferedReader.readLine();
                while (collumn < gamePanel.screenColumnNumber) {
                    String tileRow[] = currRow.split(" ");
                    int tileRowNumToInt = Integer.parseInt(tileRow[collumn]);
                    tilePosStorer[collumn][row] = tileRowNumToInt;
                    collumn++;
                }
                if (collumn == gamePanel.screenColumnNumber) {
                    collumn = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
        }
    }

    public void tileDraw(Graphics2D g2D) {

        int tileColumn = 0;
        int tileRow = 0;
        int xCords = 0;
        int yCords = 0;
        int tileID = 0;

        while (tileColumn < gamePanel.screenColumnNumber && tileRow < gamePanel.screenWidth) {

            try {
                tileID = tilePosStorer[tileColumn][tileRow];
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            g2D.drawImage(tiles[tileID].tileImage, xCords, yCords, gamePanel.scaledTileSize, gamePanel.scaledTileSize, null);
            tileColumn++;
            xCords += gamePanel.scaledTileSize;

            if (tileColumn == gamePanel.screenColumnNumber) {
                tileColumn = 0;
                xCords = 0;
                tileRow++;
                yCords += gamePanel.scaledTileSize;
            }
        }
    }
}
