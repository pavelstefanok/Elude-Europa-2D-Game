package PaooGame.Entity.Enemy;

import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class Horfus_Bomber extends Enemy{

    private long explosionStartTime = 0;
    public boolean boom;
    public boolean boomFinished = false;
    public Horfus_Bomber(int x,int y)
    {
        super(x,y,Assets.horfus_bomber.getWidth(),Assets.horfus_bomber.getHeight(), 3,4,3,0.8,5, true,false);
    }

    @Override
    public void Update(Player player){
        super.Update(player);
        if (player.getBounds().intersects(this.getBounds())) {
            this.hp = 0;
        }

        if (this.hp <= 0 && !boom) {
            Explode(player);
            boom = true;
            explosionStartTime = System.currentTimeMillis();
        }

        if (boom && System.currentTimeMillis() - explosionStartTime > 400) {
            boomFinished = true;
        }
    }

    @Override
    public void Update() {}

    public void Explode(Player player) {
        Rectangle explosionArea = new Rectangle(this.Position_x - 50, this.Position_y - 50, this.width + 100, this.height + 100);
        if (explosionArea.intersects(player.getBounds())) {
            player.takeDamage(2);
        }
    }

    @Override
    public void Draw(Graphics g,int cx,int cy) {
        super.Draw(g,cx,cy);
        g.drawImage(Assets.horfus_bomber, this.Position_x-cx, this.Position_y-cy, this.width, this.height, null);

        // explozia
        if (this.hp <= 0 && boom && !boomFinished) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(255, 0, 0, 100));
            int explosionX = this.Position_x-cx - 50;
            int explosionY = this.Position_y-cy - 50;
            int explosionWidth = this.width + 100;
            int explosionHeight = this.height + 100;
            g2d.fillOval(explosionX, explosionY, explosionWidth, explosionHeight);
        }
    }
}
