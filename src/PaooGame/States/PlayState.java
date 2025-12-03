package PaooGame.States;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import PaooGame.Entity.Bomb;
import PaooGame.Entity.Enemy.*;
import PaooGame.Entity.Items.Item;
import PaooGame.Entity.Items.PickUP;
import PaooGame.Entity.Player;
import PaooGame.Entity.Projectile;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Input.Controls;
import PaooGame.Tiles.TileManager;
import PaooGame.harta.GenerareHarta;



public class PlayState extends State {
    private final Map<Point, ArrayList<Enemy>> camereCuInamici = new HashMap<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<PickUP> pickups = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();

    private Item item;
    private boolean spawnItem = true;
    private boolean drawItem;
    Player Player = new Player(0,0);
    private Game game;
    
    
    private final int ts = 32, w = 60, h = 34, sw = 1920, sh = 1080;
    private int rx, ry, cx, cy;
    private int nivelCurent = 1;
    private final Controls tasta = new Controls();
    private final TileManager tm = new TileManager();
    private char[][] map;
    private final Random rand = new Random();


    public PlayState(Game g, int nivel) {
        this.game = g;
        nivelCurent = nivel;
        this.map = GenerareHarta.Harta(nivelCurent);
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 'S') {
                    Player.Position_x = x * ts;
                    Player.Position_y = y * ts;
                    rx = x / w;
                    ry = y / h;
                    updateCam();
                    return;
                }
            }
        }
    }

    private void updateCam() {
        drawItem = false;
        cx = rx * w * ts;
        cy = ry * h * ts;
        System.out.println("camx:" + cx + ", camy:" + cy);
        Point key = new Point(rx, ry);

        if (!camereCuInamici.containsKey(key)) {
            if (map[ry * h + h / 2][rx * w + w / 2] == 'F') {
                spawnItem = true;
                
                ArrayList<Enemy> bossRoom = new ArrayList<>();
                bossRoom.add(new Horlock(1000+cx, 800+cy)); 
                camereCuInamici.put(key, bossRoom);
            } else {
                spawnItem = true;
                camereCuInamici.put(key, genereazaInamiciPentruCamera(rx, ry));
            }
        }else spawnItem = false;

        
        enemies = camereCuInamici.get(key);
    }


    private ArrayList<Enemy> genereazaInamiciPentruCamera(int camX, int camY) {
        ArrayList<Enemy> lista = new ArrayList<>();
        Random rand = new Random();
        int nr = rand.nextInt(3) + 1; 

        int xStart = camX * w + 2;
        int xEnd = (camX + 1) * w - 2;

        int yStart = camY * h + 2;
        int yEnd = (camY + 1) * h - 2;

        
        ArrayList<Class<? extends Enemy>> inamiciDisponibili = new ArrayList<>();
        inamiciDisponibili.add(Horfus_Bomber.class);  
        inamiciDisponibili.add(Horfus_Guard.class);
        inamiciDisponibili.add(Big_Horfus_Guard.class);
        inamiciDisponibili.add(Fly.class);

        for (int i = 0; i < nr; i++) {
            int tx = rand.nextInt(xEnd-500 - xStart+500) + xStart;
            int ty = rand.nextInt(yEnd-500 - yStart+500) + yStart;
            int px = tx * ts;
            int py = ty * ts;

            
            Class<? extends Enemy> inamicClass = inamiciDisponibili.get(rand.nextInt(inamiciDisponibili.size()));

            try {
                
                Enemy e = inamicClass.getConstructor(int.class, int.class).newInstance(px, py);
                lista.add(e);
                System.out.println("  Inamic la: " + px + ", " + py);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lista;
    }


    private void verificaUsi() {
        if (!enemies.isEmpty()) return;
        int tx = Player.Position_x / ts;
        int ty = Player.Position_y / ts;
        char tile = map[ty][tx];

        if (tile == '&' || tile == '%') {
            if (tile == '%' && map[ry * h + h / 2][rx * w + w / 2] == 'F') {
                if (nivelCurent >= 3) {
                    System.exit(0);
                } else {
                    nivelCurent++;
                    game.setCurrentState(new PlayState(game, nivelCurent));
                    return;
                }
            }

            if (ty % h == 0) {
                ry--;
                Player.Position_y = (ry * h + h - 2) * ts;
                Player.Position_x = (rx * w + w / 2) * ts;
                updateCam();
            } else if (ty % h == h - 1) {
                ry++;
                Player.Position_y = (ry * h + 1) * ts;
                Player.Position_x = (rx * w + w / 2) * ts;
                updateCam();
            } else if (tx % w == 0) {
                rx--;
                Player.Position_x = (rx * w + w - 2) * ts;
                Player.Position_y = (ry * h + h / 2) * ts;
                updateCam();
            } else if (tx % w == w - 1) {
                rx++;
                Player.Position_x = (rx * w + 1) * ts;
                Player.Position_y = (ry * h + h / 2) * ts;
                updateCam();
            }
        }
    }


    @Override
    public void Update() {
        verificaUsi();
        Player.Update();

        if(Controls.Space_pressed && Player.bomb > 0)
        {
            bombs.add(new Bomb(Player.Position_x + 150,Player.Position_y));
            Controls.Space_pressed = false;
            Player.bomb-- ;
        }
        if (Controls.Enter_pressed) {
            game.setCurrentState(game.getMenuState());
            MenuState.startMusic();
            Controls.Enter_pressed = false;
        }

        if (enemies.isEmpty() && spawnItem)
        {
            item = this.SpawnItem();
            spawnItem = false;
            drawItem = true;

        }

        if (enemies.isEmpty() && !spawnItem)
        {
            if (Controls.E_pressed && Player.Position_x-cx > item.Position_x - 100 && Player.Position_x-cx < item.Position_x + 100 && Player.Position_y-cy > item.Position_y - 100 && Player.Position_y-cy < item.Position_y + 100)
            {
                Player.takeItem(item);
                drawItem = false;
            }
        }


        for (Enemy enemy : enemies) {
            if (Player.getBounds().intersects(enemy.getBounds())) {
                Player.CollisionPlayerEnemy(enemy);
            }
        }


        for (int i = 0; i < enemies.size(); i++) {
            for (int j = i + 1; j < enemies.size(); j++) {
                CollisionEnemies(enemies.get(i), enemies.get(j));
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);

            if (e instanceof Fly_Queen) {
                ((Fly_Queen) e).Update(Player, enemies);
            } else {
                e.Update(Player);
            }
            if (e.isDead()) {
                if (e instanceof Horfus_Bomber) {
                    Horfus_Bomber b = (Horfus_Bomber) e;
                    if (b.boom && b.boomFinished) {
                        PickUP drop = b.SpawnPickUP();
                        if (drop != null) {
                            pickups.add(drop);
                        }
                        enemies.remove(i);
                        i--;
                    }
                } else {
                    PickUP drop = e.SpawnPickUP();
                    if (drop != null) pickups.add(drop);
                    enemies.remove(i);
                    i--;
                }
            }
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            bomb.Update();
            if (bomb.shouldRemove()) {
                bombs.remove(i);
                i--;
            }
            for (Enemy enemy : enemies) {
                if (bomb.explosionArea.intersects(enemy.getBounds())) {
                    enemy.takeDamage(3);
                }
            }
        }

        for (Enemy enemy : enemies) {
            for (Projectile p : Player.getProjectiles()) {
                if (enemy.getBounds().intersects(p.getBounds())) {
                    enemy.takeDamage(p.getDamage());
                    p.deactivate();
                }
            }
        }

        for (Enemy enemy : enemies) {
            for (Projectile p : enemy.getProjectiles()) {
                if (p.isActive() && Player.getBounds().intersects(p.getBounds())) {
                    Player.CollisionPlayerProjectile(p);
                    p.deactivate();
                }
            }
        }

        for (int i = 0; i < pickups.size(); i++) {
            PickUP pickUP = pickups.get(i);
            if (Player.getBounds().intersects(pickUP.getBounds())) {
                Player.takePickUP(pickUP);
                pickups.remove(i);
                i--;
            }
        }

    }

    @Override
    public void Draw(Graphics g) {
        int ox = cx / ts;
        int oy = cy / ts;

        tm.drawMap(g, map, ts, ox, oy, sw, sh);



        Player.Draw(g,cx,cy);
        drawHealthHUD(g,Player);
        drawStatsHUD(g);

        for (Enemy e : enemies) {
            e.Draw(g,cx,cy);
        }
        for (PickUP p : pickups) {
            p.Draw(g,cx,cy);
        }
        for (Bomb bomb : bombs) {
            bomb.Draw(g,cx,cy);
        }

        if (drawItem)
        {
            item.Draw(g,cx,cy);
        }
        if ( drawItem && Player.Position_x-cx > item.Position_x - 100 && Player.Position_x-cx < item.Position_x + 100 && Player.Position_y-cy > item.Position_y - 100 && Player.Position_y-cy < item.Position_y+ 100 )
        {
            this.drawItemDetails(g,item);
        }

        
        
    }


    public void CollisionEnemies(Enemy e1, Enemy e2) {
        Rectangle r1 = e1.getBounds();
        Rectangle r2 = e2.getBounds();

        if (r1.intersects(r2)) {
            double dx = e1.Position_x - e2.Position_x;
            double dy = e1.Position_y - e2.Position_y;

            if (dx == 0 && dy == 0) {
                dx = Math.random() - 0.5;
                dy = Math.random() - 0.5;
            }

            
            double dist = Math.sqrt(dx * dx + dy * dy);
            double nx = dx / dist;
            double ny = dy / dist;

            Rectangle intersection = r1.intersection(r2);
            double overlap = Math.min(intersection.width, intersection.height);

            
            double pushAmount = overlap / 2.0;

            e1.Position_x += nx * pushAmount;
            e1.Position_y += ny * pushAmount;

            e2.Position_x -= nx * pushAmount;
            e2.Position_y -= ny * pushAmount;
        }
    }

    public Item SpawnItem() {
        int chance = (int) (Math.random() * 105) + 1;
        if (chance <= 50) {
            int chance_tier1 = (int) (Math.random() * 6) + 1;
            switch (chance_tier1) {
                case (1): {
                    return new Item(Assets.Hearth_of_vitality, "HEARTH OF VITALITY", "MAXIMUM HP -> 5","ITEM TIER I",100, 100);
                }
                case (2): {
                    return new Item(Assets.Boots_of_agility, "BOOTS OF AGILITY", "PLAYER SPEED -> 6","ITEM TIER I",100, 100);
                }
                case (3): {
                    return new Item(Assets.Armour_of_defense, "ARMOUR OF DEFENSE", "PLAYER ARMOUR -> 0.25","ITEM TIER I",100, 100);
                }
                case (4): {
                    return new Item(Assets.Projectile_of_pain, "PROJECTILE OF PAIN", "DAMAGE -> 1.75","ITEM TIER I",100, 100);
                }
                case (5): {
                    return  new Item(Assets.Reload_of_novice, "RELOAD OF NOVICE", "ATTACK SPEED -> 1.3","ITEM TIER I",100, 100);
                }
                case (6): {
                    return new Item(Assets.Speed_of_arrow, "SPEED OF ARROW", "PROJECTILE SPEED -> 6","ITEM TIER I",100, 100);
                }
            }
        }

        if (chance > 50 && chance <= 85) {
            int chance_tier2 = (int) (Math.random() * 6) + 1;
            switch (chance_tier2) {
                case (1): {
                    return new Item(Assets.Hearth_of_life, "HEARTH OF LIFE","MAXIMUM HP -> 6", "ITEM TIER II",100, 100);
                }
                case (2): {
                    return new Item(Assets.Boots_of_speed, "BOOTS OF SPEED","PLAYER SPEED -> 7", "ITEM TIER II",100, 100);
                }
                case (3): {
                    return new Item(Assets.Armour_of_iron, "ARMOUR OF IRON", "PLAYER ARMOUR -> 0.5","ITEM TIER II",100, 100);
                }
                case (4): {
                    return new Item(Assets.Projectile_of_devastation, "PROJECTILE OF DEVASTATION", "DAMAGE -> 2.5","ITEM TIER II",100, 100);
                }
                case (5): {
                    return new Item(Assets.Reload_of_soldier, "RELOAD OF SOLDIER", "ATTACK SPEED -> 1.5","ITEM TIER II",100, 100);
                }
                case (6): {
                    return new Item(Assets.Speed_of_bolt, "SPEED OF BOLT", "PROJECTILE SPEED -> 7","ITEM TIER II",100, 100);
                }
            }
        }

        if (chance > 85 && chance <= 100) {
            int chance_tier3 = (int) (Math.random() * 6) + 1;
            switch (chance_tier3) {
                case (1): {
                    return new Item(Assets.Hearth_of_titans, "HEARTH OF TITANS", "MAXIMUM HP -> 8","ITEM TIER III",100, 100);
                }
                case (2): {
                    return new Item(Assets.Boots_of_sprint, "BOOTS OF SPRINT", "PLAYER SPEED -> 8","ITEM TIER III",100, 100);
                }
                case (3): {
                    return new Item(Assets.Armour_of_guardian, "ARMOUR OF GUARDIAN", "PLAYER ARMOUR -> 1","ITEM TIER III",100, 100);
                }
                case (4): {
                    return new Item(Assets.Projectile_of_fury, "PROJECTILE OF FURY", "DAMAGE -> 3.5","ITEM TIER III",100, 100);
                }
                case (5): {
                    return new Item(Assets.Reload_of_general, "RELOAD OF GENERAL", "ATTACK SPEED -> 1.7","ITEM TIER III",100, 100);
                }
                case (6): {
                    return new Item(Assets.Speed_of_flash, "SPEED OF FLASH", "PROJECTILE SPEED -> 8","ITEM TIER III",100, 100);
                }
            }
        }

        if (chance > 100) {
            int chance_tier4 = (int) (Math.random() * 6) + 1;
            switch (chance_tier4) {
                case (1): {
                    return new Item(Assets.Elixir_of_the_gods, "ELIXIR OF THE GODS", "MAXIMUM HP -> 10","ITEM TIER IV",100, 100);
                }
                case (2): {
                    return new Item(Assets.Wings_of_astral_rush, "WINGS OF ASTRAL RUSH","PLAYER SPEED -> 9", "ITEM TIER IV",100, 100);
                }
                case (3): {
                    return new Item(Assets.Shield_of_imortality, "SHIELD OF IMORTALITY", "PLAYER ARMOUR -> 2","ITEM TIER IV",100, 100);
                }
                case (4): {
                    return  new Item(Assets.Viper_Bite, "VIPER BITE", "DAMAGE -> 5","ITEM TIER IV",100, 100);
                }
                case (5): {
                    return new Item(Assets.Celestial_fire, "CELESTIAL FIRE", "ATTACK SPEED -> 2","ITEM TIER IV",100, 100);
                }
                case (6): {
                    return new Item(Assets.Lightning_strike, "LIGHTNING STRIKE", "PROJECTILE SPEED -> 9","ITEM TIER IV",100, 100);
                }

            }
        }
        return null;
    }

    public void drawItemDetails(Graphics g, Item item) {
        Graphics2D g2d = (Graphics2D) g;

        
        String title = " Press E to pick up";
        String name = item.Boost;
        String description = item.Details;
        String tierText = item.Tier;
        boolean isTierIV = item.Tier != null && item.Tier.equalsIgnoreCase("ITEM TIER IV");

        
        int width = 300;
        int height = 140;
        int Pos_x = 40;
        int Pos_y = 1080 - height - 40;


        
        if (isTierIV) {
            for (int i = 1; i <= 3; i++) {
                int glowSize = 4 * i;
                Color glowColor = new Color(218,165,32, 80 / i);
                g2d.setColor(glowColor);
                g2d.fillRoundRect(Pos_x - i * 2, Pos_y - i * 2, width + glowSize, height + glowSize, 20 + glowSize, 20 + glowSize);
            }
        } else {
            for (int i = 6; i >= 1; i--) {
                float alpha = 0.03f * i;
                g2d.setColor(new Color(0, 255, 255, (int)(alpha * 255)));
                g2d.fillRoundRect(Pos_x - i, Pos_y - i, width + 2 * i, height + 2 * i, 20 + i, 20 + i);
            }
        }

        
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRoundRect(Pos_x, Pos_y, width, height, 20, 20);

        
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(Pos_x, Pos_y, width, height, 20, 20);

        
        Font titleFont = new Font("Arial", Font.ROMAN_BASELINE, 16);
        Font nameFont = new Font("Arial", Font.BOLD, 20);
        Font tierFont = new Font("Arial", Font.BOLD, 16);
        Font descFont = new Font("Arial", Font.PLAIN, 14);

        
        FontMetrics fm;
        int centerX = Pos_x + width / 2;

        
        g2d.setFont(titleFont);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.CYAN);
        g2d.drawString(title, centerX - fm.stringWidth(title) / 2, Pos_y + 25);

        
        g2d.setFont(nameFont);
        fm = g2d.getFontMetrics();
        g2d.setColor(isTierIV ? new Color(255, 215, 0) : Color.WHITE);
        g2d.drawString(name, centerX - fm.stringWidth(name) / 2, Pos_y + 60);

        
        g2d.setFont(tierFont);
        fm = g2d.getFontMetrics();
        g2d.setColor(isTierIV ? new Color(255, 215, 0) : Color.LIGHT_GRAY);
        g2d.drawString(tierText, centerX - fm.stringWidth(tierText) / 2, Pos_y + 85);

        
        g2d.setFont(descFont);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.WHITE);
        g2d.drawString(description, centerX - fm.stringWidth(description) / 2, Pos_y + 110);
    }

    public void drawHealthHUD(Graphics g, Player player) {

        Graphics2D g2d = (Graphics2D) g;

        int heartX = 10;
        int heartY = 10;
        int spacing = 8;
        int heartWidth = 32;

        double totalHearts = player.max_hp;
        double remainingHP = player.hp;

        g2d.setColor(new Color(0, 0, 0, 120));
        g2d.fillRoundRect(15, 70,  150, 300, 20, 20);

        for (int i = 0; i < totalHearts; i++) {
            BufferedImage heartImage;

            if (remainingHP >= 1.0) {
                heartImage = Assets.heart_full;
                remainingHP -= 1.0;
            } else if (remainingHP >= 0.75) {
                heartImage = Assets.heart_3sferturi;
                remainingHP -= 0.75;
            } else if (remainingHP >= 0.5) {
                heartImage = Assets.heart_half;
                remainingHP -= 0.5;
            } else if (remainingHP >= 0.25) {
                heartImage = Assets.heart_sfert;
                remainingHP -= 0.25;
            } else {
                heartImage = Assets.heart_empty;
            }

            if (heartImage != null) {
                g2d.drawImage(heartImage, heartX + i * (heartWidth + spacing), heartY, null);
            }
        }
    }

    public void drawStatsHUD(Graphics g) {
        int iconSize = 64;
        int padding = 10;
        int Pos_x= 10;
        int Pos_y = 70;

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.BLACK);

        g.drawImage(Assets.bomb_pickup, Pos_x , Pos_y, iconSize, iconSize, null);
        g.drawString("  :    " + Player.bomb, Pos_x + iconSize + padding, Pos_y + iconSize / 2 + 8);

        g.drawImage(Assets.bullet_right, Pos_x, Pos_y + 40, iconSize, iconSize, null);
        g.drawString("--> " + Player.damage, Pos_x + iconSize + padding, Pos_y + 40 + iconSize / 2 + 8);

        g.drawImage(Assets.speed_icon, Pos_x, Pos_y + 80, iconSize, iconSize, null);
        g.drawString("--> " + Player.speed, Pos_x + iconSize + padding, Pos_y + 80 + iconSize / 2 + 8);

        g.drawImage(Assets.attack_speed_icon, Pos_x, Pos_y + 120, iconSize, iconSize, null);
        g.drawString("--> " + Player.attackSpeed, Pos_x + iconSize + padding, Pos_y + 120 + iconSize / 2 + 8);

        g.drawImage(Assets.projectile_speed_icon, Pos_x, Pos_y + 160, iconSize, iconSize, null);
        g.drawString("--> " + (int)Player.projectileSpeed, Pos_x + iconSize + padding, Pos_y + 160 + iconSize / 2 + 8);

        g.drawImage(Assets.armour_icon, Pos_x, Pos_y + 200, iconSize, iconSize, null);
        g.drawString("--> " + Player.armour, Pos_x + iconSize + padding, Pos_y + 200 + iconSize / 2 + 8);
    }
}