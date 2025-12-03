package PaooGame.Entity.Enemy;

import PaooGame.Entity.Player;
import PaooGame.Entity.Projectile;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class Horfus_Star extends Enemy{

    public Horfus_Star(int x,int y)
    {
        super(x,y,Assets.horfus_star.getWidth(), Assets.horfus_star.getHeight(), 3,4,3,0.8,5, true,true);
    }


    @Override
    public void shoot(Player player) {

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastProjectile < (1000 / attackSpeed)) {
            return;
        }

        int centerX = this.Position_x + this.width / 2;
        int centerY = this.Position_y + this.height / 2;

        double[][] directions = {
                {0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (double[] dir : directions) {
            projectiles.add(new Projectile(Assets.projectile_enemy_gold, centerX, centerY, Assets.projectile_enemy_gold.getWidth(), Assets.projectile_enemy_gold.getHeight(), dir[0], dir[1], projectileSpeed, damage));
        }
        lastProjectile = currentTime;
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
        g.drawImage(Assets.horfus_star,this.Position_x-cx,this.Position_y-cy,this.width,this.height,null);
    }
}
