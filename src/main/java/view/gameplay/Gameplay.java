package view.gameplay;

import view.Scanner.Colors;
import view.characters.Wand;
import view.characters.Wizard;
import view.gameplay.fights.*;
import view.items.Potion;
import view.items.SortingHat;
import view.spells.PlayerSpell;
import view.userinterfaces.Text;
import view.userinterfaces.UserInterfaces;


public class Gameplay {

    public static int Counter = 0;
    public static void main(UserInterfaces userInteract) {


        PlayerSpell[] playerSpells = {PlayerSpell.WINGARDIUM_LEVIOSA, PlayerSpell.ACCIO,PlayerSpell.EXPECTO_PATRONUM,PlayerSpell. SECTUMSEMPRA,PlayerSpell.AVADA_KEDAVRA,PlayerSpell. EXPELLIARMUS,PlayerSpell.GRYFFINDORSWORD};
        playerSpells[0].setLocked(false);

        Potion potion1 = new Potion("Potion 1", 10);
        Potion potion2 = new Potion("Potion 2", 50);
        Wizard wizard = new Wizard(userInteract.askUserName());

        System.out.println(Colors.YELLOW+"   " +
                " (\"                         \uD83D\uDCAB BIENVENUE DANS LE MONDE MAGIQUE DE HARRY POTTER ! \uD83D\uDCAB"+Colors.RESET+"\n-------------------------\nVous êtes sur le point d'entrer dans une aventure palpitante où vous pourrez découvrir des sorts incroyables, des créatures fantastiques et des lieux mystérieux. \nCependant, vous devez vous méfier des dangers qui vous guettent. Vous rencontrerez peut-être des ennemis qui chercheront à vous nuire.\nVous devrez vous montrer vigilant et utiliser votre intelligence pour surmonter tous les obstacles.\nAlors, êtes-vous prêt à relever le défi et à devenir le héros de votre propre histoire ?");
        System.out.println("-------------------------");
        System.out.println("Rendez-vous chez Ollivander, le célèbre fabricant de baguettes magiques, pour trouver la baguette qui est faite pour vous.\n" +
                "Après avoir essayé plusieurs baguettes, vous en trouverez une qui est parfaitement adaptée à vos compétences et à votre personnalité. N'oubliez pas : c'est la baguette qui vous choisit, pas l'inverse. ");
        Wand wand = Wand.generateRandomWand();
        System.out.println("La baguette qui vous est alors confiée est"+Colors.PURPLE + wand +Colors.RESET+", cette baguette devrait vous permettre d'accomplir de grandes choses.\n" );
        userInteract.selectPet();
        System.out.println("Il est désormais temps de prendre le train pour rejoindre votre nouvelle école : Poudlard\n" +
                "Une fois dans la salle commune, le choipeau magique va permettre de vous assigner à une maison.\n");

        SortingHat.assignHouse(wizard);
        String assignedHouse = wizard.getHouse().getName();
        userInteract.gryffindorSword(wizard);
        System.out.println("Et après une longue réflexion, le choipeau magique décide de vous attribuer la maison : \n" + assignedHouse + "\n");
        userInteract.afficher(wizard);
        int damage = TrollFight.startFight(wizard, playerSpells);
        userInteract.afterfight(wizard,damage,potion1,1);
        BasilicFight.startFight(wizard, playerSpells);
        userInteract.afterfight(wizard,damage,potion1,2);
        DetractorsFight.startFight(wizard, playerSpells);
        userInteract.afterfight2(wizard,damage,potion1);
        PettigrowFight.startFight(wizard);
        userInteract.afterfight2(wizard,damage,potion2);
        DoloresFight.startFight(wizard,playerSpells);
        userInteract.afterfight(wizard,damage,potion2,3);
        DeathEatersFight.startFight(wizard,playerSpells);
        userInteract.afterfight(wizard,damage,potion2,4);
        PlayerSpell.unlockSpell(5);
        VoldemortFight.startFight(wizard,playerSpells);
        Text.askText(45);
        System.exit(0);

    }
}