package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.io.IOException;

public class TileManager {
    private HashMap<Character, BufferedImage> tileSprites;

    public TileManager() {
        tileSprites = new HashMap<>();

        try {
            tileSprites.put('↓', ImageIO.read(getClass().getResource("/textures/zidlvl1susa.png")));
            tileSprites.put('↑', ImageIO.read(getClass().getResource("/textures/zidlvl1jos.png")));
            tileSprites.put('←', ImageIO.read(getClass().getResource("/textures/zidlvl1dreapta.png")));
            tileSprites.put('→', ImageIO.read(getClass().getResource("/textures/zidlvl1stanga.png")));
            tileSprites.put('↘', ImageIO.read(getClass().getResource("/textures/coltsusstanga.png")));
            tileSprites.put('↙', ImageIO.read(getClass().getResource("/textures/coltsusdreapta.png")));
            tileSprites.put('↗', ImageIO.read(getClass().getResource("/textures/coltjosstanga.png")));
            tileSprites.put('↖', ImageIO.read(getClass().getResource("/textures/coltjosdreapta.png")));
            tileSprites.put('`', ImageIO.read(getClass().getResource("/textures/Tile Level 2_1.png")));
            tileSprites.put('F', ImageIO.read(getClass().getResource("/textures/CHAIR1.png")));
            tileSprites.put('*', ImageIO.read(getClass().getResource("/textures/STONE2.png")));
            tileSprites.put('%', ImageIO.read(getClass().getResource("/textures/Stone 1 test.png")));
            tileSprites.put('⇘', ImageIO.read(getClass().getResource("/textures/coltsusstanga.png")));
            tileSprites.put('⇙', ImageIO.read(getClass().getResource("/textures/coltsusdreapta.png")));
            tileSprites.put('⇗', ImageIO.read(getClass().getResource("/textures/coltjosstanga.png")));
            tileSprites.put('⇖', ImageIO.read(getClass().getResource("/textures/coltjosdreapta.png")));
            tileSprites.put('⇂', ImageIO.read(getClass().getResource("/textures/zidlvl1susa.png")));
            tileSprites.put('↾', ImageIO.read(getClass().getResource("/textures/zidlvl1jos.png")));
            tileSprites.put('↼', ImageIO.read(getClass().getResource("/textures/zidlvl1dreapta.png")));
            tileSprites.put('⇀', ImageIO.read(getClass().getResource("/textures/zidlvl1stanga.png")));
            tileSprites.put('~', ImageIO.read(getClass().getResource("/textures/Tile Level 3_1.png")));
            tileSprites.put('➴', ImageIO.read(getClass().getResource("/textures/coltsusstanga.png")));
            tileSprites.put('↚', ImageIO.read(getClass().getResource("/textures/coltsusdreapta.png")));
            tileSprites.put('➶', ImageIO.read(getClass().getResource("/textures/coltjosstanga.png")));
            tileSprites.put('↸', ImageIO.read(getClass().getResource("/textures/coltjosdreapta.png")));
            tileSprites.put('⇣', ImageIO.read(getClass().getResource("/textures/zidlvl1susa.png")));
            tileSprites.put('⇡', ImageIO.read(getClass().getResource("/textures/zidlvl1jos.png")));
            tileSprites.put('⇠', ImageIO.read(getClass().getResource("/textures/zidlvl1dreapta.png")));
            tileSprites.put('⇢', ImageIO.read(getClass().getResource("/textures/zidlvl1stanga.png")));
            tileSprites.put('"', ImageIO.read(getClass().getResource("/textures/Tile Level 2_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMap(Graphics g, char[][] map, int tileSize, int offsetX, int offsetY, int screenWidth, int screenHeight) {
        int tilesX = screenWidth / tileSize + 1;
        int tilesY = screenHeight / tileSize + 1;

        for (int y = 0; y < tilesY; y++) {
            for (int x = 0; x < tilesX; x++) {
                int mapX = x + offsetX;
                int mapY = y + offsetY;

                if (mapY >= 0 && mapY < map.length && mapX >= 0 && mapX < map[0].length) {
                    char tileChar = map[mapY][mapX];
                    BufferedImage img = tileSprites.get(tileChar);
                    if (img != null) {
                        g.drawImage(img, x * tileSize, y * tileSize, tileSize, tileSize, null);
                    }
                }
            }
        }
    }







    public void drawTile(Graphics g, char tileChar, int x, int y, int tileSize) {
        BufferedImage img = tileSprites.get(tileChar);
        if (img != null) {
            g.drawImage(img, x, y, tileSize, tileSize, null);
        }
    }
    public void drawCamera(Graphics g, char[][] map, int camTileX, int camTileY, int camW, int camH, int tileSize) {
        for (int y = 0; y < camH; y++) {
            for (int x = 0; x < camW; x++) {
                int mapX = camTileX + x;
                int mapY = camTileY + y;

                if (mapY >= 0 && mapY < map.length && mapX >= 0 && mapX < map[0].length) {
                    char tileChar = map[mapY][mapX];
                    drawTile(g, tileChar, x * tileSize, y * tileSize, tileSize);
                }
            }
        }
    }

}

