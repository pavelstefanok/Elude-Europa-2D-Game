package PaooGame.Entity.Items;

import PaooGame.Entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {

    public String Boost,Details,Tier;
    BufferedImage Sprite_Item;
    public boolean isActive = true;
    public Item(BufferedImage Sprite_Item, String Boost,String Details,String tier,int x,int y){
        super( 960, 540,  64, 64);
        this.Boost = Boost;
        this.Sprite_Item = Sprite_Item;
        this.Details = Details;
        this.Tier = tier;
    }

    public void Update()
    {
    }
    public void Draw(Graphics g,int cx, int cy)
    {
        g.drawImage(this.Sprite_Item, this.Position_x,this.Position_y,this.Sprite_Item.getWidth(),this.Sprite_Item.getHeight(),null);
    }

}
