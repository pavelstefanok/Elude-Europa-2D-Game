package PaooGame.Entity;
import PaooGame.Entity.Enemy.Enemy;
import PaooGame.Entity.Items.Item;
import PaooGame.Entity.Items.PickUP;
import PaooGame.Graphics.Assets;
import PaooGame.Input.Controls;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Character {

    private double knockbackX = 0;
    private double knockbackY = 0;
    private int knockbackTimer = 0;

    public boolean isInvincible = false;
    private long invincibilityStartTime = 0;
    private final int INVINCIBILITY_DURATION_MS = 2000;

    public int bomb , max_bomb ;
    public double max_hp;
    public double attackCooldown = 1.0 / attackSpeed;
    private long lastProjectile = 0;
    private ArrayList<Projectile> projectiles = new ArrayList<>();

    public Player(int x, int y) {
        super(x, y, Assets.player.getWidth(), Assets.player.getHeight(), 4, 1, 15, 1, 5);
        this.max_hp = super.hp;
        this.bomb = 2;
        this.max_bomb = 5;
    }


    public void CollisionPlayerEnemy(Enemy enemy) {

        if (isInvincible) return;

        double dx = this.Position_x - enemy.Position_x;
        double dy = this.Position_y - enemy.Position_y;

        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist == 0)
            dist = 1;

        knockbackX = (dx / dist) * 10;
        knockbackY = (dy / dist) * 10;
        knockbackTimer = 10;

        isInvincible = true;
        invincibilityStartTime = System.currentTimeMillis();

        this.hp -= 1;
    }

    public void CollisionPlayerProjectile(Projectile projectile){

        if (isInvincible) return;
        isInvincible = true;
        invincibilityStartTime = System.currentTimeMillis();
        this.hp -= projectile.getDamage();

    }
    @Override
    public void Update() {

        if (hp <= 0)
        {
            //mort
        }
        long currentTime = System.currentTimeMillis();

        int dx = 0;
        int dy = 0;
        if (Controls.A_pressed )
            dx -= 1;
        if (Controls.D_pressed )
            dx += 1;
        if (Controls.W_pressed )
            dy -= 1;
        if (Controls.S_pressed)
            dy += 1;

        if (dx != 0 || dy != 0) {
            double length = Math.sqrt(dx * dx + dy * dy);
            double normX = dx / length;
            double normY = dy / length;

            this.Position_x += normX * speed;
            this.Position_y += normY * speed;
        }



        if (knockbackTimer > 0) {
            this.Position_x += knockbackX;
            this.Position_y += knockbackY;
            knockbackTimer--;

            knockbackX *= 0.9;
            knockbackY *= 0.9;
        } else {
            if (Controls.Right_pressed && currentTime - lastProjectile >= (attackCooldown * 1000)) {
                projectiles.add(new Projectile(Assets.bullet_right, this.Position_x , this.Position_y - this.height/4, 8, 8, "Dreapta", this.projectileSpeed, this.damage));
                lastProjectile = currentTime;
            }
            if (Controls.Left_pressed && currentTime - lastProjectile >= (attackCooldown * 1000)) {
                projectiles.add(new Projectile(Assets.bullet_left, this.Position_x-this.width/2, this.Position_y, 8, 8, "Stanga", this.projectileSpeed, this.damage));
                lastProjectile = currentTime;
            }
            if (Controls.Up_pressed && currentTime - lastProjectile >= (attackCooldown * 1000)) {
                projectiles.add(new Projectile(Assets.bullet_up, this.Position_x - this.width/4, this.Position_y - this.height/2, 8, 8, "Sus", this.projectileSpeed, this.damage));
                lastProjectile = currentTime;
            }
            if (Controls.Down_pressed && currentTime - lastProjectile >= (attackCooldown * 1000)) {
                projectiles.add(new Projectile(Assets.bullet_down, this.Position_x - this.width / 4, this.Position_y + this.height/4, 8, 8, "Jos", this.projectileSpeed, this.damage));
                lastProjectile = currentTime;
            }
        }

        if (isInvincible && System.currentTimeMillis() - invincibilityStartTime >= INVINCIBILITY_DURATION_MS) {
            isInvincible = false;
        }

        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            p.Update();
            if (!p.isActive()) {
                projectiles.remove(i);
                i--;
            }
        }
    }

    public void takePickUP(PickUP PickUP)
    {
        switch (PickUP.Boost)
        {
            case("HP") : {
                if (this.hp + 0.5 <= this.max_hp){
                    this.hp += 0.5;
                }
                else
                {
                    this.hp = this.max_hp;
                }
                break;
            }
            case("BIG HP") : {
                if (this.hp + 1 <= this.max_hp){
                    this.hp += 1;
                }
                else
                {
                    this.hp = this.max_hp;
                }
                break;
            }

            case("BOMB") : {
                if (this.bomb != this.max_bomb)
                {
                    this.bomb++ ;
                }
                break;
            }
        }
    }

    public void takeItem(Item Item) {

        if (Item.isActive){

            switch (Item.Boost) {

            case ("HEARTH OF VITALITY"): {
                this.max_hp = 5;
                break;
            }
            case ("HEARTH OF LIFE"): {
                this.max_hp = 6;
                break;
            }
            case ("HEARTH OF TITANS"): {
                this.max_hp = 8;
                break;
            }
            case ("ELIXIR OF THE GODS"): {
                this.max_hp = 10;
                break;
            }

            case ("BOOTS OF AGILITY"): {
                this.speed = 6;
                break;
            }
            case ("BOOTS OF SPEED"): {
                this.speed = 7;
                break;
            }
            case ("BOOTS OF SPRINT"): {
                this.speed = 8;
                break;
            }
            case ("WINGS OF ASTRAL RUSH"): {
                this.speed = 9;
                break;
            }

            case ("ARMOUR OF DEFENSE"): {
                this.armour = 0.25;
                break;
            }
            case ("ARMOUR OF IRON"): {
                this.armour = 0.5;
                break;
            }
            case ("ARMOUR OF GUARDIAN"): {
                this.armour = 1;
                break;
            }
            case ("SHIELD OF IMORTALITY"): {
                this.armour = 2;
                break;
            }

            case ("PROJECTILE OF PAIN"): {
                this.damage = 1.75;
                break;
            }
            case ("PROJECTILE OF DEVASTATION"): {
                this.damage = 2.5;
                break;
            }
            case ("PROJECTILE OF FURY"): {
                this.damage = 3.5;
                break;
            }
            case ("VIPER BITE"): {
                this.damage = 5;
                break;
            }

            case ("RELOAD OF NOVICE"): {
                this.attackSpeed = 1.3;
                break;
            }
            case ("RELOAD OF SOLDIER"): {
                this.attackSpeed = 1.5;
                break;
            }
            case ("RELOAD OF GENERAL"): {
                this.attackSpeed = 1.7;
                break;
            }
            case ("CELESTIAL FIRE"): {
                this.attackSpeed = 2;
                break;
            }

            case ("SPEED OF ARROW"): {
                this.projectileSpeed = 6;
                break;
            }
            case ("SPEED OF BOLT"): {
                this.projectileSpeed = 7;
                break;
            }
            case ("SPEED OF FLASH"): {
                this.projectileSpeed = 8;
                break;
            }
            case ("LIGHTNING STRIKE"): {
                this.projectileSpeed = 9;
                break;
            }

        }
            Item.isActive = false;
        }
    }

    @Override
    public void Draw(Graphics g,int cx, int cy) {

        if (isInvincible) {
            if ((System.currentTimeMillis() / 100) % 2 == 0) {
                return;
            }
        }
        g.drawImage(Assets.player, this.Position_x-cx-this.width/2,this.Position_y-cy-this.height/2, this.width, this.height, null);

        for (Projectile p : projectiles) {
            p.Draw(g,cx,cy);
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
}

