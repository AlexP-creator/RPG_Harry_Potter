package view.userinterfaces;

import view.Scanner.Colors;
import view.characters.Pet;
import view.characters.Wizard;
import view.characters.types.PetTypes;
import view.gameplay.Gameplay;
import view.items.Potion;
import view.spells.PlayerSpell;

import java.util.Scanner;



public class Cli implements UserInterfaces {

    public static Scanner scanner = null;
    static {
        scanner = new Scanner(System.in);
    }
    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
    public static int badInput() {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée non valide, veuillez réessayer.");
            }
        }
        return input;
    }




    @Override
    public String askUserName() {
        String userInput = "";
        boolean hasAnswered = false;
        while (!hasAnswered) {
            Text.askText(15);
            userInput = getScanner().nextLine();
            if (userInput.matches("[a-zA-Z]+")) {
                hasAnswered = true;
                System.out.println("Bienvenue, " + userInput + " !");
            } else {
                Text.askText(16);
            }
        }
        Wizard wizard = new Wizard(userInput);
        return wizard.getName();
    }

    public Pet selectPet() {
System.out.println("Maintenant, il est temps de choisir votre animal de compagnie. Rendez-vous à l'animalerie du Chemin de Traverse pour trouver l'animal qui sera votre ami fidèle tout au long de votre aventure. Vous avez le choix entre plusieurs créatures magiques :\n" +
        "une chouette \uD83E\uDD89\n" +
        "un crapeau \uD83D\uDC38\n" +
        "un rat \uD83D\uDC00\n" +
        "un chat \uD83D\uDC31\n" +
        "Après une légère hésitation, votre choix se porte sur un/une :");
        String petType = getScanner().nextLine();
        while (!Pet.isValidType(petType)) {
            System.out.println("Malheureusement, ce choix n'est pas disponible dans la boutique. Veuillez choisir parmi les animaux suivants : crapeau, rat, chouette ou chat.");
            petType = getScanner().nextLine();
        }
        System.out.println("Après une légère hésitation votre choix se porte sur un/une " + petType + " !");
        return new Pet(PetTypes.valueOf(petType.toUpperCase()));
    }




    public String afficher(Wizard wizard) {
        System.out.println("\n-----" + Colors.YELLOW + "RECAPITULATIF" + Colors.RESET + "-----");
        System.out.println("\uD83E\uDDD9 Nom du joueur : " + wizard.getName());
        System.out.println("\uD83C\uDFE0 Maison : " + wizard.getHouseName());
        System.out.println("\uD83E\uDE84 Sorts maîtrisés : ");

        for (PlayerSpell spell : PlayerSpell.values()) {
            if (!spell.isLocked()) {
                System.out.println(spell.getName());
            }
        }
        System.out.println("❤️ Vie : " + wizard.getHealth());
        System.out.println("-------------------------");

        return "Récapitulatif affiché.";
    }



        public int victoryChoice(Wizard wizard, int damage) {
            Text.askText(21);
            Text.askText(22);
            Text.askText(23);

            int choice = getScanner().nextInt();
            while (choice < 1 || choice > 2) {
                Text.askText(25);
                choice = getScanner().nextInt();
            }

            switch (choice) {
                case 1:
                    wizard.health += 10;
                    Text.askText(27);
                    break;
                case 2:
                    Text.askText(28);
                    Gameplay.Counter++;
                    break;
                default:
                    Text.askText(29);
                    break;
            }

            return choice;
        }

    public int managePotion(Wizard wizard, Potion potion) {
        System.out.println("Voulez-vous utiliser la potion '" + potion.getName() + "' ?");
        Text.askText(12);
        Text.askText(13);
        int choice = getScanner().nextInt();
        while (choice < 1 || choice > 2) {
            Text.askText(7);
            choice = getScanner().nextInt();
        }
        potion.usePotion(wizard, choice);

        switch (choice) {
            case 1:
                System.out.println("Vous avez utilisé la potion '" + potion.getName() + "' et récupéré " + potion.getHealthPoints() + " points de vie.");
                break;
            case 2:
                Text.askText(9);
                break;
            default:
                Text.askText(10);
                break;
        }

        return choice;
    }

    public static void enemyDefeated(String enemyName) {
        System.out.println("Vous avez vaincu le " + enemyName + " !");
    }

    public static void SpellChoice(PlayerSpell[] playerSpells) {
        Text.askText(43);
        int sortIndex = 1;
        for (PlayerSpell spell : playerSpells) {
            if (!spell.isLocked()) {
                System.out.println(sortIndex + ". " + spell.getName());
                sortIndex++;
            }
        }
    }
    public void afterfight(Wizard wizard, int damage, Potion potion, int spellIndex) {
        afficher(wizard);
        victoryChoice(wizard, damage);
        managePotion(wizard, potion);
        PlayerSpell.unlockSpell(spellIndex);
        afficher(wizard);
    }

    public void afterfight2(Wizard wizard, int damage, Potion potion) {
        afficher(wizard);
        victoryChoice(wizard, damage);
        managePotion(wizard, potion);

        afficher(wizard);
    }

    public void gryffindorSword(Wizard player) {
        if (player.getHouse().getName().equals(Colors.RED+"Gryffindor"+Colors.RESET)) {
            PlayerSpell.unlockSpell(6);
        }
    }
}



