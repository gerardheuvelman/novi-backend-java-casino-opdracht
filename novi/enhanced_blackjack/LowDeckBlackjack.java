package novi.enhanced_blackjack;
import java.util.Scanner;

public class LowDeckBlackjack extends EnhancedBlackjackGame {

    public LowDeckBlackjack(Scanner inputScanner) {
        super(inputScanner);
        System.out.println("Creating a \"low\" deck...");
        deck = new LowDeck();
    }
}
