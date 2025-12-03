package PaooGame.Entity;


public abstract class Character extends Entity {
    public double hp;
    public double damage;
    public double attackSpeed;
    public double projectileSpeed;
    public double armour;
    public int speed;

    public Character(int Position_x, int Position_y, int width, int height, double hp, double damage, int speed, double attackSpeed, double projectileSpeed) {
        super(Position_x, Position_y, width, height);
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.attackSpeed = attackSpeed;
        this.projectileSpeed = projectileSpeed;
        this.armour = 0;
    }

    public void Update(Player player)
    {
        this.Update();
    }
    public abstract void Update();

    public void takeDamage(double damage)
    {
        this.hp -= damage + armour;
    }


}
