package view.spells;
public enum PlayerSpell {
    WINGARDIUM_LEVIOSA("Wingardium Leviosa", 40, 0.6, true),
    ACCIO("Accio", 1, 1.0, true),
     EXPECTO_PATRONUM("Expecto Patronum",20,0.7,true),
    SECTUMSEMPRA("Sectumsempra",60,0.4,true),
    AVADA_KEDAVRA("Avada Kedavra",100,0.2,true),
    EXPELLIARMUS("Expeliarmus",60,0.4,true),

    GRYFFINDORSWORD("GryffindorSword",10,1.0,true);

    private final String name;
    private final int damage;
    private final double successRate;
    private boolean locked;

    PlayerSpell(String name, int damage, double successRate, boolean locked) {
        this.name = name;
        this.damage = damage;
        this.successRate = successRate;
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    public static void unlockSpell(int spellIndex) {
        if (spellIndex >= 0 && spellIndex < values().length) {
            values()[spellIndex].setLocked(false);
        }
    }

}
