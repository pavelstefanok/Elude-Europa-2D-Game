package PaooGame.Graphics;

import java.awt.image.BufferedImage;


public class Assets
{
    public static BufferedImage horlock;
    public static BufferedImage horfus;
    public static BufferedImage big_horfus;
    public static BufferedImage horfus_star;
    public static BufferedImage big_horfus_guard;
    public static BufferedImage horfus_bomber;
    public static BufferedImage fly_queen;
    public static BufferedImage fly;
    public static BufferedImage horfus_guard;
    public static BufferedImage horfus_blop;

    public static BufferedImage bomb_1;
    public static BufferedImage bomb_2;
    public static BufferedImage bomb_explosion;


    public static BufferedImage bomb_pickup;
    public static BufferedImage health_potion;
    public static BufferedImage big_health_potion;

    public static BufferedImage projectile_enemy_red;
    public static BufferedImage projectile_enemy_purple;
    public static BufferedImage projectile_enemy_gold;

    public static BufferedImage bullet_right;
    public static BufferedImage bullet_left;
    public static BufferedImage bullet_up;
    public static BufferedImage bullet_down;
    public static BufferedImage floor_1;
    public static BufferedImage floor_2;
    public static BufferedImage menuBackground;
    public static BufferedImage player;

    public static BufferedImage heart_empty;
    public static BufferedImage heart_sfert;
    public static BufferedImage heart_half;
    public static BufferedImage heart_3sferturi;
    public static BufferedImage heart_full;

    //  Items
    public static BufferedImage Hearth_of_vitality;
    public static BufferedImage Hearth_of_life;
    public static BufferedImage Hearth_of_titans;
    public static BufferedImage Elixir_of_the_gods;

    public static BufferedImage Boots_of_agility;
    public static BufferedImage Boots_of_speed;
    public static BufferedImage Boots_of_sprint;
    public static BufferedImage Wings_of_astral_rush;

    public static BufferedImage Armour_of_defense;
    public static BufferedImage Armour_of_iron;
    public static BufferedImage Armour_of_guardian;
    public static BufferedImage Shield_of_imortality;

    public static BufferedImage Projectile_of_pain;
    public static BufferedImage Projectile_of_devastation;
    public static BufferedImage Projectile_of_fury;
    public static BufferedImage Viper_Bite;

    public static BufferedImage Reload_of_novice;
    public static BufferedImage Reload_of_soldier;
    public static BufferedImage Reload_of_general;
    public static BufferedImage Celestial_fire;

    public static BufferedImage Speed_of_arrow;
    public static BufferedImage Speed_of_bolt;
    public static BufferedImage Speed_of_flash;
    public static BufferedImage Lightning_strike;

    public static BufferedImage speed_icon;
    public static BufferedImage attack_speed_icon;
    public static BufferedImage armour_icon;
    public static BufferedImage projectile_speed_icon;
    public static BufferedImage key;



