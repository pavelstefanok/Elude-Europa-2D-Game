package PaooGame.Entity.Enemy;

import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Fly_Queen extends Enemy{

    public Fly_Queen(int x,int y)
    {
        super(x,y,Assets.fly_queen.getWidth(), Assets.fly_queen.getHeight(), 3,4,3,0.8,5, true,false);
    }

    public void Update(Player player, ArrayList<Enemy> enemies){
        super.Update(player);
        if ( this.hp <=0 )
        {
            enemies.add(new Fly(this.Position_x, this.Position_y));
            enemies.add(new Fly(this.Position_x - 50, this.Position_y));
            enemies.add(new Fly(this.Position_x - 50, this.Position_y + 50));
        }
    }

    @Override
    public void Update() {
    }

    public void Draw(Graphics g,int cx,int cy)
    {
        super.Draw(g,cx,cy);
        g.drawImage(Assets.fly_queen,this.Position_x-cx,this.Position_y-cy,this.width,this.height,null);
    }

}
