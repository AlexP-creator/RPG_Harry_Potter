package view.ennemies;

public enum EnemyList {
    TROLL(new Enemy("Troll", 50, 20)),
    BASILISK(new Enemy("Basilisk", 100, 30)),
    DEMENTOR(new Enemy("Dementor", 150, 40)),

    PETER_PETEGROW(new Enemy("Peter Petegrow",200,50)),
    DOLORES_OMBRAGE(new Enemy("Dolores Ombrage",250,60)),
    DETAH_EATERS(new Enemy("Death Eaters",300,70)),

    VOLDEMORT(new Enemy("Voldemort",500,80));

    private Enemy enemy;

    EnemyList(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
