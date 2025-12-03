package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends Entity{

    private long spawnTime;
    public boolean exploded = false;
    private int currentFrame = 0;
    private long lastFrameSwitch = 0;
    private long frameDelay = 500;
    public Rectangle explosionArea = new Rectangle(0, 0, 0, 0);

    private final long fuseDuration = 3000;

    public Bomb(int x, int y){
        super(x,y,Assets.bomb_pickup.getWidth(),Assets.bomb_pickup.getHeight());
        spawnTime = System.currentTimeMillis();
    }

    public void Update(){

        long now = System.currentTimeMillis();

        if (!exploded) {
            if (now - spawnTime >= fuseDuration) {
                explode();
            } else if (now - lastFrameSwitch >= frameDelay) {
                currentFrame = (currentFrame + 1) % 2;
                lastFrameSwitch = now;
            }
        }
        if (exploded)
        {
            explosionArea = new Rectangle(this.Position_x - 50, this.Position_y - 50, 100, 100);
        }
    }


    public void explode() {

        exploded = true;
    }

    public boolean shouldRemove() {
        return exploded && System.currentTimeMillis() - spawnTime > fuseDuration + 500;
    }

    public void Draw(Graphics g,int cx,int cy)
    {
        if (exploded) {
            g.drawImage(Assets.bomb_explosion, this.Position_x-cx, this.Position_y-cy, null);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(255, 0, 0, 100));
            int explosionX = this.Position_x - 50;
            int explosionY = this.Position_y - 50;
            int explosionWidth = this.width + 100;
            int explosionHeight = this.height + 100;
            g2d.fillOval(explosionX, explosionY, explosionWidth, explosionHeight);
        } else {
            BufferedImage currentImage = (currentFrame == 0) ? Assets.bomb_1 : Assets.bomb_2;
            g.drawImage(currentImage, this.Position_x-cx, this.Position_y-cy, null);
        }    }
}
