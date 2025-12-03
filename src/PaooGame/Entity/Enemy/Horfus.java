package PaooGame.Entity.Enemy;

import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class Horfus extends Enemy{

    public Horfus(int x,int y)
    {
        super(x,y,84,84,3,4,3,0.8,5, true,false);
    }

    @Override
    public void Update(Player player){
        super.Update(player);
    }

    @Override
    public void Update() {
    }


    public void Draw(Graphics g,int cx,int cy)
    {
        super.Draw(g,cx,cy);
        g.drawImage(Assets.horfus_guard,this.Position_x-cx,this.Position_y-cy,this.width,this.height,null);
    }
}
