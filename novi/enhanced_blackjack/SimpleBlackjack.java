package novi.enhanced_blackjack;
import java.util.Scanner;

public class SimpleBlackjack extends EnhancedBlackjackGame {

    public SimpleBlackjack(Scanner inputScanner) {
        super(inputScanner);
        System.out.println("Creating a standard simple deck...");
        deck = new SimpleDeck();
    }
}
