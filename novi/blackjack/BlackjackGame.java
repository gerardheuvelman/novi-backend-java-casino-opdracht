// TO DO LIST
//- Implement y/n input validation loop
// - If all "normal" players are bust, then house does not draw a card at all (and wins automatically)

package novi.blackjack;

import novi.Playable;
import java.util.*;

public class BlackjackGame implements Playable {

    private final Scanner scanner;

    private static boolean gameIsRunning;

    private static final List<Player> players = new ArrayList<>();
    private static final List<Player> activePlayers = new ArrayList<>();
    private  final HashMap<Player, Integer> playerScores = new HashMap<>();

    private static Deck freshNewDeck = null;

    private static final Dealer dealer = new Dealer("HOUSE");

    public BlackjackGame(Scanner scanner) {
        this.scanner =  scanner;
        gameIsRunning = false;
    }

    public void playGame() {

        System.out.println("Welcome to Blackjack!");
        System.out.println("This game uses real cards and real decks, so card counting is a viable strategy!");

        System.out.print("How many people are playing? ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numPlayers; i++) {
            System.out.printf("Player %s, what is your name? ", i+1);
            String playerName = scanner.nextLine();
            Player player = new Player(playerName);
            players.add(player);
            activePlayers.add(player);
        }
        players.add(dealer);
        activePlayers.add(dealer);

        System.out.print("How many decks should we use? ");
        int numDecks = scanner.nextInt();
        scanner.nextLine();

        freshNewDeck = new Deck(numDecks);

        //Set up the game
        System.out.println("OK, here we go!...");
        int currentRound = 0;
        handeRound(true);
        printGameState();

        gameIsRunning = true;

        /////GAME LOOP/////
        while (gameIsRunning) {
            currentRound++;
            System.out.printf("Round %s:\n", currentRound);

            handeRound(false);
            cullPlayers();
            determineWinners();

        }
        ////////////////////
    }

    private void handeRound(boolean firstRound) {
        // Game setup flow
        if (firstRound) {
            for (Player player : players) {
                if (player.getClass() == dealer.getClass()) {
                    dealer.deal(freshNewDeck, player, 1);
                } else {
                    dealer.deal(freshNewDeck, player,2);
                }
            }
        } else {
            // Normal round flow
            for (Player player : players) {
                if (player.getClass() != dealer.getClass()) {
                    // "NORMAL" PLAYER FLOW

                    // First, check if the player has over 21
                    if (!player.bust && !player.folded) {
                        System.out.printf("%s, do you want a a card? (y/n) ", player.getName());
                        char playerChoice = scanner.nextLine().charAt(0);
                        if (playerChoice == 'y') {
                            dealer.deal(freshNewDeck, player, 1);
                        }
                    } else {
                        player.folded = true;
                        // Player has folded, so do nothing else tot do
                    }
                } else {
                    // DEALER FLOW (IMPORTANT: THE DEALER SHOULD ALWAYS BE THE LAST ENTRY IN THE ACTIVE PLAYERS LIST)
                    System.out.print("House turn:\n");


                    // determine the (intermediate) highest card count for this round
                    int runningCardCountTotal = 0;
                    HashMap<Player, Integer> intermediateCardTotals = updateCardTotals();
                    for (int cardTotal : intermediateCardTotals.values()) {
                        if (cardTotal > runningCardCountTotal) {
                            runningCardCountTotal = cardTotal;
                        }
                    }
                    int highestCardCount = runningCardCountTotal;

                    // The dealer makes an informed decision
                    if (dealer.decide(highestCardCount)) {
                        System.out.print("The house plays...\n");
                        dealer.deal(freshNewDeck, dealer, 1);
                    } else {
                        dealer.folded = true;
                        System.out.print("The house folds...\n");
                        // Dealer has folded, so do nothing else to do
                    }
                }
                printGameState();
            }
        }
    }

    private HashMap<Player, Integer> updateCardTotals() {
        for (Player player : players) {
            int cardSum = player.getCardSum();
            playerScores.put(player, cardSum);
        }
        return playerScores;
    }

private void determineWinners() {
        for (Player player : playerScores.keySet()) {
            if (playerScores.get(player) == 21) {
                if (dealer.getCardSum() == 21) {
                    //Declare victory for the dealer
                    System.out.printf("The house wins with %s ", dealer.getCardSum());
                    gameIsRunning = false;
                }
                //Declare victory for this player
                System.out.println("We have a winner!!");
                printGameState();
                System.out.printf("%s has won the game!", player.getName());
                gameIsRunning = false;
            }
        }

    }

    private static void printGameState() {
        System.out.println("Game State: ");
        for (Player player : players) {
            String cardRanks = getCardRanks(player);
            System.out.printf("| %s : %s (%s)\n", player.getName(), cardRanks, player.getCardSum());
        }
    }

    private static String getCardRanks(Player player) {
        StringBuilder builder = new StringBuilder();
        List<Card> drawnCards = player.getDrawnCards();
        builder.append("| ");
        for (Card card : drawnCards) {
            builder.append(card.getRank().toString());
            builder.append(" | ");
        }
        return builder.toString();
    }

    private static void cullPlayers() {
        List<Player> bustPlayers = new ArrayList<>();
        // Collect bust players
        for (Player player : activePlayers) {
            if (player.getCardSum() > 21) {
                System.out.printf("%s, you are Bust!!\n", player.getName());
                bustPlayers.add(player);
            }
        }
        // Remove bust players from active player list
        for (Player player : bustPlayers) {
            activePlayers.remove(player);
            String multiple = (activePlayers.size() > 1)? "s" :  "";
            System.out.printf("%s player%s left...\n", activePlayers.size(), multiple);
        }
    }
}