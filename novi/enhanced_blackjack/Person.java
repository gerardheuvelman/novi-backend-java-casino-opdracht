package novi.enhanced_blackjack;

 public class Person {

    private Hand hand;

    // Constructor //
    public Person () {
    }

    // GETSET //


     public Hand getHand() {
         return hand;
     }

     public void setHand(Hand hand) {
         this.hand = hand;
     }

     void performMove(Deck deck, String move) {

    }
    void addCardsToHand(Card[] cards) {
        for (Card card: cards) {
             hand.AddCard(card);
        }

    }

    boolean isStaying() {
        return false;

    }

    boolean isBust() {
        return hand.getTotalValue() > 21;
    }

    int getHandValue() {
        return hand.getTotalValue();
    }

    String renderHand() {
        return hand.render();
    }

 }
