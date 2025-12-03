package PaooGame.Entity.Items;

import PaooGame.Entity.Entity;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PickUP extends Entity {

    public String Boost;
    BufferedImage Sprite_Pickup;
    public PickUP(BufferedImage Sprite_PickUP,String Boost,int x, int y)
    {
        super( x, y,64,64);
        this.Sprite_Pickup = Sprite_PickUP;
        this.Boost = Boost;
    }

    public void Update()
    {
    }
    public void Draw(Graphics g,int cx, int cy)
    {
        g.drawImage(this.Sprite_Pickup, this.Position_x-cx,this.Position_y-cy,this.Sprite_Pickup.getWidth(),this.Sprite_Pickup.getHeight(),null);
    }
}
