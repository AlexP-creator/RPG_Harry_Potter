package view.items;

import view.Scanner.Colors;
import view.characters.House;
import view.characters.Wizard;

import java.util.Random;

public class SortingHat {
    private static final double[] HOUSE_PERCENTAGES = {0.25,0.25,0.25,0.25};
    private static final String[] HOUSE_NAMES = {Colors.BLUE+"Ravenclaw"+Colors.RESET,Colors.GRAY+ "Hufflepuff"+Colors.RESET,Colors.RED+"Gryffindor"+Colors.RESET, Colors.GREEN_2+"SLYTHERIN"+Colors.RESET};
    private static final Random random = new Random();

    public static void assignHouse(Wizard wizard) {
        double randomNumber = random.nextDouble();
        double currentPercentage = 0;
        for (int i = 0; i < HOUSE_PERCENTAGES.length; i++) {
            currentPercentage += HOUSE_PERCENTAGES[i];
            if (randomNumber <= currentPercentage) {
                House house = new House(HOUSE_NAMES[i]);
                wizard.setHouse(house);

                break;
            }
        }


    }


}

