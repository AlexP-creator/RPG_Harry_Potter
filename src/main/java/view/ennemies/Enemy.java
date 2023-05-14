package view.ennemies;


import view.characters.Wizard;

public class Enemy {

    private String name;
    private int health;
    private int damage;

    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void attack(Wizard wizard) {
        int damage = getDamage();
        wizard.takeDamage(damage, this);
    }
    public boolean isAlive() {
        return health > 0;
    }




}
