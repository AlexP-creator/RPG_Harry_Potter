package view.gameplay;

import view.userinterfaces.Cli;
import view.userinterfaces.Gui;
import view.userinterfaces.UserInterfaces;

import java.util.Scanner;

import static view.userinterfaces.Cli.badInput;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Console ou interface graphique ?");
        System.out.println("1 - Console");
        System.out.println("2 - Interface graphique");
        int choice = badInput();
        UserInterfaces userInteract;
        if (choice == 1) {
            userInteract = new Cli();
        } else {
            userInteract = new Gui();
        }
        Gameplay.main(userInteract);
    }

}