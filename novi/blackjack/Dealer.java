package novi.blackjack;

import java.util.HashMap;
import java.util.List;

public class Dealer extends Player {

    public Dealer(String name) {
        super(name);
    }

    public void deal(Deck deck, Player player, int numCards) {
        for (int i = 0; i < numCards; i++) {
            Card card = deck.DrawCard();
            player.receiveCard(card);
        }
    }

    protected boolean decide (int highestCardCount) {
        if (cardSum < highestCardCount) {
            return true;
        } else return cardSum < 17;
    }

}
