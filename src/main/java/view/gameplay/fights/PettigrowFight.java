package view.gameplay.fights;

import view.characters.Wizard;
import view.userinterfaces.Text;

import java.util.Scanner;

public class PettigrowFight {

    public static void startFight(Wizard wizard) { Text.askText(58);

        System.out.println("Vous devez vous échapper en utilisant le sortilège accio, entrer 'Accio' pour attirer le portoloin et vous enfuir ! ");
        try {
            waitFiveSeconds();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String input = getInputFromUser();
        checkInput(input, wizard);
    }

    public static void waitFiveSeconds() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Le temps est écoulé !");

    }

    public static String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void checkInput(String input, Wizard wizard) {
        if (input.equalsIgnoreCase("Accio")) {
            System.out.println("Bravo, vous prenez le portoloin et réussissez à vous échapper !");
        } else {
            System.out.println("Malheureusement, vous avez raté votre sort, Voldemort en profite et vous envoie un sort fatal !");

        }
    }
}
