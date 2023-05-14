package view.userinterfaces;


import view.characters.Pet;
import view.characters.Wizard;
import view.items.Potion;

public class Gui implements UserInterfaces {

    @Override
    public String askUserName() {
        return null;
    }

    @Override
    public Pet selectPet() {
        return null;
    }

    @Override
    public String afficher(Wizard wizard) {
        return null;
    }

    @Override
    public int victoryChoice(Wizard wizard, int damage) {
        return 0;
    }

    @Override
    public int managePotion(Wizard wizard, Potion potion) {
        return 0;
    }

    @Override
    public void afterfight(Wizard wizard, int damage, Potion potion, int spellIndex) {

    }

    @Override
    public void afterfight2(Wizard wizard, int damage, Potion potion) {

    }

    @Override
    public void gryffindorSword(Wizard player) {

    }
}