package PaooGame.Entity.Enemy;

import PaooGame.Entity.Player;
import PaooGame.Entity.Projectile;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class Big_Horfus_Guard extends Enemy{

    public Big_Horfus_Guard(int x,int y)
    {
        super(x,y,Assets.big_horfus_guard.getWidth(), Assets.big_horfus_guard.getHeight(), 3,4,3,0.8,5, true,false);
    }

    @Override
    public void Update(Player player){
        super.Update(player);
        this.shoot(player);

    }

    @Override
    public void Update() {
    }

    @Override
    public void shoot(Player player) {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastProjectile < (1000 / attackSpeed)) {
            return;
        }
            projectiles.add(new Projectile(Assets.projectile_enemy_purple,Position_x + width / 2, Position_y, Assets.projectile_enemy_purple.getWidth(), Assets.projectile_enemy_purple.getHeight(), "Sus", projectileSpeed, damage));
            projectiles.add(new Projectile(Assets.projectile_enemy_purple,Position_x + width / 2, Position_y + height, Assets.projectile_enemy_purple.getWidth(), Assets.projectile_enemy_purple.getHeight(), "Jos", projectileSpeed, damage));
            projectiles.add(new Projectile(Assets.projectile_enemy_purple,Position_x, Position_y + height / 2, Assets.projectile_enemy_purple.getWidth(), Assets.projectile_enemy_purple.getHeight(), "Stanga", projectileSpeed, damage));
            projectiles.add(new Projectile(Assets.projectile_enemy_purple,Position_x + width, Position_y + height / 2, Assets.projectile_enemy_purple.getWidth(), Assets.projectile_enemy_purple.getHeight(), "Dreapta", projectileSpeed, damage));
            lastProjectile = currentTime;
        }



    public void Draw(Graphics g,int cx, int cy)
    {
        super.Draw(g,cx,cy);
        g.drawImage(Assets.big_horfus_guard,this.Position_x-cx,this.Position_y-cy,this.width,this.height,null);
    }
}
