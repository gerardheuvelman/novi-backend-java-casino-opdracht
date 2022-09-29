package novi.blackjack;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck(int numDecks) {

        for (int i = 0; i < numDecks; i++) {
            for (Suit suit :  Suit.values()) {
                cards.add(new Card(suit, Rank.Ace));
                cards.add(new Card(suit, Rank.Two));
                cards.add(new Card(suit, Rank.Three));
                cards.add(new Card(suit, Rank.Four));
                cards.add(new Card(suit, Rank.Five));
                cards.add(new Card(suit, Rank.Six));
                cards.add(new Card(suit, Rank.Seven));
                cards.add(new Card(suit, Rank.Eight));
                cards.add(new Card(suit, Rank.Nine));
                cards.add(new Card(suit, Rank.Ten));
                cards.add(new Card(suit, Rank.Jack));
                cards.add(new Card(suit, Rank.Queen));
                cards.add(new Card(suit, Rank.King));
            }
        }
    }

    public int numCards() {
        return cards.size();
    }

    public Card DrawCard() {
        Random random = new Random();
        return cards.remove(random.nextInt(numCards()-1));
    }
}
