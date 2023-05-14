package view.gameplay.fights;

import view.characters.Wizard;
import view.ennemies.Enemy;
import view.ennemies.EnemyList;
import view.spells.PlayerSpell;
import view.spells.Spell;
import view.userinterfaces.Text;

import java.util.Scanner;

import static view.userinterfaces.Cli.*;

    public class TrollFight {


        public static int startFight(Wizard wizard, PlayerSpell[] playerSpells) {
            scanner = new Scanner(System.in);
            Enemy enemy = EnemyList.TROLL.getEnemy();
            System.out.println("Alors que vous vous promeniez paisiblement dans les toilettes du donjon, vous entendez soudain un grognement sourd. En vous retournant, vous tombez nez à nez avec un troll de 2 mètres de haut ! Il vous regarde d'un air menaçant, prêt à vous charger. ");
            while (wizard.isAlive()) {
                System.out.println("Que décidez vous de faire :");
                System.out.println("1. L'attaquer avec vos mains ");
                System.out.println("2. Utiliser un sort ");

                int choice = badInput();

                switch (choice) {
                    case 1:
                        System.out.println("Bien tenté, mais vos coups de poing ont simplement chatouillé le troll.\\nIl vous a attrapé et alors qu'il allait vous tuer un professeur est intervenu pour vous sortir de ce mauvais pas.\\nVous avez eu chaud, mais il serait peut-être sage d'écouter davantage les cours de Défense contre les forces du Mal à l'avenir.\n");
                        wizard.takeDamage(enemy.getDamage(), enemy);
                        break;
                    case 2:
                        SpellChoice(playerSpells);

                        int spellIndex = 1;
                        for (PlayerSpell spell : playerSpells) {
                            if (!spell.isLocked()) {
                                spellIndex++;
                            }
                        }
                        Scanner scanner = getScanner();
                        int spellChoice = badInput();
                        while (spellChoice < 1 || spellChoice >= spellIndex) {
                            Text.askText(39);
                            spellChoice = scanner.nextInt();
                        }
                        PlayerSpell chosenSpell = playerSpells[spellChoice - 1];
                        Spell spell = new Spell();
                        if (spell.isSuccess(wizard, chosenSpell)) {
                            System.out.println("Bravo, vous avez réussi votre sort ! ");
                            int modifiedDamage = spell.getModifiedDamage(wizard, chosenSpell);
                            enemy.setHealth(enemy.getHealth() - modifiedDamage); System.out.println("Vous avez fait " + modifiedDamage + " de dégâts.");

                        } else {
                            System.out.println("Malheuresemement votre sort a échoué.");
                            wizard.takeDamage(enemy.getDamage(), enemy);
                        }
                        break;
                    default:
                        break;
                }
                if (enemy.getHealth() <= 0) {
                    enemyDefeated(enemy.getName());
                    return 1;
                }
            }

            return 0;
        }
    }
