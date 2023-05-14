package view.userinterfaces;

import view.Scanner.Colors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Text {

    private static final String nameFile = "C:\\Users\\alexa\\OneDrive\\Documents\\A1\\JAVA\\HarryRPG\\src\\main\\java\\view\\harry.txt";

    public static String readLine(int numeroLigne) {
        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
            String line;
            int numLine = 1;
            while ((line = br.readLine()) != null) {
                if (numLine == numeroLigne) {
                    return line;
                }
                numLine++;
            }
        } catch (IOException e) {
            System.err.format("Erreur de lecture du fichier : %s%n", e);
        }
        return null;
    }
    public static String askText(int numeroLigne) {
        String line = readLine(numeroLigne);
        if (line != null) {
            System.out.println(line);
        }
        return line;
    }

    public static String askText(int numeroLigne, String color) {
        String line = readLine(numeroLigne);
        if (line != null) {
            System.out.println(color + line + Colors.RESET);
        }
        return line ;
    }



}
