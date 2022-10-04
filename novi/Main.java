package novi;

import novi.enhanced_blackjack.EnhancedBlackjackGame;
import novi.enhanced_blackjack.LowDeckBlackjack;
import novi.enhanced_blackjack.SimpleBlackjack;
import novi.higherlower.HigherLowerGame;
import novi.blackjack.BlackjackGame;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int gameSelection;

        Scanner inputScanner = new Scanner(System.in);
        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.println("Press 1 for \"Higher or Lower\"");
            System.out.println("Press 2 for \"Blackjack\"");
            System.out.println("Press 3 for \"Enhanced Blackjack(full deck)\"");
            System.out.println("Press 4 for \"Enhanced Blackjack(low deck)\"");

            while (!inputScanner.hasNextInt()) {
                inputScanner.next(); // belangrijk!!
                System.out.println("Dat is geen geldige invoer!");
            }
            gameSelection = inputScanner.nextInt();
            if (gameSelection > 0 && gameSelection < 5) {
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
            case 3: {
                game = new SimpleBlackjack(s);
            }
            break;
            case 4: {
                game = new LowDeckBlackjack(s);
            }
            break;


            default:{
                System.out.println("Oops... Er is iets misgegaan.");
            }
        }
        game.playGame();
    }
}
