package PaooGame.Entity.Enemy;

import PaooGame.Entity.Character;
import PaooGame.Entity.Items.PickUP;
import PaooGame.Entity.Player;
import PaooGame.Entity.Projectile;
import PaooGame.Graphics.Assets;


import java.awt.*;
import java.util.ArrayList;

public abstract class Enemy extends Character {
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public long lastProjectile = 0;
    public boolean attack_projectile;
    private boolean isMobile;
    public Enemy(int x, int y, int width, int height, double hp, double damage, int speed, double attackSpeed, double projectileSpeed,boolean isMobile, boolean attack_projectile) {
        super(x, y, width, height, hp, damage, speed, attackSpeed, projectileSpeed);
        this.attack_projectile = attack_projectile;
        this.isMobile = isMobile;
    }

    @Override
    public void Update(Player player) {

        for (Projectile p : projectiles) {
            p.Update();
        }

        if (isMobile) {
            int dx = player.Position_x - this.Position_x;
            int dy = player.Position_y - this.Position_y;
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist > 1) {
                double dirX = dx / dist;
                double dirY = dy / dist;
                double randomOffsetX = (Math.random() - 0.6) * 0.9;
                double randomOffsetY = (Math.random() - 0.6) * 0.9;

                this.Position_x += (dirX + randomOffsetX) * speed;
                this.Position_y += (dirY + randomOffsetY) * speed;
            }
        }

        if (attack_projectile) {
                shoot(player);
        }
    }

    public void shoot(Player player) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastProjectile < (1000 / attackSpeed)) {
            return;
        }

        String direction;
        if (Math.abs(player.Position_x - this.Position_x) > Math.abs(player.Position_y - this.Position_y)) {
            direction = (player.Position_x > this.Position_x) ? "Dreapta" : "Stanga";
        } else {
            direction = (player.Position_y > this.Position_y) ? "Jos" : "Sus";
        }

        int projX = this.Position_x + width / 2;
        int projY = this.Position_y + height / 2;

        Projectile p = new Projectile(Assets.projectile_enemy_red, projX, projY, 8, 8, direction, projectileSpeed, damage);
        projectiles.add(p);

        lastProjectile = currentTime;
    }

    public boolean isDead()
    {
        return this.hp <= 0;
    }


    public PickUP SpawnPickUP()
    {
        int chance = (int)(Math.random() * 100) + 1;
        if (chance <= 15) {
            return new PickUP(Assets.health_potion, "HP", this.Position_x, this.Position_y);
        } else if (chance >= 45 && chance <= 50) {
            return new PickUP(Assets.big_health_potion, "BIG HP", this.Position_x, this.Position_y);
        } else if (chance >= 90) {
            return new PickUP(Assets.bomb_pickup, "BOMB", this.Position_x, this.Position_y);
        }
        return null;
    }

    @Override
  public void Draw(Graphics g,int cx,int cy) {
        for (Projectile p : projectiles) {
            if (p.isActive())
                p.Draw(g,cx,cy);
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
}


