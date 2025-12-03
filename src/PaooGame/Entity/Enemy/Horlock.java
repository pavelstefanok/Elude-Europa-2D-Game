package PaooGame.Entity.Enemy;

import PaooGame.Entity.Player;
import PaooGame.Entity.Projectile;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.util.Random;

public class Horlock extends Enemy{

    Random random = new Random();
    private long lastAttackTime = System.currentTimeMillis();
    private long attackCooldown = 1200 + random.nextInt(800);
    private double maxHp, displayedHp;

    public enum AttackType {
        TRIPLE_LINE_SHOT,
        FIVE_WAY_SHOT,
        GIANT_PROJECTILE
    }

    public Horlock(int x , int y){
        super(x,y,500,500,50,2,0,2,6,false,false);
        this.maxHp = hp;
        this.displayedHp = hp;
    }

    @Override
    public void Update(Player player) {

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAttackTime >= attackCooldown) {
            performRandomAttack(player);

            lastAttackTime = currentTime;
            attackCooldown = 1200 + random.nextInt(800);
        }
        super.Update(player);
        if (displayedHp > hp) {
            displayedHp -= 5;
            if (displayedHp < hp)
                displayedHp = hp;
        }
    }

    @Override
    public void Update()
    {
    }

    @Override
    public void Draw(Graphics g,int cx,int cy) {

        super.Draw(g,cx,cy);
        g.drawImage(Assets.horlock, Position_x-cx, Position_y-cy, width, height, null);
        drawBossHealthBar(g);
    }


    private void performRandomAttack(Player player) {
        int index = random.nextInt(AttackType.values().length);
        AttackType attack = AttackType.values()[index];

        switch (attack) {
            case TRIPLE_LINE_SHOT:
                shootTripleLine();
                break;
            case FIVE_WAY_SHOT:
                shootFiveWay();
                break;
            case GIANT_PROJECTILE:
                shootGiantProjectile(player);
                break;
        }
    }

    private void shootTripleLine() {

        projectiles.add(new Projectile(Assets.projectile_enemy_purple, this.Position_x, (this.Position_y + this.height) / 2 - 200, 32, 32, "Stanga", 5.0, 1));

        projectiles.add(new Projectile(Assets.projectile_enemy_purple, this.Position_x, (this.Position_y + this.height) / 2 , 32, 32, "Stanga", 5.0, 1));

        projectiles.add(new Projectile(Assets.projectile_enemy_purple, this.Position_x, (this.Position_y + this.height) / 2  + 200, 32, 32, "Stanga", 5.0, 1));
    }

    private void shootFiveWay() {

        for (int i = -2; i <= 2; i++) {
            double dy = i * 0.8;
            projectiles.add(new Projectile(Assets.projectile_enemy_red, this.Position_x, (this.Position_y + this.height) / 2 , 32, 32, -1, dy, 5, 1));
        }
    }

    private void shootGiantProjectile(Player player) {

        double dx = player.Position_x - this.Position_x;
        double dy = player.Position_y - this.Position_y;

        projectiles.add(new Projectile(Assets.projectile_enemy_gold, this.Position_x, (this.Position_y + this.height) / 2 , 128, 128, dx, dy, 4, 3));
    }

    private void drawBossHealthBar(Graphics g) {
        int screenWidth = 1920;
        int barWidth = 600;
        int barHeight = 25;
        int x = (screenWidth - barWidth) / 2;
        int y = 1000;

        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, barWidth, barHeight);

        double percent =  displayedHp / maxHp;
        int hpWidth = (int) (barWidth * percent);
        g.setColor(Color.RED);
        g.fillRect(x, y, hpWidth, barHeight);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, barWidth, barHeight);

        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int textX = x + (barWidth - fm.stringWidth("HORLOCK")) / 2;
        g.drawString("HORLOCK", textX, y - 10);
    }
}
