package novi;

import novi.higherlower.HigherLowerGame;
import novi.blackjack.BlackjackGame;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int gameSelection;

        String menu = "Press 1 for \"Higher or Lower\";\nPress 2 for \"Blackjack\";";

        Scanner inputScanner = new Scanner(System.in);
        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.println(menu);
            while (!inputScanner.hasNextInt()) {
                inputScanner.next(); // belangrijk!!
                System.out.println("Dat is geen geldige invoer!");
            }
            gameSelection = inputScanner.nextInt();
            if (gameSelection > 0 && gameSelection < 3) {
                inputIsValid = true;
                launchGame(inputScanner, gameSelection);
            } else {
                System.out.println("Dat is geen geldige selectie!");
            }
            System.out.println();
        }
    }

    private static void launchGame(Scanner s, int gameSelection) {
        Playable game = null;
        switch (gameSelection) {
            case 1: {
                game = new HigherLowerGame(s);
            }
            break;
            case 2: {
                game = new BlackjackGame(s);
            }
            break;
            default:{
                System.out.println("Oops... Er is iets misgegaan.");
            }
        }
        game.playGame();
    }
}
