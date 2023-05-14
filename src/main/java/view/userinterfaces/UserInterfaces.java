package view.userinterfaces;

import view.characters.Pet;
import view.characters.Wizard;
import view.items.Potion;


public interface UserInterfaces {
    String askUserName();
    Pet selectPet();
    String afficher(Wizard wizard);

    int victoryChoice(Wizard wizard,int damage);
    int managePotion(Wizard wizard, Potion potion);

    void afterfight(Wizard wizard, int damage, Potion potion,int spellIndex);
   void afterfight2(Wizard wizard, int damage, Potion potion);

    void gryffindorSword(Wizard player);
}
