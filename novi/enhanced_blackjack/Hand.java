package novi.enhanced_blackjack;

import java.util.ArrayList;
import java.util.List;

 public class Hand {

    private List<Card> cards = new ArrayList<>();

    public Hand() {

    }

    public void AddCard(Card card) {
        cards.add(card);
    }

    int getTotalValue() {
        int runningTotal = 0;
        for (Card card : cards) {
            runningTotal += card.getValue().getValue();
        }
        return runningTotal;
    }

    String render() {
        StringBuilder builder = new StringBuilder();
        for (Card card : cards) {
            CardSuit suit = card.getSuit();
            CardValue value = card.getValue();

            builder.append(suit.getSymbol());
            builder.append(value.getValue());
            builder.append(" ");
        }
        return builder.toString();
    }
 }
