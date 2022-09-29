package novi.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {

    protected String name;
    protected int cardSum;
    protected boolean folded = false;
    protected boolean bust =false;
    protected List<Card> drawnCards = new ArrayList<>();

    public Player(String name) {
       this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getDrawnCards() {
        return drawnCards;
    }

    public int getCardSum() {
        return cardSum;
    }

    public void receiveCard(Card card) {
        drawnCards.add(card);
        cardSum = recalculateCardSum();
    }

    private int recalculateCardSum() {
        int runningTotal = 0;
        int numAces = 0;
        for (Card card : drawnCards) {
            runningTotal += card.getValue();
            if (card.getRank() == Rank.Ace) {
                numAces++;
            }
            if (runningTotal > 21) {
                if (numAces >0) {
                    runningTotal -= 10;
                    numAces--;
                }
            }
        }
        if (runningTotal > 21) {
            this.bust = true;
        }
        return runningTotal;
    }
}
