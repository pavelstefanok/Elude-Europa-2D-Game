package PaooGame.Entity;

import java.awt.*;

public abstract class Entity {
    public int Position_x;
    public int Position_y;
    protected int width, height;

    public Entity(int Position_x, int Position_y, int width, int height) {
        this.Position_x = Position_x;
        this.Position_y = Position_y;
        this.width = width;
        this.height = height;
    }

    public abstract void Update();
    public abstract void Draw(Graphics g,int cx,int cy);

    public Rectangle getBounds() {
        return new Rectangle(this.Position_x-this.width/2, this.Position_y-this.height/2, this.width, this.height);
    }

}