    public static void Init()
    {
        bullet_right = ImageLoader.LoadImage("/textures/Bullet_right.png");
        bullet_left = ImageLoader.LoadImage("/textures/Bullet_left.png");
        bullet_up = ImageLoader.LoadImage("/textures/Bullet_up.png");
        bullet_down = ImageLoader.LoadImage("/textures/Bullet_down.png");
        menuBackground = ImageLoader.LoadImage("/textures/Menu_Background.jpg");

        horfus  = ImageLoader.LoadImage("/textures/Horfus.png");
        big_horfus  = ImageLoader.LoadImage("/textures/Big_Horfus.png");
        horfus_star = ImageLoader.LoadImage("/textures/Horfus_Star.png");
        horfus_guard = ImageLoader.LoadImage("/textures/Horfus_Guard.png");
        big_horfus_guard = ImageLoader.LoadImage("/textures/Big_Horfus_Guard.png");
        horfus_blop = ImageLoader.LoadImage("/textures/Horfus_Blop.png");
        horfus_bomber = ImageLoader.LoadImage("/textures/Horfus_Bomber.png");
        fly = ImageLoader.LoadImage("/textures/Fly.png");
        fly_queen = ImageLoader.LoadImage("/textures/Fly_Queen.png");
        horlock = ImageLoader.LoadImage("/textures/Horlock.png");

        player = ImageLoader.LoadImage("/textures/Player.png");
        projectile_enemy_red = ImageLoader.LoadImage("/textures/Projectile_Enemy_Red.png");
        projectile_enemy_purple = ImageLoader.LoadImage("/textures/Projectile_Enemy_Purple.png");
        projectile_enemy_gold = ImageLoader.LoadImage("/textures/Projectile_Enemy_Gold.png");
        big_health_potion = ImageLoader.LoadImage("/textures/Big_health_potion.png");
        health_potion = ImageLoader.LoadImage("/textures/Health_potion.png");
        bomb_pickup = ImageLoader.LoadImage("/textures/Bomb_pickup.png");

        // HP ITEMS
        Hearth_of_vitality = ImageLoader.LoadImage("/textures/HP 1.png");
        Hearth_of_life = ImageLoader.LoadImage("/textures/HP 2.png");
        Hearth_of_titans = ImageLoader.LoadImage("/textures/HP 3.png");
        Elixir_of_the_gods = ImageLoader.LoadImage("/textures/HP 4.png");

        //SPEED ITEMS
        Boots_of_agility = ImageLoader.LoadImage("/textures/SPEED 1.png");
        Boots_of_speed = ImageLoader.LoadImage("/textures/SPEED 2.png");
        Boots_of_sprint = ImageLoader.LoadImage("/textures/SPEED 3.png");
        Wings_of_astral_rush = ImageLoader.LoadImage("/textures/SPEED 4.png");

        //ARMOUR ITEMS
        Armour_of_defense = ImageLoader.LoadImage("/textures/ARMOUR 1.png");
        Armour_of_iron = ImageLoader.LoadImage("/textures/ARMOUR 2.png");
        Armour_of_guardian = ImageLoader.LoadImage("/textures/ARMOUR 3.png");
        Shield_of_imortality = ImageLoader.LoadImage("/textures/ARMOUR 4.png");

        //DAMAGE ITEMS
        Projectile_of_pain = ImageLoader.LoadImage("/textures/DAMAGE 1.png");
        Projectile_of_devastation = ImageLoader.LoadImage("/textures/DAMAGE 2.png");
        Projectile_of_fury = ImageLoader.LoadImage("/textures/DAMAGE 3.png");
        Viper_Bite = ImageLoader.LoadImage("/textures/DAMAGE 4.png");

        //ATTACK SPEED ITEMS
        Reload_of_novice = ImageLoader.LoadImage("/textures/ATT SPEED 1.png");
        Reload_of_soldier = ImageLoader.LoadImage("/textures/ATT SPEED 2.png");
        Reload_of_general = ImageLoader.LoadImage("/textures/ATT SPEED 3.png");
        Celestial_fire = ImageLoader.LoadImage("/textures/ATT SPEED 4.png");

        //PROJECTILE SPEED ITEMS
        Speed_of_arrow = ImageLoader.LoadImage("/textures/PROJECTILE SPEED 1.png");
        Speed_of_bolt = ImageLoader.LoadImage("/textures/PROJECTILE SPEED 2.png");
        Speed_of_flash = ImageLoader.LoadImage("/textures/PROJECTILE SPEED 3.png");
        Lightning_strike = ImageLoader.LoadImage("/textures/PROJECTILE SPEED 4.png");

        heart_empty = ImageLoader.LoadImage("/textures/heart_goala.png");
        heart_sfert = ImageLoader.LoadImage("/textures/heart_sfert.png");
        heart_half = ImageLoader.LoadImage("/textures/heart_jumate.png");
        heart_3sferturi = ImageLoader.LoadImage("/textures/heart_3sferturi.png");
        heart_full = ImageLoader.LoadImage("/textures/heart_plina.png");

        bomb_1 = ImageLoader.LoadImage("/textures/Bomb_1.png");
        bomb_2 = ImageLoader.LoadImage("/textures/Bomb_2.png");
        bomb_explosion = ImageLoader.LoadImage("/textures/Bomb_explosion.png");

        //Icons
        speed_icon = ImageLoader.LoadImage("/textures/Speed_icon.png");
        attack_speed_icon = ImageLoader.LoadImage("/textures/Attack_speed_icon.png");
        armour_icon = ImageLoader.LoadImage("/textures/Armour_icon.png");
        projectile_speed_icon = ImageLoader.LoadImage("/textures/Projectile_speed_icon.png");
        key = ImageLoader.LoadImage("/textures/Key.png");













        // grass = sheet.crop(0, 0);
       // soil = sheet.crop(1, 0);
       // water = sheet.crop(2, 0);
       // mountain = sheet.crop(3, 0);
       // townGrass = sheet.crop(0, 1);
       // townGrassDestroyed = sheet.crop(1, 1);
       // townSoil = sheet.crop(2, 1);
       // tree = sheet.crop(3, 1);
       // playerLeft = sheet.crop(0, 2);
       // playerRight = sheet.crop(1, 2);
       // rockUp = sheet.crop(2, 2);
       // rockDown = sheet.crop(3, 2);
       // rockLeft = sheet.crop(0, 3);
       // rockRight = sheet.crop(1, 3);
    }
}