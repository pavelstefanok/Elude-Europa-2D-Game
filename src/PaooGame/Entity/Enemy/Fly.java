package PaooGame.Entity.Enemy;

import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fly extends Enemy{

    public Fly(int x,int y)
    {
        super(x, y, Assets.fly.getWidth(), Assets.fly.getHeight(), 2, 1, 4, 0,0,true,false);
    }

    @Override
    public void Update(Player player) {
        super.Update(player);
    }

    @Override
    public void Update()
    {
    }

    @Override
    public void Draw(Graphics g,int cx,int cy)
    {
        super.Draw(g,cx,cy);
        g.drawImage(Assets.fly,this.Position_x-cx,this.Position_y-cy,this.width,this.height,null );

    }
}
