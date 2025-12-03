package PaooGame.Entity.Enemy;

import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class Horfus_Blop extends Enemy{

    public Horfus_Blop(int x,int y)
    {
        super(x,y,Assets.horfus_blop.getWidth(), Assets.horfus_blop.getHeight(), 3,5,0,0.9,2, false,true);
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
        g.drawImage(Assets.horfus_blop,this.Position_x-cx,this.Position_y-cy,this.width,this.height,null);
    }
}
