package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity {
    private BufferedImage Sprite_Projectile;
    private String direction;
    private double speed, damage, dx, dy;
    private boolean activ = true;

    public Projectile(BufferedImage Sprite_Projectile ,int x, int y, int width, int height , String direction, double speed, double damage) {
        super(x, y, width, height);
        this.direction = direction;
        this.speed = speed;
        this.damage = damage;
        this.Sprite_Projectile = Sprite_Projectile;
    }

    public Projectile(BufferedImage Sprite_Projectile, int x, int y, int width, int height, double dx, double dy, double speed, double damage) {

        super(x,y,width,height);
        this.Sprite_Projectile = Sprite_Projectile;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        this.damage = damage;
        double len = Math.sqrt(dx * dx + dy * dy);
        if (len != 0) {
            this.dx /= len;
            this.dy /= len;
        }
    }

    @Override
    public void Update() {

        if (direction != null) {
            switch (direction) {
                case ("Dreapta"): {

                    Position_x += speed;
                    break;
                }
                case ("Stanga"): {

                    Position_x -= speed;
                    break;
                }
                case ("Sus"): {

                    Position_y -= speed;
                    break;
                }
                case ("Jos"): {

                    Position_y += speed;
                    break;
                }
            }
        } else{

                Position_x += dx * speed;
                Position_y += dy * speed;
        }

//        if (Position_x < 0 || Position_y < 0 || Position_x > 1920 || Position_y > 1080) {
//            activ = false;
//        }
    }

    @Override
    public void Draw(Graphics g,int cx,int cy) {
        g.drawImage(this.Sprite_Projectile, this.Position_x-cx, this.Position_y-cy,32,32,null);
    }

    public double getDamage() {
        return damage;
    }

    public boolean isActive() {
        return activ;
    }

    public void deactivate() {
        activ = false;
    }
}
