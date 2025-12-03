package PaooGame.Tiles;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;

    public static Tile[] tiles = new Tile[256];
    //public static Tile Floor_1 = new Floor_1(0);
    //public static Tile Floor_2 = new Floor_2(1);
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void Draw(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public int getId() {
        return id;
    }
}