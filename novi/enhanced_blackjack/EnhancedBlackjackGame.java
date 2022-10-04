package novi.enhanced_blackjack;
import novi.Playable;

import java.lang.reflect.Type;
import java.util.Scanner;

public abstract class EnhancedBlackjackGame implements Playable {

    protected  Deck deck;

    private Person player = new Player();
    private Person dealer = new Dealer();

    private Scanner inputScanner;

    // Constructors //

    public EnhancedBlackjackGame(Scanner inputScanner) {
        this.inputScanner = inputScanner;
    }

    // Methods //

     public void playGame() {
         System.out.println("Welkom bij Enhanced Blackjack.");
         setupGame();
         runGameLoop();
     }

    public void setupGame() {
        deck.shuffle();

        player.setHand(new Hand());
        dealer.setHand(new Hand());

        String playerHand = drawCardsAndRender(player, 2 );
        String dealerHand = drawCardsAndRender(dealer, 1 );
        System.out.println("Player: " + playerHand + "(" + player.getHand().getTotalValue() + ")");
        System.out.println("Dealer: " + dealerHand + "(" + dealer.getHand().getTotalValue() + ")");
    }

    private String drawCardsAndRender(Person person, int number) {
        Card[] cards = new Card[number];
        for (int i = 0; i  < number; i++) {
            cards[i] = deck.getNextCard();
        }
        person.addCardsToHand(cards);
        return person.renderHand();
    }

    void runGameLoop() {
        inputScanner.nextLine();
        boolean playerPhase = true;
        boolean dealerPhase = true;
        boolean evaluationPhase = true;
        char playerChoice = 'x';

        while (playerPhase) {
            //player phase
            System.out.println("Play or Stay? (p/s)");
            playerChoice = inputScanner.nextLine().charAt(0);
            if (playerChoice == 'p') {
                System.out.println("Player plays...");
                String playerHand = drawCardsAndRender(player, 1);
                System.out.println("Player: " + playerHand + "(" + player.getHand().getTotalValue() + ")");
                if (player.isBust()) {
                    System.out.println("Player is bust. Player loses!)");
                    playerPhase = false;
                    evaluationPhase = false;
                }
            } else if (playerChoice == 's') {
                System.out.println("player stays...\n");
                playerPhase =false;
            } else {
                System.out.println("Illegal input. Terminating program...");
                playerPhase = false;
                dealerPhase = false;
                evaluationPhase = false;
            }
        }
        while (dealerPhase) {
            // Dealer phase
            if (playerChoice =='s') {
                int target = player.getHandValue();
                if (target > dealer.getHandValue()) {
                    // Dealer plays
                    System.out.println("Dealer plays...");
                    String dealerHand = drawCardsAndRender(dealer, 1);
                    System.out.println("Dealer: " + dealerHand + "(" + dealer.getHandValue() + ")");
                    if (dealer.isBust()) {
                        System.out.println("Dealer is bust. Player Wins!!)");
                        dealerPhase = false;
                        evaluationPhase = false;
                    }
                } else {
                    System.out.println("Dealer Wins!!");
                    dealerPhase = false;
                }
            }
        }
        // evaluation phase
        if (evaluationPhase) {
            if (player.getHandValue() > dealer.getHandValue()) {
                System.out.println("Player wins with " + player.getHandValue());
            }
        }
    }
}
