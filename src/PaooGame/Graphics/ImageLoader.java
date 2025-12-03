package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.System.exit;


public class ImageLoader
{
    public static BufferedImage LoadImage(String path) {
        try {
            if (ImageLoader.class.getResource(path) == null) {
                System.err.println("Nu s-a gasit imaginea: " + path);
                exit(1); // opreste jocul ca sa nu crape mai tarziu
            }
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}