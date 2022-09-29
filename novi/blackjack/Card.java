package novi.blackjack;

import java.util.HashMap;
import java.util.Random;

public class Card {
    private final Suit suit;
    private Rank rank;

    public Card() {
        Random random = new Random();
        int suitPick = random.nextInt(Suit.values().length);
        suit = Suit.values()[suitPick];

        int rankPick = random.nextInt(Rank.values().length);
        rank = Rank.values()[rankPick];
    }

    public Card(Suit s, Rank r) {
        suit = s;
        rank = r;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        HashMap<Rank, Integer> cardValues = new HashMap<>();
        cardValues.put(Rank.Ace, 11);
        cardValues.put(Rank.Two, 2);
        cardValues.put(Rank.Three, 3);
        cardValues.put(Rank.Four, 4);
        cardValues.put(Rank.Five, 5);
        cardValues.put(Rank.Six, 6);
        cardValues.put(Rank.Seven, 7);
        cardValues.put(Rank.Eight, 8);
        cardValues.put(Rank.Nine, 9);
        cardValues.put(Rank.Ten, 10);
        cardValues.put(Rank.Jack, 10);
        cardValues.put(Rank.Queen, 10);
        cardValues.put(Rank.King, 10);

        return cardValues.get(rank);
    }

    public String getName() {
        return String.format("%s of %s",rank, suit);

    }
}
