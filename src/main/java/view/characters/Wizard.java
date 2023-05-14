package view.characters;
import javafx.scene.control.Label;
import view.Scanner.Colors;
import view.ennemies.Enemy;


public class Wizard {
    private final String name;
    private House house;
    public int health;

    public Wizard(String name) {
        this.name = name;
        this.health = 2000;

    }

    public void takeDamage(int damage, Enemy enemy) {
        if (this.house != null && this.house.getName().equals(Colors.RED+"Gryffindor"+Colors.RESET)) {
            damage = (int) Math.ceil(damage /2.0);
        }
        System.out.println("Le " + enemy.getName() + " vous inflige " + damage + " de dégâts.");
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            System.out.println(Colors.RED_2+"\nVous avez été vaincu(e)..."+Colors.RESET);
            System.exit(0);
        }

    }




    public String getName() {
        return this.name;
    }

    public House getHouse() {
        return this.house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getHouseName() {
        return this.house.getName();
    }

    public int getHealth() {
        return this.health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}
